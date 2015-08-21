package org.mk.badam7.database.dao;

import java.util.List;

import org.mk.badam7.database.entity.GameEntity;
import org.mk.badam7.database.entity.PlayerCurrentGameInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerCurrentGameInstanceDAO extends JpaRepository<PlayerCurrentGameInstanceEntity, Integer>
{
    @Query(value = "SELECT count(pcgi) FROM PlayerCurrentGameInstanceEntity pcgi WHERE pcgi.gameEntity = :game")
    public Integer getPlayerCountForGame(@Param("game") GameEntity GameEntity);

    public List<PlayerCurrentGameInstanceEntity> findByGameEntity(GameEntity gameEntity);
}
