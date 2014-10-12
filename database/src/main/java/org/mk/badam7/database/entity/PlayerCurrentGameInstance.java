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

}
