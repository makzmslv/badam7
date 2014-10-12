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
@Table(name = "RESULT_GAME")
public class ResultGame
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

    @Column(name = "POINTS")
    private Integer points;

    @Column(name = "POSITION")
    private Integer position;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "game", cascade = CascadeType.REMOVE)
    private List<ResultHand> handResults;

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

    public Integer getPoints()
    {
        return points;
    }

    public void setPoints(Integer points)
    {
        this.points = points;
    }

    public Integer getPosition()
    {
        return position;
    }

    public void setPosition(Integer position)
    {
        this.position = position;
    }

    public List<ResultHand> getHandResults()
    {
        return handResults;
    }

    public void setHandResults(List<ResultHand> handResults)
    {
        this.handResults = handResults;
    }

    @Override
    public String toString()
    {
        return "ResultGame [id=" + id + ", game=" + game + ", player=" + player + ", points=" + points + ", position=" + position + ", handResults=" + handResults + "]";
    }

}
