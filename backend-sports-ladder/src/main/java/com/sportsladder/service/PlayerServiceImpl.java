package com.sportsladder.service;

import com.sportsladder.dataaccess.PlayerRepository;
import com.sportsladder.domain.Challenge;
import com.sportsladder.domain.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Felipe Leite on 7/1/2017.
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public Player getPlayerById(Long id){
        return playerRepository.findPlayerById(id);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public List<Player> sortPlayersByRankAscending(List<Player> players) {
        Collections.sort(players);
        return players;
    }

    @Override
    public List<Player> swapPlayersRank(Player player1, Player player2) {

        if (player1.getRank() == null || player2.getRank() == null) {
            throw new NullPointerException("A players rank cannot be set to null for the swap");
        }

        Player player1swapped = new Player();
        Player player2swapped = new Player();

        player1swapped.setId(player1.getId());
        player1swapped.setName(player1.getName());
        player2swapped.setId(player2.getId());
        player2swapped.setName(player2.getName());

        player1swapped.setRank(player2.getRank());
        player2swapped.setRank(player1.getRank());

        List <Player> swappedRank = new ArrayList<>();

        swappedRank.add(player1swapped);
        swappedRank.add(player2swapped);

        return swappedRank;
    }


    @Override
    public Player savePlayer(Player player) {
        playerRepository.save(player);
        return player;
    }

    @Override
    public List<Player> saveAllPlayers(List<Player> players) {
        playerRepository.save(players);
        return players;
    }

    @Override
    public List<Player> updateRankOffset(Player player, List<Player> players) {
        players.stream().filter(listPlayer -> player.getId() == listPlayer.getId()).forEach(player1 -> player1.setRank(player.getRank()));
        players.stream().filter(listPlayer -> (listPlayer.getRank() != null &&
                listPlayer.getRank() >= player.getRank() &&
                listPlayer.getId() != player.getId())).
                forEach(listPlayer -> listPlayer.setRank(listPlayer.getRank() + 1));
        return players;
    }

    @Override
    public boolean deletePlayer(Long playerId) {
        playerRepository.delete(playerId);
        return true;
    }

    @Override
    public List<Player> getUnrankedPlayers() {
        return playerRepository.findByRank(null);
    }

    @Override
    public List<Player> adjustRankGaps(List<Player> players) {
        Predicate<Player> predicate  = player -> checkRankGap(player, players);
        List<Player> adjustedPlayers = new ArrayList<>();
        adjustedPlayers.addAll(players);
        players.stream().filter(predicate).forEach(player -> player.setRank(player.getRank() - 1));
        return adjustedPlayers;
    }

    /**
     *
     * @param currentPlayer please filter out the first player as this function will collapse
     * @param players
     * @return
     */
    protected boolean checkRankGap(Player currentPlayer, List<Player> players) {
        if (currentPlayer.getRank ()== null  || ((Integer)1).equals(currentPlayer.getRank())) {
            return false;
        }
        return !players.stream().filter(player -> player.getRank() != null).
                anyMatch(player -> currentPlayer.getRank() -1 == player.getRank());
    }

    public Player completeChallenge(Challenge challenge, Player winner) {
        if (winner.equals(challenge.getChallenger())) {
            List<Player> affectedPlayers = playerRepository
                    .findAllByRankGreaterThanAndRankLessThan(challenge.getDefender().getRank(), challenge.getChallenger().getRank());
            affectedPlayers.add(challenge.getDefender());
            affectedPlayers.add(challenge.getChallenger());

            for (Player player : affectedPlayers) {
                player.setRank(player.getRank() + 1);
            }
            challenge.getChallenger().setRank(challenge.getDefender().getRank() - 1);
            saveAllPlayers(affectedPlayers);
        }
        return winner;
    }

}
