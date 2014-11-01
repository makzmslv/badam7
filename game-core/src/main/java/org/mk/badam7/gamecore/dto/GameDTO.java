package org.mk.badam7.gamecore.dto;

public class GameDTO
{
    private Integer id;

    private Integer gameType;

    private Integer noOfPlayers;

    private Integer status;

    private Integer noOfHands;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

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

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
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
        return "GameDTO [id=" + id + ", gameType=" + gameType + ", noOfPlayers=" + noOfPlayers + ", status=" + status + ", noOfHands=" + noOfHands + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gameType == null) ? 0 : gameType.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((noOfHands == null) ? 0 : noOfHands.hashCode());
        result = prime * result + ((noOfPlayers == null) ? 0 : noOfPlayers.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameDTO other = (GameDTO) obj;
        if (gameType == null)
        {
            if (other.gameType != null)
                return false;
        }
        else if (!gameType.equals(other.gameType))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (noOfHands == null)
        {
            if (other.noOfHands != null)
                return false;
        }
        else if (!noOfHands.equals(other.noOfHands))
            return false;
        if (noOfPlayers == null)
        {
            if (other.noOfPlayers != null)
                return false;
        }
        else if (!noOfPlayers.equals(other.noOfPlayers))
            return false;
        if (status == null)
        {
            if (other.status != null)
                return false;
        }
        else if (!status.equals(other.status))
            return false;
        return true;
    }

}
