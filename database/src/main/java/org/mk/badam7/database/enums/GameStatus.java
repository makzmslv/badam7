package org.mk.badam7.database.enums;

import java.util.ArrayList;
import java.util.List;

public enum GameStatus
{
    CREATED(1),
    WAITING_FOR_PLAYERS(2),
    WAITING_TO_START(3),
    IN_PROGRESS(4),
    ABANDONED(5),
    COMPLETED(6);

    private Integer status;

    GameStatus(Integer id)
    {
        this.status = id;
    }

    public Integer getCode()
    {
        return status;
    }

    public static final List<Integer> getAllStatuses()
    {
        List<Integer> statuses = new ArrayList<Integer>();
        statuses.add(CREATED.getCode());
        statuses.add(WAITING_FOR_PLAYERS.getCode());
        statuses.add(WAITING_TO_START.getCode());
        statuses.add(IN_PROGRESS.getCode());
        statuses.add(ABANDONED.getCode());
        statuses.add(COMPLETED.getCode());
        return statuses;
    }

}
