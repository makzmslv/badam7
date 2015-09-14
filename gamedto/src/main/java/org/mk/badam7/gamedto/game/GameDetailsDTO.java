package org.mk.badam7.gamedto.game;

import java.util.List;

public class GameDetailsDTO
{
    private Integer currentHandId;

    private List<Integer> playerIds;

    public Integer getCurrentHandId()
    {
        return currentHandId;
    }

    public void setCurrentHandId(Integer currentHandId)
    {
        this.currentHandId = currentHandId;
    }

    public List<Integer> getPlayerIds()
    {
        return playerIds;
    }

    public void setPlayerIds(List<Integer> playerIds)
    {
        this.playerIds = playerIds;
    }
}
