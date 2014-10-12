package org.mk.badam7.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HAND_CURRENT_CARD")
public class HandCurrentCard
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "REF_HAND")
    private Hand hand;

    @OneToMany
    @JoinColumn(name = "REF_CARD")
    private Card card;

    @Column(name = "STATUS")
    private Integer status;

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

    public Hand getHand()
    {
        return hand;
    }

    public void setHand(Hand hand)
    {
        this.hand = hand;
    }

    public Card getCard()
    {
        return card;
    }

    public void setCard(Card card)
    {
        this.card = card;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getCardInsertionRank()
    {
        return cardInsertionRank;
    }

    public void setCardInsertionRank(Integer cardInsertionRank)
    {
        this.cardInsertionRank = cardInsertionRank;
    }

    @Override
    public String toString()
    {
        return "HandCurrentCard [id=" + id + ", hand=" + hand + ", card=" + card + ", status=" + status + ", cardInsertionRank=" + cardInsertionRank + "]";
    }

}
