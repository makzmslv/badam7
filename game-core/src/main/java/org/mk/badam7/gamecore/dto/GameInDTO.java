package org.mk.badam7.gamecore.dto;

public class GameInDTO
{
    private Integer gameType;

    private Integer noOfPlayers;

    private Integer noOfHands;

    public Integer getGameType()
    {
        return gameType;
    }

    public void setGameType(Integer gameType)
    {
        this.gameType = gameType;
    }

    public Integer getNoOfPlayers()
    {
        return noOfPlayers;
    }

    public void setNoOfPlayers(Integer noOfPlayers)
    {
        this.noOfPlayers = noOfPlayers;
    }

    public Integer getNoOfHands()
    {
        return noOfHands;
    }

    public void setNoOfHands(Integer noOfHands)
    {
        this.noOfHands = noOfHands;
    }

    @Override
    public String toString()
    {
        return "GameInDTO [gameType=" + gameType + ", noOfPlayers=" + noOfPlayers + ", noOfHands=" + noOfHands + "]";
    }

}
