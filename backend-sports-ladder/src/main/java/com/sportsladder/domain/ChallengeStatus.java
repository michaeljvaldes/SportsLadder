package com.sportsladder.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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


    public ChallengeStatus getChallengeStatusFromId(int id){
        List<ChallengeStatus> challengeStatuses = Arrays.stream(ChallengeStatus.values())
                .filter(challengeStatus -> challengeStatus.getId() == id)
                .collect(Collectors.toList());
        if(challengeStatuses.size() != 0)
            return challengeStatuses.get(0);
        else
            return null;
    }
}
