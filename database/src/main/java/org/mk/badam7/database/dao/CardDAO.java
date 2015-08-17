package org.mk.badam7.database.dao;

import org.mk.badam7.database.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardDAO extends JpaRepository<CardEntity, Integer>
{

}
