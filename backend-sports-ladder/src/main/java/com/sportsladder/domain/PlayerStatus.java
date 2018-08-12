package com.sportsladder.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Michael Valdes on 8/10/2018.
 */
public enum PlayerStatus {
    PLAYER_STATUS_FREE(1),
    PLAYER_STATUS_CHALLENGE(2);

    private int id;

    public int getId() {
        return id;
    }

    private PlayerStatus(int id){
        this.id = id;
    }


    public PlayerStatus getPlayerStatusFromId(int id){
        List<PlayerStatus> playerStatuses = Arrays.stream(PlayerStatus.values())
                .filter(playerStatus -> playerStatus.getId() == id)
                .collect(Collectors.toList());
        if(playerStatuses.size() != 0)
            return playerStatuses.get(0);
        else
            return null;
    }
}
