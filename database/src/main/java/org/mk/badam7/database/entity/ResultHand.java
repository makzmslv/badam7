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
public class ResultHand
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "REF_HAND")
    private Hand hand;

    @OneToOne
    @JoinColumn(name = "REF_PLAYER")
    private Player player;

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

    public Hand getHand()
    {
        return hand;
    }

    public void setHand(Hand hand)
    {
        this.hand = hand;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
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
        return "ResultHand [id=" + id + ", hand=" + hand + ", player=" + player + ", points=" + points + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hand == null) ? 0 : hand.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
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
        ResultHand other = (ResultHand) obj;
        if (hand == null)
        {
            if (other.hand != null)
                return false;
        }
        else if (!hand.equals(other.hand))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (player == null)
        {
            if (other.player != null)
                return false;
        }
        else if (!player.equals(other.player))
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
