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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYER_GAME_CURRENT_INSTANCE")
public class PlayerCurrentGameInstanceEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "REF_GAME")
    private GameEntity gameEntity;

    @OneToOne
    @JoinColumn(name = "REF_PLAYER")
    private PlayerEntity playerEntity;

    @Column(name = "PLAYER_NO")
    private Integer playerNo;

    @Column(name = "STATUS")
    private Integer status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "playerCurrentGameInstanceEntity", cascade = CascadeType.REMOVE)
    private List<PlayerCurrentCardEntity> playerCurrentCardEntities;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public GameEntity getGame()
    {
        return gameEntity;
    }

    public void setGame(GameEntity gameEntity)
    {
        this.gameEntity = gameEntity;
    }

    public PlayerEntity getPlayer()
    {
        return playerEntity;
    }

    public void setPlayer(PlayerEntity playerEntity)
    {
        this.playerEntity = playerEntity;
    }

    public Integer getPlayerNo()
    {
        return playerNo;
    }

    public void setPlayerNo(Integer playerNo)
    {
        this.playerNo = playerNo;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public List<PlayerCurrentCardEntity> getPlayerCurrentCards()
    {
        return playerCurrentCardEntities;
    }

    public void setPlayerCurrentCards(List<PlayerCurrentCardEntity> playerCurrentCardEntities)
    {
        this.playerCurrentCardEntities = playerCurrentCardEntities;
    }

    @Override
    public String toString()
    {
        return "PlayerCurrentGameInstance [id=" + id + ", game=" + gameEntity + ", player=" + playerEntity + ", playerNo=" + playerNo + ", status=" + status + ", playerCurrentCards="
                + playerCurrentCardEntities + "]";
    }

}
