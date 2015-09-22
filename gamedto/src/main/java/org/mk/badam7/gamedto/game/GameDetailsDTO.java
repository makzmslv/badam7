package org.mk.badam7.gamedto.game;

import java.util.Map;

public class GameDetailsDTO
{
    private Integer currentHandId;

    private Integer gameStatus;

    private Integer nextPlayerId;

    private Map<Integer, Integer> playerIds;

    public Integer getCurrentHandId()
    {
        return currentHandId;
    }

    public void setCurrentHandId(Integer currentHandId)
    {
        this.currentHandId = currentHandId;
    }

    public Integer getGameStatus()
    {
        return gameStatus;
    }

    public void setGameStatus(Integer gameStatus)
    {
        this.gameStatus = gameStatus;
    }

    public Map<Integer, Integer> getPlayerIds()
    {
        return playerIds;
    }

    public void setPlayerIds(Map<Integer, Integer> playerIds)
    {
        this.playerIds = playerIds;
    }

    public Integer getNextPlayerId()
    {
        return nextPlayerId;
    }

    public void setNextPlayerId(Integer nextPlayerId)
    {
        this.nextPlayerId = nextPlayerId;
    }

}
