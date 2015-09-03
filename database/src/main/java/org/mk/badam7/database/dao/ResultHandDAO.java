package org.mk.badam7.database.dao;

import java.util.List;

import org.mk.badam7.database.entity.HandResultEntity;
import org.mk.badam7.database.entity.PlayerCurrentGameInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultHandDAO extends JpaRepository<HandResultEntity, Integer>
{
    public List<HandResultEntity> findByPlayerCurrentGameInstanceEntity(PlayerCurrentGameInstanceEntity player);
}
