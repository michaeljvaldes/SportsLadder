package com.sportsladder.service;

import com.sportsladder.config.TestConfig;
import com.sportsladder.domain.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Felipe Leite on 7/8/2017.
 */
@ContextConfiguration(classes = {TestConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class PlayerServiceImplTest {


    PlayerServiceImpl playerServiceImpl = new PlayerServiceImpl();

    List<Player> players = new ArrayList<Player>();

    @Before
    public void setup () {
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


        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
    }
    @Test
    public void checkRankGapNegative() throws Exception {
        Assert.assertFalse(playerServiceImpl.checkRankGap(players.get(1), players));
    }

    @Test
    public void checkRankGapNegativeFirst() throws Exception {
        Assert.assertFalse(playerServiceImpl.checkRankGap(players.get(0), players));
    }

    @Test
    public void checkRankGapCurrentNullRank() throws Exception {
        players.remove(2);
        Assert.assertFalse(playerServiceImpl.checkRankGap(players.get(3), players));
    }

    @Test
    public void checkRankGapPositive() throws Exception {
        players.remove(1);
        Assert.assertTrue(playerServiceImpl.checkRankGap(players.get(1), players));
    }

}