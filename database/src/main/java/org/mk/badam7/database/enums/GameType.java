package org.mk.badam7.database.enums;

public enum GameType
{
    BADAM7(1);

    private Integer status;

    GameType(Integer id)
    {
        this.status = id;
    }

    public Integer getType()
    {
        return status;
    }

}