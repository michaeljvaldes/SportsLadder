package com.sportsladder.service;

import com.sportsladder.config.TestConfig;
import com.sportsladder.domain.Challenge;
import com.sportsladder.domain.ChallengeStatus;
import com.sportsladder.domain.Player;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Michael Valdes on 8/11/2018.
 */
@ContextConfiguration(classes = {TestConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class ChallengeServiceImplTest {

    @Autowired
    ChallengeService challengeService;

    @Autowired
    PlayerService playerService;

    List<Player> players = new ArrayList<>();

    @Before
    public void setup () {
        Player player1 = new Player();
        player1.setName("Duane");
        player1.setRank(1);

        Player player2 = new Player();
        player2.setName("Also Duane");
        player2.setRank(2);

        Player player3 = new Player();
        player3.setName("Duane the Jock Rhonson");
        player3.setRank(3);

        Player player4 = new Player();
        player4.setName("Definitely Not Duane");
        player4.setRank(4);

        Player player5 = new Player();
        player5.setName("Enaud");
        player5.setRank(5);

        List<Player> newPlayers = new ArrayList<>();
        newPlayers.add(player1);
        newPlayers.add(player2);
        newPlayers.add(player3);
        newPlayers.add(player4);
        newPlayers.add(player5);
        playerService.saveAllPlayers(newPlayers);

        players.addAll(playerService.getAllPlayers());

    }

    @Test
    public void getAllChallenges() throws Exception {
        List<Challenge> expectedChallenges = new ArrayList<>();
        Challenge challenge1 = new Challenge(players.get(0), players.get(1));
        Challenge challenge2 = new Challenge(players.get(2), players.get(3));
        expectedChallenges.add(challenge1);
        expectedChallenges.add(challenge2);
        challengeService.saveAllChallenges(expectedChallenges);

        List<Challenge> queriedChallenges = challengeService.getAllChallenges();
        expectedChallenges.sort(Comparator.comparing(Challenge::getId));

        Assert.assertTrue(expectedChallenges.size() == queriedChallenges.size());
        for(int i = 0; i < expectedChallenges.size(); i++) {
            Assert.assertTrue(expectedChallenges.get(i).equals(queriedChallenges.get(i)));
        }
    }

    @Test
    public void saveChallenge() throws Exception {
        Challenge challenge1 = new Challenge(players.get(0), players.get(1));
        challengeService.saveChallenge(challenge1);
        List<Challenge> queriedChallenges = challengeService.getAllChallenges();
        Assert.assertTrue(queriedChallenges.contains(challenge1));
    }

    @Test
    public void saveAllChallenges() throws Exception {
        List<Challenge> expectedChallenges = new ArrayList<>();
        Challenge challenge1 = new Challenge(players.get(0), players.get(1));
        Challenge challenge2 = new Challenge(players.get(2), players.get(3));
        expectedChallenges.add(challenge1);
        expectedChallenges.add(challenge2);

        challengeService.saveAllChallenges(expectedChallenges);

        List<Challenge> queriedChallenges = challengeService.getAllChallenges();
        for(Challenge challenge : expectedChallenges) {
            Assert.assertTrue(queriedChallenges.contains(challenge));
        }
    }

    @Test
    public void deleteChallenge() throws Exception {
        Challenge challenge1 = new Challenge(players.get(0), players.get(1));
        challengeService.saveChallenge(challenge1);
        challengeService.deleteChallenge(challenge1);
        List<Challenge> queriedChallenges = challengeService.getAllChallenges();
        Assert.assertTrue(!queriedChallenges.contains(challenge1));
    }

    @Test
    public void getChallengesByPlayer() throws Exception {
        List<Challenge> expectedChallenges = new ArrayList<>();
        Challenge challenge1 = new Challenge(players.get(0), players.get(1));
        challenge1.setWinner(players.get(0));
        challenge1.setStatus(ChallengeStatus.CHALLENGE_STATUS_COMPLETE);
        challenge1.setCompletionDate(LocalDateTime.now());
        Challenge challenge2 = new Challenge(players.get(0), players.get(2));
        expectedChallenges.add(challenge1);
        expectedChallenges.add(challenge2);

        Challenge challenge3 = new Challenge(players.get(3), players.get(4));

        List<Challenge> allChallenges = new ArrayList<>();
        allChallenges.add(challenge1);
        allChallenges.add(challenge2);
        allChallenges.add(challenge3);
        challengeService.saveAllChallenges(allChallenges);

        List<Challenge> queriedChallenges = challengeService.getChallengesByPlayer(players.get(0));
        expectedChallenges.sort(Comparator.comparing(Challenge::getId));
        queriedChallenges.sort(Comparator.comparing(Challenge::getId));
        Assert.assertTrue(expectedChallenges.size() == queriedChallenges.size());
        for(int i = 0; i < expectedChallenges.size(); i++) {
            Assert.assertTrue(expectedChallenges.get(0).equals(queriedChallenges.get(0)));
        }

    }

    @Test
    public void getActiveChallengeByPlayer() throws Exception {
        Challenge challenge1 = new Challenge(players.get(0), players.get(1));
        challenge1.setWinner(players.get(0));
        challenge1.setStatus(ChallengeStatus.CHALLENGE_STATUS_COMPLETE);
        challenge1.setCompletionDate(LocalDateTime.now());

        List<Challenge> expectedChallenges = new ArrayList<>();
        Challenge challenge2 = new Challenge(players.get(0), players.get(2));
        expectedChallenges.add(challenge2);

        Challenge challenge3 = new Challenge(players.get(3), players.get(4));

        List<Challenge> allChallenges = new ArrayList<>();
        allChallenges.add(challenge1);
        allChallenges.add(challenge2);
        allChallenges.add(challenge3);
        challengeService.saveAllChallenges(allChallenges);

        Challenge queriedChallenge = challengeService.getActiveChallengeByPlayer(players.get(0));
        Assert.assertTrue(queriedChallenge != null);
            Assert.assertTrue(expectedChallenges.get(0).equals(queriedChallenge));
    }

    @Test
    public void completeChallenge() throws Exception {
        Challenge challenge1 = new Challenge(players.get(0), players.get(1));
        challengeService.completeChallenge(challenge1, players.get(0));
        Assert.assertTrue(players.get(0).equals(challenge1.getWinner()));
        Assert.assertTrue(ChallengeStatus.CHALLENGE_STATUS_COMPLETE.equals(challenge1.getStatus()));
        Assert.assertTrue(challenge1.getCompletionDate() != null);
    }

    @Test
    public void activateChallenge() throws Exception {
        Challenge challenge = challengeService.activateChallenge(players.get(2), players.get(0));
        Challenge challenge1 = challengeService.getActiveChallengeByPlayer(players.get(2));
        Challenge challenge2 = challengeService.getActiveChallengeByPlayer(players.get(0));

        Assert.assertTrue(challenge.equals(challenge1));
        Assert.assertTrue(challenge.equals(challenge2));
        Assert.assertTrue(ChallengeStatus.CHALLENGE_STATUS_ACTIVE.equals(challenge.getStatus()));
    }

    @Test
    public void getRecentChallenges() throws Exception {
        Challenge challenge1 = new Challenge(players.get(0), players.get(1));
        challenge1.setWinner(players.get(0));
        challenge1.setStatus(ChallengeStatus.CHALLENGE_STATUS_COMPLETE);
        challenge1.setCompletionDate(LocalDateTime.now());
        challengeService.saveChallenge(challenge1);

        List<Challenge> queriedChallenges = challengeService.getRecentChallenges(players.get(0), players.get(1));

        Assert.assertTrue(queriedChallenges.size() == 1);
        Assert.assertTrue(queriedChallenges.get(0).equals(challenge1));
    }

    @Test
    public void arePlayersWithinChallengeRange() throws Exception {
        Assert.assertTrue(challengeService.arePlayersWithinChallengeRange(players.get(2), players.get(0)));
        Assert.assertFalse(challengeService.arePlayersWithinChallengeRange(players.get(0), players.get(2)));
        Assert.assertFalse(challengeService.arePlayersWithinChallengeRange(players.get(4), players.get(0)));
    }

}