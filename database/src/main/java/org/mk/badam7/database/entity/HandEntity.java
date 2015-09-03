package org.mk.badam7.database.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HAND")
public class HandEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "HAND_NO")
    private Integer handNo;

    @ManyToOne
    @JoinColumn(name = "REF_GAME")
    private GameEntity gameEntity;

    @Column(name = "STATUS")
    private Integer status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "handEntity", cascade = CascadeType.REMOVE)
    private List<HandCurrentCardEntity> handCurrentCardEntities;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getHandNo()
    {
        return handNo;
    }

    public void setHandNo(Integer handNo)
    {
        this.handNo = handNo;
    }

    public GameEntity getGame()
    {
        return gameEntity;
    }

    public void setGame(GameEntity gameEntity)
    {
        this.gameEntity = gameEntity;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public List<HandCurrentCardEntity> getHandCurrentCards()
    {
        return handCurrentCardEntities;
    }

    public void setHandCurrentCards(List<HandCurrentCardEntity> handCurrentCardEntities)
    {
        this.handCurrentCardEntities = handCurrentCardEntities;
    }

    public GameEntity getGameEntity()
    {
        return gameEntity;
    }

    public void setGameEntity(GameEntity gameEntity)
    {
        this.gameEntity = gameEntity;
    }

    public List<HandCurrentCardEntity> getHandCurrentCardEntities()
    {
        return handCurrentCardEntities;
    }

    public void setHandCurrentCardEntities(List<HandCurrentCardEntity> handCurrentCardEntities)
    {
        this.handCurrentCardEntities = handCurrentCardEntities;
    }

    @Override
    public String toString()
    {
        return "Hand [id=" + id + ", handNo=" + handNo + ", game=" + gameEntity + ", status=" + status + ", handCurrentCards=" + handCurrentCardEntities + "]";
    }

}
