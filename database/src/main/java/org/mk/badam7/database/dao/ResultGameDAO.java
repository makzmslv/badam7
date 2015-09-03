package org.mk.badam7.database.dao;

import org.mk.badam7.database.entity.GameResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultGameDAO extends JpaRepository<GameResultEntity, Integer>
{

}
