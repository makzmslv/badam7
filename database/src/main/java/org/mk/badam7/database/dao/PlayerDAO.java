package org.mk.badam7.database.dao;

import org.mk.badam7.database.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerDAO extends JpaRepository<Player, Integer>
{
    public Player findByUsernameAndVerified(String username, Boolean isVerified);

}
