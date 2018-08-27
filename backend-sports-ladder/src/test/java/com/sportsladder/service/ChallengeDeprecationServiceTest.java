package com.sportsladder.service;

import com.sportsladder.config.Rules;
import com.sportsladder.dataaccess.ChallengeRepository;
import com.sportsladder.domain.Challenge;
import com.sportsladder.domain.ChallengeStatus;
import com.sportsladder.domain.Player;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ChallengeDeprecationServiceTest {

    @Mock
    private static Rules rules;

    @Mock
    private PlayerService playerService;

    @Mock
    private ChallengeService challengeService;

    @Mock
    private ChallengeRepository challengeRepository;

    @InjectMocks
    private ChallengeDeprecationService challengeDeprecationService =
            new ChallengeDeprecationService(rules,
                                            playerService,
                                            challengeService,
                                            challengeRepository);

    @Test
    @Ignore //TODO: I can't figure out how to properly inject/mock the rules. This test needs that
    public void testDeprecateExpiredChallenge() throws Exception {
        List<Challenge> activeChallenges = new ArrayList<>();

        Player challenger = new Player();
        Player defender = new Player();

        Challenge expiredChallenge = new Challenge();
        expiredChallenge.setChallenger(challenger);
        expiredChallenge.setDefender(defender);
        expiredChallenge.setStartDate(LocalDateTime.now().plusDays(rules.getChallengeRule().getEligibleDuration()));
        activeChallenges.add(expiredChallenge);

        Mockito.when(challengeRepository.findByStatus(
                    ChallengeStatus.CHALLENGE_STATUS_ACTIVE.getId()))
                .thenReturn(activeChallenges);

        challengeDeprecationService.deprecateExpiringChallenges();

        Assert.assertTrue(expiredChallenge.getCompletionDate() != null);
        Assert.assertTrue(expiredChallenge.getStatus().equals(ChallengeStatus.CHALLENGE_STATUS_COMPLETE));
        Assert.assertTrue(expiredChallenge.getWinner().equals(defender));
    }

}