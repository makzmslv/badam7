package org.mk.badam7.database.dao;

import java.util.List;

import org.mk.badam7.database.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDAO extends JpaRepository<GameEntity, Integer>
{
    public List<GameEntity> findByStatus(Integer status);
}
