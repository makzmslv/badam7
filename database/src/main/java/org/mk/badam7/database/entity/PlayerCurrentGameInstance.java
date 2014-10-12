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
public class PlayerCurrentGameInstance
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "REF_GAME")
    private Game game;

    @OneToOne
    @JoinColumn(name = "REF_PLAYER")
    private Player player;

    @Column(name = "PLAYER_NO")
    private Integer playerNo;

    @Column(name = "STATUS")
    private Integer status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "playerCurrentGameInstance", cascade = CascadeType.REMOVE)
    private List<PlayerCurrentCard> playerCurrentCards;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Game getGame()
    {
        return game;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
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

    public List<PlayerCurrentCard> getPlayerCurrentCards()
    {
        return playerCurrentCards;
    }

    public void setPlayerCurrentCards(List<PlayerCurrentCard> playerCurrentCards)
    {
        this.playerCurrentCards = playerCurrentCards;
    }

    @Override
    public String toString()
    {
        return "PlayerCurrentGameInstance [id=" + id + ", game=" + game + ", player=" + player + ", playerNo=" + playerNo + ", status=" + status + ", playerCurrentCards=" + playerCurrentCards + "]";
    }

}
