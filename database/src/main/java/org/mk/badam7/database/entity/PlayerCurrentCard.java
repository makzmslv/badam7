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
public class PlayerCurrentCard
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "REF_CARD")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "REF_PLAYER_CURRENT_GAME_INSTANCE")
    private PlayerCurrentGameInstance playerCurrentGameInstance;

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

    public Card getCard()
    {
        return card;
    }

    public void setCard(Card card)
    {
        this.card = card;
    }

    public PlayerCurrentGameInstance getPlayerCurrentGameInstance()
    {
        return playerCurrentGameInstance;
    }

    public void setPlayerCurrentGameInstance(PlayerCurrentGameInstance playerCurrentGameInstance)
    {
        this.playerCurrentGameInstance = playerCurrentGameInstance;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
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
        return "PlayerCurrentCard [id=" + id + ", card=" + card + ", playerCurrentGameInstance=" + playerCurrentGameInstance + ", status=" + status + ", cardRemovalRank=" + cardRemovalRank + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((card == null) ? 0 : card.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((playerCurrentGameInstance == null) ? 0 : playerCurrentGameInstance.hashCode());
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
        PlayerCurrentCard other = (PlayerCurrentCard) obj;
        if (card == null)
        {
            if (other.card != null)
                return false;
        }
        else if (!card.equals(other.card))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (playerCurrentGameInstance == null)
        {
            if (other.playerCurrentGameInstance != null)
                return false;
        }
        else if (!playerCurrentGameInstance.equals(other.playerCurrentGameInstance))
            return false;
        return true;
    }

}
