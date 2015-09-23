package org.mk.badam7.database.dao;

import org.mk.badam7.database.entity.GameDetailsEntity;
import org.mk.badam7.database.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDetailsDAO extends JpaRepository<GameDetailsEntity, Integer>
{
    public GameDetailsEntity findByGameEntity(GameEntity gameEntity);
}
