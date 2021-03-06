package org.mk.badam7.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GAME")
public class GameEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "GAME_TYPE")
    private Integer gameType;

    @Column(name = "NO_OF_PLAYERS")
    private Integer noOfPlayers;

    @Column(name = "NO_OF_HANDS")
    private Integer noOfHands;

    @Column(name = "STATUS")
    private Integer status;

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
        return "Game [id=" + id + ", gameType=" + gameType + ", noOfPlayers=" + noOfPlayers + ", noOfHands=" + noOfHands + ", status=" + status + "]";
    }

}
