package org.mk.badam7.database.dao;

import org.mk.badam7.database.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerDAO extends JpaRepository<PlayerEntity, Integer>
{
    public PlayerEntity findByUsernameAndVerified(String username, Boolean isVerified);

    public PlayerEntity findByUsername(String username);

    public PlayerEntity findByEmail(String email);

}
