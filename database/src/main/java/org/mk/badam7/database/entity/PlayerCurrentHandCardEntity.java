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
@Table(name = "PLAYER_CURRENT_CARD")
public class PlayerCurrentHandCardEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "REF_CARD")
    private CardEntity cardEntity;

    @ManyToOne
    @JoinColumn(name = "REF_HAND")
    private HandEntity handEntity;

    @ManyToOne
    @JoinColumn(name = "REF_PLAYER_CURRENT_GAME_INSTANCE")
    private PlayerCurrentGameInstanceEntity playerCurrentGameInstanceEntity;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "CARD_REMOVAL_RANK")
    private Integer cardRemovalRank;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public CardEntity getCard()
    {
        return cardEntity;
    }

    public void setCard(CardEntity cardEntity)
    {
        this.cardEntity = cardEntity;
    }

    public PlayerCurrentGameInstanceEntity getPlayerCurrentGameInstance()
    {
        return playerCurrentGameInstanceEntity;
    }

    public void setPlayerCurrentGameInstance(PlayerCurrentGameInstanceEntity playerCurrentGameInstanceEntity)
    {
        this.playerCurrentGameInstanceEntity = playerCurrentGameInstanceEntity;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public CardEntity getCardEntity()
    {
        return cardEntity;
    }

    public void setCardEntity(CardEntity cardEntity)
    {
        this.cardEntity = cardEntity;
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

    public Integer getCardRemovalRank()
    {
        return cardRemovalRank;
    }

    public void setCardRemovalRank(Integer cardRemovalRank)
    {
        this.cardRemovalRank = cardRemovalRank;
    }

    @Override
    public String toString()
    {
        return "PlayerCurrentCard [id=" + id + ", card=" + cardEntity + ", playerCurrentGameInstance=" + playerCurrentGameInstanceEntity + ", status=" + status + ", cardRemovalRank="
                + cardRemovalRank + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cardEntity == null) ? 0 : cardEntity.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((playerCurrentGameInstanceEntity == null) ? 0 : playerCurrentGameInstanceEntity.hashCode());
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
        PlayerCurrentHandCardEntity other = (PlayerCurrentHandCardEntity) obj;
        if (cardEntity == null)
        {
            if (other.cardEntity != null)
                return false;
        }
        else if (!cardEntity.equals(other.cardEntity))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (playerCurrentGameInstanceEntity == null)
        {
            if (other.playerCurrentGameInstanceEntity != null)
                return false;
        }
        else if (!playerCurrentGameInstanceEntity.equals(other.playerCurrentGameInstanceEntity))
            return false;
        return true;
    }

}
