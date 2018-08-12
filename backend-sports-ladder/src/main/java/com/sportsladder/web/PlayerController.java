package com.sportsladder.web;

import com.sportsladder.domain.Challenge;
import com.sportsladder.domain.Player;
import com.sportsladder.service.ChallengeService;
import com.sportsladder.service.PlayerService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Felipe Leite on 7/1/2017.
 */
@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ChallengeService challengeService;

    @RequestMapping(value = "/mockdata")
    public void setup() {

        //add players
        Player player1 = new Player();
        player1.setName("Chris Diehl");
        player1.setRank(1);

        Player player2 = new Player();
        player2.setName("Felipe Leite");
        player2.setRank(2);

        Player player3 = new Player();
        player3.setName("Michael Valdes");
        player3.setRank(3);

        Player player4 = new Player();
        player4.setName("Jide Laoye");
        player4.setRank(4);

        Player player5 = new Player();
        player5.setName("Tong Luo");
        player5.setRank(null);

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        playerService.saveAllPlayers(players);


        //add challenges
        Challenge challenge1 = new Challenge(player3, player1);

        List<Challenge> challenges = new ArrayList<>();
        challenges.add(challenge1);
        challengeService.saveAllChallenges(challenges);

    }



    @RequestMapping(value = "/")
    public List<Player> getPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return playerService.sortPlayersByRankAscending(players);
    }

    @RequestMapping(value = "/add/{name}")
    public Player addPlayer(@PathVariable String name) {
        Player player = new Player();
        player.setName(name);
        return playerService.savePlayer(player);
    }

    @RequestMapping(value = "/update/{id}/{name}/{rank}")
    public List<Player> updatePlayer(@PathVariable Long id,
                               @PathVariable String name,
                               @PathVariable Integer rank) {
        Player player = new Player();
        player.setId(id);
        player.setName(name);
        player.setRank(rank);
        return playerService.sortPlayersByRankAscending(playerService.saveAllPlayers(
                playerService.updateRankOffset(player, playerService.getAllPlayers())));

    }

    @RequestMapping(value = "/{id}")
    public Player viewPlayerProfile(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @RequestMapping(value = "/{id}/challenges")
    public List<Challenge> viewPlayerChallenges(@PathVariable Long id) {
        Player player =  playerService.getPlayerById(id);
        return challengeService.getChallengesByPlayer(player);
    }

    @RequestMapping(value = "/challenge/test")
    public Challenge testChallenge() {
        Player player = playerService.getPlayerById(3L);
        Challenge challenge = challengeService.getActiveChallengeByPlayer(player);
        challengeService.completeChallenge(challenge, player);
        playerService.completeChallenge(challenge, player);
        return challenge;
    }


}
