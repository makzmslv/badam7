package org.mk.badam7.database.dao;

import org.mk.badam7.database.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDAO extends JpaRepository<Game, Integer>
{

}
