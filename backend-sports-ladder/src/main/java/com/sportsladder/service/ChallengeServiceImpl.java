package com.sportsladder.service;

import com.sportsladder.dataaccess.ChallengeRepository;
import com.sportsladder.domain.Challenge;
import com.sportsladder.domain.ChallengeStatus;
import com.sportsladder.domain.Player;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Felipe Leite on 7/1/2017.
 */
@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    ChallengeRepository challengeRepository;

    @Override
    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    @Override
    public Challenge saveChallenge(Challenge challenge) {
        challengeRepository.save(challenge);
        return challenge;
    }

    @Override
    public List<Challenge> saveAllChallenges(List<Challenge> challenges) {
        challengeRepository.save(challenges);
        return challenges;
    }

    @Override
    public boolean deleteChallenge(Challenge challenge) {
        challengeRepository.delete(challenge);
        return true;
    }

    public List<Challenge> getChallengesByPlayer(Player player) {
        List<Challenge> challenges = challengeRepository.findByDefender(player);
        challenges.addAll(challengeRepository.findByChallenger(player));
        return challenges;
    }

    public Challenge getActiveChallengeByPlayer(Player player) {
        List<Challenge> challenges = getChallengesByPlayer(player).stream()
                .filter(challenge -> challenge.getStatus()==ChallengeStatus.CHALLENGE_STATUS_ACTIVE)
                .collect(Collectors.toList());
        if(challenges.size() == 1){
            return challenges.get(0);
        }
        else
            return null;
    }


    /* to activate a challenge, the following conditions must be met:
        1) neither player may have active challenges
        2) the challenger must have a higher rank (lower on ladder) than defender
        3) the challenger must be within 3 ranks of the defender
    */
    public Challenge activateChallenge(Player challenger, Player defender) {
        List<Challenge> activeChallenges = new ArrayList<>();
        activeChallenges.add(getActiveChallengeByPlayer(challenger));
        activeChallenges.add(getActiveChallengeByPlayer(defender));
        while(activeChallenges.remove(null));

        if(!activeChallenges.isEmpty())
            return null;

        if(!arePlayersWithinChallengeRange(challenger, defender))
            return null;

        if(!getRecentChallenges(challenger, defender).isEmpty())
            return null;

        Challenge challenge = new Challenge(challenger, defender);
        challengeRepository.save(challenge);
        return challenge;
    }

    public List<Challenge> getRecentChallenges(Player player1, Player player2) {
        return challengeRepository.findByCompletionDateAfter(LocalDateTime.now().minusDays(7));
    }

    public boolean arePlayersWithinChallengeRange(Player challenger, Player defender) {
        return (challenger.getRank() > defender.getRank()) && (challenger.getRank() <= defender.getRank() + 3);
    }

    public Challenge completeChallenge(Challenge challenge, Player winner) {
        challenge.setWinner(winner);
        challenge.setCompletionDate(LocalDateTime.now());
        challenge.setStatus(ChallengeStatus.CHALLENGE_STATUS_COMPLETE);
        challengeRepository.save(challenge);
        return challenge;
    }

}
