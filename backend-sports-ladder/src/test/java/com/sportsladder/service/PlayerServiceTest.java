package com.sportsladder.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import com.sportsladder.config.TestConfig;
import com.sportsladder.domain.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Felipe Leite on 7/1/2017.
 */

@ContextConfiguration(classes = {TestConfig.class})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class PlayerServiceTest {

    @Autowired
    PlayerService playerService;

    List<Player> players = new ArrayList<Player>();

    @Before
    public void setup () {
        players.addAll(getPlayers());
    }

    private List<Player> getPlayers() {
        List<Player> localPlayers = new ArrayList<>();
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


        localPlayers.add(player1);
        localPlayers.add(player2);
        localPlayers.add(player3);
        localPlayers.add(player4);
        localPlayers.add(player5);
        return  localPlayers;
    }

    private List<Player> getPlayersWithIds() {
        final long[] i = {1};
        List<Player> instantPlayers = getPlayers();
        instantPlayers.forEach(player -> player.setId(i[0]++));
        return instantPlayers;
    }

    @Test
    public void getAllPlayersTest (){
        playerService.saveAllPlayers(players);
        List<Player> actualPlayers = playerService.getAllPlayers();
        Assert.assertEquals(players, actualPlayers);

    }

    @Test
    public void sortPlayersByRankAscendingTest() {
        playerService.savePlayer(players.get(4));
        playerService.savePlayer(players.get(3));
        playerService.savePlayer(players.get(2));
        playerService.savePlayer(players.get(1));
        playerService.savePlayer(players.get(0));
        List<Player> actualPlayers = playerService.getAllPlayers();
        Assert.assertEquals(players, playerService.sortPlayersByRankAscending(actualPlayers));
    }

    @Test
    public void swapPlayersRankBothWithRank() throws Exception {
        List <Player> swappedPlayers = playerService.swapPlayersRank(players.get(1), players.get(0));
        Predicate <Player> predicateChris = player -> "Chris Diehl".equals(player.getName());
        Predicate <Player> predicateFelipe = player -> "Felipe Leite".equals(player.getName());

        Assert.assertEquals(players.stream().filter(predicateChris).findFirst().get().getRank(),
                swappedPlayers.stream().filter(predicateFelipe).findFirst().get().getRank());

        Assert.assertEquals(players.stream().filter(predicateFelipe).findFirst().get().getRank(),
                swappedPlayers.stream().filter(predicateChris).findFirst().get().getRank());

        Assert.assertEquals(players.stream().filter(predicateFelipe).findFirst().get().getId(),
                swappedPlayers.stream().filter(predicateFelipe).findFirst().get().getId());

        Assert.assertEquals(players.stream().filter(predicateChris).findFirst().get().getId(),
                swappedPlayers.stream().filter(predicateChris).findFirst().get().getId());


    }

    @Test
    public void swapPlayersRankOneNullRank() throws Exception {
        try {
            List<Player> swappedPlayers = playerService.swapPlayersRank(players.get(1), players.get(4));
            fail("Expected null pointer exception");
        } catch (NullPointerException e) {
            //Expected behaviour
        }
    }

    @Test
    public void savePlayer() throws Exception {
        Assert.assertEquals(players.get(0), playerService.savePlayer(players.get(0)));
    }


    @Test
    public void saveModifiedPlayer(){
        playerService.saveAllPlayers(players);
        players.get(1).setName("Felipe Riso Bezerrra Leite");
        playerService.savePlayer(players.get(1));
        Assert.assertEquals(players, playerService.getAllPlayers());

    }

    @Test
    public void saveAllPlayers() throws Exception {
        Assert.assertEquals(players, playerService.saveAllPlayers(players));
    }

    @Test
    public void deletePlayer() throws Exception {
        playerService.saveAllPlayers(players);
        playerService.deletePlayer(players.get(2).getId());
        Integer originalSize = players.size();
        List<Player>expectedPlayers = getPlayersWithIds();
        expectedPlayers.remove(2);
        assertEquals(expectedPlayers, playerService.getAllPlayers());
        assertEquals(originalSize -1 , playerService.getAllPlayers().size());
    }


    @Test
    public void adjustRankGaps() throws Exception {
        players.remove(2);
        List<Player> actualGapClosed = playerService.adjustRankGaps(players);
        players.get(2).setRank(2);
        players.get(3).setRank(3);
        Assert.assertEquals( players, actualGapClosed);
    }

    @Test
    public void adjustRankGapsTwice() throws Exception {
        players.remove(2);
        players.remove(1);
        List<Player> actualGapClosed = playerService.adjustRankGaps(players);
        players.get(1).setRank(2);
        Assert.assertEquals( players, actualGapClosed);
    }


    @Test
    public void getUnrankedPlayers() throws Exception {
        playerService.saveAllPlayers(players);
        Assert.assertEquals(players.get(4), playerService.getUnrankedPlayers().get(0));
    }

    @Test
    public void getUnrankedPlayersEmpty() throws Exception {
        playerService.savePlayer(players.get(0));
        playerService.savePlayer(players.get(1));

        Assert.assertEquals(0, playerService.getUnrankedPlayers().size());
    }

    @Test
    public void updateRankOffset() {
        Player player = new Player();
        player.setId(4l);
        player.setName("Jide Laoye");
        player.setRank(2);
        List<Player> newPlayers = playerService.updateRankOffset(player, getPlayersWithIds());
        List<Player> expectedPlayers = getPlayersWithIds();
        expectedPlayers.get(1).setRank(3);
        expectedPlayers.get(2).setRank(4);
        expectedPlayers.get(3).setRank(2);
        Collections.sort(expectedPlayers);
        Collections.sort(newPlayers);
        Assert.assertEquals(expectedPlayers, newPlayers);

    }

}