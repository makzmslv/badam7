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
public class ResultHandEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "REF_HAND")
    private HandEntity handEntity;

    @OneToOne
    @JoinColumn(name = "REF_PLAYER")
    private PlayerEntity playerEntity;

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

    public PlayerEntity getPlayer()
    {
        return playerEntity;
    }

    public void setPlayer(PlayerEntity playerEntity)
    {
        this.playerEntity = playerEntity;
    }

    public Integer getPoints()
    {
        return points;
    }

    public void setPoints(Integer points)
    {
        this.points = points;
    }

    @Override
    public String toString()
    {
        return "ResultHand [id=" + id + ", hand=" + handEntity + ", player=" + playerEntity + ", points=" + points + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((handEntity == null) ? 0 : handEntity.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((playerEntity == null) ? 0 : playerEntity.hashCode());
        result = prime * result + ((points == null) ? 0 : points.hashCode());
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
        ResultHandEntity other = (ResultHandEntity) obj;
        if (handEntity == null)
        {
            if (other.handEntity != null)
                return false;
        }
        else if (!handEntity.equals(other.handEntity))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (playerEntity == null)
        {
            if (other.playerEntity != null)
                return false;
        }
        else if (!playerEntity.equals(other.playerEntity))
            return false;
        if (points == null)
        {
            if (other.points != null)
                return false;
        }
        else if (!points.equals(other.points))
            return false;
        return true;
    }

}
