package com.sportsladder.domain;

import java.util.Arrays;

/**
 * Created by Michael Valdes on 8/10/2018.
 */
public enum ChallengeStatus {
    CHALLENGE_STATUS_ACTIVE(1),
    CHALLENGE_STATUS_COMPLETE(2);

    private int id;

    public int getId() {
        return id;
    }

    private ChallengeStatus(int id){
        this.id = id;
    }


    public static ChallengeStatus getChallengeStatusFromId(int id){
        return Arrays.stream(ChallengeStatus.values())
                .filter(challengeStatus -> challengeStatus.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
