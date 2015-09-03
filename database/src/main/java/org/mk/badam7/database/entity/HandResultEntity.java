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
@Table(name = "RESULT_HAND")
public class HandResultEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "REF_HAND")
    private HandEntity handEntity;

    @OneToOne
    @JoinColumn(name = "REF_PLAYER")
    private PlayerCurrentGameInstanceEntity playerCurrentGameInstanceEntity;

    @Column(name = "POINTS")
    private Integer points;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public HandEntity getHand()
    {
        return handEntity;
    }

    public void setHand(HandEntity handEntity)
    {
        this.handEntity = handEntity;
    }

    public Integer getPoints()
    {
        return points;
    }

    public void setPoints(Integer points)
    {
        this.points = points;
    }

    public HandEntity getHandEntity()
    {
        return handEntity;
    }

    public void setHandEntity(HandEntity handEntity)
    {
        this.handEntity = handEntity;
    }

    public PlayerCurrentGameInstanceEntity getPlayerCurrentGameInstanceEntity()
    {
        return playerCurrentGameInstanceEntity;
    }

    public void setPlayerCurrentGameInstanceEntity(PlayerCurrentGameInstanceEntity playerCurrentGameInstanceEntity)
    {
        this.playerCurrentGameInstanceEntity = playerCurrentGameInstanceEntity;
    }

    @Override
    public String toString()
    {
        return "ResultHand [id=" + id + ", hand=" + handEntity + ", player=" + playerCurrentGameInstanceEntity + ", points=" + points + "]";
    }

}
