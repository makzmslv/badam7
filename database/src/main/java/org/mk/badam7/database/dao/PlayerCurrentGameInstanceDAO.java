package org.mk.badam7.database.dao;

import org.mk.badam7.database.entity.Game;
import org.mk.badam7.database.entity.PlayerCurrentGameInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerCurrentGameInstanceDAO extends JpaRepository<PlayerCurrentGameInstance, Integer>
{
    @Query(value = "SELECT count(pcgi) FROM PlayerCurrentGameInstance pcgi WHERE pcgi.game = :game")
    public Integer getPlayerCountForGame(@Param("game") Game Game);
}
