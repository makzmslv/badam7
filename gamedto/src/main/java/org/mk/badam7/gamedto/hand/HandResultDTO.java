package org.mk.badam7.gamedto.hand;

public class HandResultDTO
{
    private Integer id;

    private Integer handId;

    private Integer playerCurrentGameInstanceId;

    private Integer points;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getHandId()
    {
        return handId;
    }

    public void setHandId(Integer handId)
    {
        this.handId = handId;
    }

    public Integer getPlayerCurrentGameInstanceId()
    {
        return playerCurrentGameInstanceId;
    }

    public void setPlayerCurrentGameInstanceId(Integer playerCurrentGameInstanceId)
    {
        this.playerCurrentGameInstanceId = playerCurrentGameInstanceId;
    }

    public Integer getPoints()
    {
        return points;
    }

    public void setPoints(Integer points)
    {
        this.points = points;
    }
}
