package org.mk.badam7.database.dao;

import java.util.List;

import org.mk.badam7.database.entity.HandEntity;
import org.mk.badam7.database.entity.PlayerCurrentGameInstanceEntity;
import org.mk.badam7.database.entity.PlayerCurrentHandCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerCurrentHandCardDAO extends JpaRepository<PlayerCurrentHandCardEntity, Integer>
{
    public List<PlayerCurrentHandCardEntity> findByHandEntityAndPlayerCurrentGameInstanceEntity(HandEntity handEntity, PlayerCurrentGameInstanceEntity playerCurrentGameInstanceEntity);
}
