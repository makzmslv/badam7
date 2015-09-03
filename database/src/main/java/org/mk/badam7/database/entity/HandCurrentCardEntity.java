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
@Table(name = "HAND_CURRENT_CARD")
public class HandCurrentCardEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "REF_HAND")
    private HandEntity handEntity;

    @OneToOne
    @JoinColumn(name = "REF_CARD")
    private CardEntity cardEntity;

    @Column(name = "CARD_INSERTION_RANK")
    private Integer cardInsertionRank;

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

    public CardEntity getCard()
    {
        return cardEntity;
    }

    public void setCard(CardEntity cardEntity)
    {
        this.cardEntity = cardEntity;
    }

    public Integer getCardInsertionRank()
    {
        return cardInsertionRank;
    }

    public void setCardInsertionRank(Integer cardInsertionRank)
    {
        this.cardInsertionRank = cardInsertionRank;
    }

    public HandEntity getHandEntity()
    {
        return handEntity;
    }

    public void setHandEntity(HandEntity handEntity)
    {
        this.handEntity = handEntity;
    }

    public CardEntity getCardEntity()
    {
        return cardEntity;
    }

    public void setCardEntity(CardEntity cardEntity)
    {
        this.cardEntity = cardEntity;
    }

    @Override
    public String toString()
    {
        return "HandCurrentCard [id=" + id + ", hand=" + handEntity + ", card=" + cardEntity + ", cardInsertionRank=" + cardInsertionRank + "]";
    }

}
