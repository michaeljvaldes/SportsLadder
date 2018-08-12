package com.sportsladder.service;

import com.sportsladder.domain.Challenge;
import com.sportsladder.domain.Player;
import java.util.List;

/**
 * Created by Felipe Leite on 7/1/2017.
 */
public interface ChallengeService {


    /**
     * Gets the list of all challenges in the DB
     * @return the unordered list of challenges
     */
    public List<Challenge> getAllChallenges();

    /**
     * Saves challenge
     * @param challenge
     * @return
     */
    public Challenge saveChallenge(Challenge challenge);

    public List<Challenge> saveAllChallenges(List<Challenge> challenges);

    public boolean deleteChallenge(Challenge challenge);

    public List<Challenge> getChallengesByPlayer(Player player);

    public Challenge getActiveChallengeByPlayer(Player player);

    public Challenge completeChallenge(Challenge challenge, Player winner);

    public Challenge activateChallenge(Player challenger, Player defender);

    public boolean arePlayersWithinChallengeRange(Player challenger, Player defender);

    public List<Challenge> getRecentChallenges(Player player1, Player player2);
}
