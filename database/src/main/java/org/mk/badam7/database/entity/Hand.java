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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "HAND")
public class Hand
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "HAND_NO")
    private Integer handNo;

    @ManyToOne
    @JoinColumn(name = "REF_GAME")
    private Game game;

    @ManyToMany
    @JoinColumn(name = "REF_PLAYER")
    private Player currentPlayer;

    @Column(name = "STATUS")
    private Integer status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hand", cascade = CascadeType.REMOVE)
    private List<HandCurrentCard> handCurrentCards;

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

    public Game getGame()
    {
        return game;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer)
    {
        this.currentPlayer = currentPlayer;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public List<HandCurrentCard> getHandCurrentCards()
    {
        return handCurrentCards;
    }

    public void setHandCurrentCards(List<HandCurrentCard> handCurrentCards)
    {
        this.handCurrentCards = handCurrentCards;
    }

    @Override
    public String toString()
    {
        return "Hand [id=" + id + ", handNo=" + handNo + ", game=" + game + ", currentPlayer=" + currentPlayer + ", status=" + status + ", handCurrentCards=" + handCurrentCards + "]";
    }

}
