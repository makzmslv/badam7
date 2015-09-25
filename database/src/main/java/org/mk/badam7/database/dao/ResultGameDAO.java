package org.mk.badam7.database.dao;

import java.util.List;

import org.mk.badam7.database.entity.GameEntity;
import org.mk.badam7.database.entity.GameResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultGameDAO extends JpaRepository<GameResultEntity, Integer>
{
    public GameResultEntity findByGameEntityAndPosition(GameEntity gameEntity, Integer position);

    public List<GameResultEntity> findByGameEntityOrderByPositionAsc(GameEntity gameEntity);
}
