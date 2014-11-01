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

    public Integer getStatusCode()
    {
        return status;
    }

    public static final List<Integer> getAllStatuses()
    {
        List<Integer> statuses = new ArrayList<Integer>();
        statuses.add(CREATED.getStatusCode());
        statuses.add(WAITING_FOR_PLAYERS.getStatusCode());
        statuses.add(WAITING_TO_START.getStatusCode());
        statuses.add(IN_PROGRESS.getStatusCode());
        statuses.add(ABANDONED.getStatusCode());
        statuses.add(COMPLETED.getStatusCode());
        return statuses;
    }

}
