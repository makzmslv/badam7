package org.mk.badam7.gamedto.playercurrenthand;

public class PlayerCurrentHandCardDTO
{
    private Integer id;

    private Integer status;

    private Integer handId;

    private Integer playerCurrentGameInstanceId;

    private Integer cardValue;

    private Integer cardId;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
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

    public Integer getCardValue()
    {
        return cardValue;
    }

    public void setCardValue(Integer cardValue)
    {
        this.cardValue = cardValue;
    }

    public Integer getCardId()
    {
        return cardId;
    }

    public void setCardId(Integer cardId)
    {
        this.cardId = cardId;
    }

}
