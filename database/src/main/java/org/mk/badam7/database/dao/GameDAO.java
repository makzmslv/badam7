package org.mk.badam7.database.dao;

import org.mk.badam7.database.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDAO extends JpaRepository<GameEntity, Integer>
{

}
