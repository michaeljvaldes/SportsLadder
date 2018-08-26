package com.sportsladder.domain;

import org.junit.Assert;
import org.junit.Test;

public class ChallengeStatusTest {

    @Test
    public void testGetId() throws Exception {
        Assert.assertEquals(1, ChallengeStatus.CHALLENGE_STATUS_ACTIVE.getId());
        Assert.assertEquals(2, ChallengeStatus.CHALLENGE_STATUS_COMPLETE.getId());
    }

    @Test
    public void testGetChallengeStatusFromIdActive() throws Exception {
        Assert.assertEquals(ChallengeStatus.CHALLENGE_STATUS_ACTIVE,
                ChallengeStatus.getChallengeStatusFromId(1));
    }

    @Test
    public void testGetChallengeStatusFromIdComplete() throws Exception {
        Assert.assertEquals(ChallengeStatus.CHALLENGE_STATUS_COMPLETE,
                ChallengeStatus.getChallengeStatusFromId(2));
    }

    @Test
    public void testGetChallengeStatusFromIdNull() throws Exception {
        Assert.assertEquals(null,
                ChallengeStatus.getChallengeStatusFromId(0));
    }
}