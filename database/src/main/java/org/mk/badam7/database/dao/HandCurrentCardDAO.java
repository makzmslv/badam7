package org.mk.badam7.database.dao;

import java.util.List;

import org.mk.badam7.database.entity.HandCurrentCardEntity;
import org.mk.badam7.database.entity.HandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HandCurrentCardDAO extends JpaRepository<HandCurrentCardEntity, Integer>
{
    public List<HandCurrentCardEntity> findByHandEntity(HandEntity handEntity);

    @Query(value = "SELECT handCurrentCard FROM HandCurrentCardEntity handCurrentCard WHERE handCurrentCard.handEntity =:handEntity AND handCurrentCard.cardEntity.suite = :suite ORDER BY handCurrentCard.cardEntity.value ASC")
    public List<HandCurrentCardEntity> getByHandAndSuiteAndOrderByValue(@Param("handEntity") HandEntity handEntity, @Param("suite") String suite);

    @Query(value = "SELECT handCurrentCard FROM HandCurrentCardEntity handCurrentCard WHERE handCurrentCard.handEntity = :handEntity AND handCurrentCard.id = (SELECT MAX(handCurrentCard.id) FROM HandCurrentCardEntity handCurrentCard)")
    public HandCurrentCardEntity getByHandEntityAndId(@Param("handEntity") HandEntity handEntity);

    @Query(value = "SELECT max(handCurrentCard.cardEntity.value) FROM HandCurrentCardEntity handCurrentCard WHERE handCurrentCard.cardEntity.suite = :suite)")
    public Integer getByCardSuiteAndCardValueMax(@Param("suite") String suite);

    @Query(value = "SELECT min(handCurrentCard.cardEntity.value) FROM HandCurrentCardEntity handCurrentCard WHERE handCurrentCard.cardEntity.suite = :suite)")
    public Integer getByCardSuiteAndCardValueMin(@Param("suite") String suite);
}
