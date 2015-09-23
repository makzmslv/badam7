package org.mk.badam7.database.dao;

import java.util.List;

import org.mk.badam7.database.entity.CardEntity;
import org.mk.badam7.database.entity.HandEntity;
import org.mk.badam7.database.entity.PlayerCurrentGameInstanceEntity;
import org.mk.badam7.database.entity.PlayerCurrentHandCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerCurrentHandCardDAO extends JpaRepository<PlayerCurrentHandCardEntity, Integer>
{
    public List<PlayerCurrentHandCardEntity> findByHandEntityAndPlayerCurrentGameInstanceEntityAndStatus(HandEntity handEntity, PlayerCurrentGameInstanceEntity playerCurrentGameInstanceEntity,
            Integer status);

    public PlayerCurrentHandCardEntity findByHandEntityAndCardEntity(HandEntity handEntity, CardEntity cardEntity);

    @Query(value = "SELECT playerCurrentCard FROM PlayerCurrentHandCardEntity playerCurrentCard WHERE playerCurrentCard.handEntity = :handEntity  AND playerCurrentCard.playerCurrentGameInstanceEntity = :player AND playerCurrentCard.cardEntity.suite = :suite AND playerCurrentCard.cardEntity.value = :value")
    public PlayerCurrentHandCardEntity getByHandEntityAndPlayerAndCardSuiteAndCardValue(@Param("handEntity") HandEntity handEntity, @Param("player") PlayerCurrentGameInstanceEntity player,
            @Param("suite") String suite, @Param("value") Integer value);
}
