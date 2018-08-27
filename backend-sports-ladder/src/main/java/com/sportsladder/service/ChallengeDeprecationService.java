package com.sportsladder.service;

import static com.sportsladder.domain.ChallengeStatus.CHALLENGE_STATUS_ACTIVE;

import com.sportsladder.config.Rules;
import com.sportsladder.dataaccess.ChallengeRepository;
import com.sportsladder.domain.Challenge;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Michael Valdes on 8/11/2018.
 */
@Component
@ComponentScan(basePackages = {"com.sportsladder.config"})
public class ChallengeDeprecationService {

    private Rules rules;

    private PlayerService playerService;

    private ChallengeService challengeService;

    private ChallengeRepository challengeRepository;

    @Autowired
    public ChallengeDeprecationService(Rules rules,
                                       PlayerService playerService,
                                       ChallengeService challengeService,
                                       ChallengeRepository challengeRepository) {
        this.rules = rules;
        this.playerService = playerService;
        this.challengeService = challengeService;
        this.challengeRepository = challengeRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void deprecateExpiringChallenges(){
        System.out.println("Checking for expired Challenges");
        System.out.println(rules.getChallengeRule().getEligibleDuration());
        LocalDateTime now = LocalDateTime.now();

        List<Challenge> activeChallenges = challengeRepository.findByStatus(CHALLENGE_STATUS_ACTIVE.getId());
        activeChallenges = activeChallenges.stream()
            .filter(challenge -> now.isAfter(
                challenge.getStartDate().plusDays(
                    rules.getChallengeRule().getEligibleDuration()
                )
            )).collect(Collectors.toList());

        for (Challenge challenge : activeChallenges) {
            challengeService.completeChallenge(challenge, challenge.getChallenger());
            playerService.completeChallenge(challenge, challenge.getChallenger());
            System.out.print("Challenge id: " + challenge.getId() + " deprecated.");
        }
    }

}
