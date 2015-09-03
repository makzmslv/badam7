package org.mk.badam7.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RESULT_GAME")
public class GameResultEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "REF_GAME")
    private GameEntity gameEntity;

    @OneToOne
    @JoinColumn(name = "REF_PLAYER")
    private PlayerCurrentGameInstanceEntity playerCurrentGameInstanceEntity;

    @Column(name = "POINTS")
    private Integer points;

    @Column(name = "POSITION")
    private Integer position;

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

    public PlayerCurrentGameInstanceEntity getPlayerCurrentGameInstanceEntity()
    {
        return playerCurrentGameInstanceEntity;
    }

    public void setPlayerCurrentGameInstanceEntity(PlayerCurrentGameInstanceEntity playerCurrentGameInstanceEntity)
    {
        this.playerCurrentGameInstanceEntity = playerCurrentGameInstanceEntity;
    }

    public Integer getPoints()
    {
        return points;
    }

    public void setPoints(Integer points)
    {
        this.points = points;
    }

    public Integer getPosition()
    {
        return position;
    }

    public void setPosition(Integer position)
    {
        this.position = position;
    }

    @Override
    public String toString()
    {
        return "ResultGame [id=" + id + ", game=" + gameEntity + ", player=" + playerCurrentGameInstanceEntity + ", points=" + points + ", position=" + position + "]";
    }

}
