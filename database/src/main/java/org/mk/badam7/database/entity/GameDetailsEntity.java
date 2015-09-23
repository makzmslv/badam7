package org.mk.badam7.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GAME_DETAILS")
public class GameDetailsEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "REF_GAME")
    private GameEntity gameEntity;

    @Column(name = "CURRENT_HAND_ID")
    private Integer currentHandId;

    @Column(name = "CURRENT_PLAYER_ID")
    private Integer currentPlayerId;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public GameEntity getGameEntity()
    {
        return gameEntity;
    }

    public void setGameEntity(GameEntity gameEntity)
    {
        this.gameEntity = gameEntity;
    }

    public Integer getCurrentHandId()
    {
        return currentHandId;
    }

    public void setCurrentHandId(Integer currentHandId)
    {
        this.currentHandId = currentHandId;
    }

    public Integer getCurrentPlayerId()
    {
        return currentPlayerId;
    }

    public void setCurrentPlayerId(Integer currentPlayerId)
    {
        this.currentPlayerId = currentPlayerId;
    }

}
