package org.mk.badam7.gamedto.hand;

public class HandCurrentCardDTO
{
    private Integer id;

    private Integer handId;

    private Integer cardId;

    private Integer cardInsertionRank;

    private Integer playerCurrentGameInstanceId;

    public Integer getHandId()
    {
        return handId;
    }

    public void setHandId(Integer handId)
    {
        this.handId = handId;
    }

    public Integer getCardId()
    {
        return cardId;
    }

    public void setCardId(Integer cardId)
    {
        this.cardId = cardId;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getCardInsertionRank()
    {
        return cardInsertionRank;
    }

    public void setCardInsertionRank(Integer cardInsertionRank)
    {
        this.cardInsertionRank = cardInsertionRank;
    }

    public Integer getPlayerCurrentGameInstanceId()
    {
        return playerCurrentGameInstanceId;
    }

    public void setPlayerCurrentGameInstanceId(Integer playerCurrentGameInstanceId)
    {
        this.playerCurrentGameInstanceId = playerCurrentGameInstanceId;
    }
}
