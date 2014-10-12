package org.mk.badam7.database.enums;

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

}
