package org.mk.badam7.database.dao;

import org.mk.badam7.database.entity.PlayerCurrentHandCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerCurrentCardDAO extends JpaRepository<PlayerCurrentHandCardEntity, Integer>
{

}
