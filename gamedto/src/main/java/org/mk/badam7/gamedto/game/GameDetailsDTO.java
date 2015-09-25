package org.mk.badam7.gamedto.game;

import java.util.Map;

public class GameDetailsDTO
{
    private Integer gameId;

    private Integer currentHandId;

    private Integer gameStatus;

    private Integer currentHandStatus;

    private Integer handWinnerId;

    private Integer gameWinnerId;

    private Integer currentPlayerId;

    private Map<Integer, Integer> playerIds;

    public Integer getGameId()
    {
        return gameId;
    }

    public void setGameId(Integer gameId)
    {
        this.gameId = gameId;
    }

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

    public Integer getCurrentPlayerId()
    {
        return currentPlayerId;
    }

    public void setCurrentPlayerId(Integer currentPlayerId)
    {
        this.currentPlayerId = currentPlayerId;
    }

    public Integer getCurrentHandStatus()
    {
        return currentHandStatus;
    }

    public void setCurrentHandStatus(Integer currentHandStatus)
    {
        this.currentHandStatus = currentHandStatus;
    }

    public Integer getHandWinnerId()
    {
        return handWinnerId;
    }

    public void setHandWinnerId(Integer handWinnerId)
    {
        this.handWinnerId = handWinnerId;
    }

    public Integer getGameWinnerId()
    {
        return gameWinnerId;
    }

    public void setGameWinnerId(Integer gameWinnerId)
    {
        this.gameWinnerId = gameWinnerId;
    }

}
