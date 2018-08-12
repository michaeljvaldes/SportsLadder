package com.sportsladder.service;

import static com.sportsladder.domain.ChallengeStatus.CHALLENGE_STATUS_ACTIVE;

import com.sportsladder.dataaccess.ChallengeRepository;
import com.sportsladder.domain.Challenge;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Michael Valdes on 8/11/2018.
 */
@Component
public class ChallengeDeprecationService {

    @Autowired
    PlayerService playerService;

    @Autowired
    ChallengeService challengeService;

    @Autowired
    ChallengeRepository challengeRepository;

    @Scheduled(fixedRate = 60000)
    public void deprecateExpiringChallenges(){
        System.out.print("Checking for expired Challenges\n");
        LocalDateTime now = LocalDateTime.now();
        List<Challenge> activeChallenges = challengeRepository.findByStatus(CHALLENGE_STATUS_ACTIVE.getId());
        activeChallenges = activeChallenges.stream()
                .filter(challenge -> now.isAfter(challenge.getStartDate().plusDays(7)))
                .collect(Collectors.toList());
        for (Challenge challenge : activeChallenges) {
            challengeService.completeChallenge(challenge, challenge.getChallenger());
            playerService.completeChallenge(challenge, challenge.getChallenger());
            System.out.print("Challenge id: " + challenge.getId() + " deprecated.");
        }
    }

}
