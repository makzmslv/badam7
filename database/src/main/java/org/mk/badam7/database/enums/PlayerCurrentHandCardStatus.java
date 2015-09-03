package org.mk.badam7.database.enums;

public enum PlayerCurrentHandCardStatus
{
    IN_HAND(1),
    PLACED_ON_TABLE(2);

    private Integer status;

    PlayerCurrentHandCardStatus(Integer id)
    {
        this.status = id;
    }

    public Integer getStatusCode()
    {
        return status;
    }
}
