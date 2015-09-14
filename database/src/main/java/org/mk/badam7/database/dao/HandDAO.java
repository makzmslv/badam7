package org.mk.badam7.database.dao;

import org.mk.badam7.database.entity.GameEntity;
import org.mk.badam7.database.entity.HandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HandDAO extends JpaRepository<HandEntity, Integer>
{
    @Query(value = "SELECT COUNT(hand) FROM HandEntity hand WHERE hand.gameEntity = :game")
    public Integer getHandNoForGame(@Param("game") GameEntity gameEntity);

    public HandEntity findByGameEntityAndStatus(@Param("game") GameEntity gameEntity, Integer status);

}
