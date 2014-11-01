package org.mk.badam7.database.enums;

public enum PlayerCurrentGameInstanceStatus
{
    JOINED_GAME(1),
    HAND_IN_PROGRESS(2),
    HAND_COMPLETED(3),
    ABANDONED(4),
    GAME_COMPLETED(5);

    private Integer status;

    PlayerCurrentGameInstanceStatus(Integer id)
    {
        this.status = id;
    }

    public Integer getStatusCode()
    {
        return status;
    }
}
