package org.mk.badam7.database.dao;

import java.util.List;

import org.mk.badam7.database.entity.HandEntity;
import org.mk.badam7.database.entity.HandResultEntity;
import org.mk.badam7.database.entity.PlayerCurrentGameInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResultHandDAO extends JpaRepository<HandResultEntity, Integer>
{
    public List<HandResultEntity> findByPlayerCurrentGameInstanceEntity(PlayerCurrentGameInstanceEntity player);

    @Query(value = "SELECT handResultEntity FROM HandResultEntity handResultEntity WHERE handResultEntity.handEntity = :handEntity AND handResultEntity.points = (SELECT MAX(handResultEntity.points) FROM HandResultEntity handResultEntity)")
    public HandResultEntity findByHandEntityAndPointsIsMax(@Param("handEntity") HandEntity handEntity);
}
