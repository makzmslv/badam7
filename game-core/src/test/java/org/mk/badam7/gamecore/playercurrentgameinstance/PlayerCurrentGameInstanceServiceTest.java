package org.mk.badam7.gamecore.playercurrentgameinstance;

import org.junit.Test;
import org.mk.badam7.gamecore.PlayerCurrentGameInstanceService.PlayerCurrentGameInstanceService;
import org.mk.badam7.gamecore.dbunit.AbstractDbUnit;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerCurrentGameInstanceServiceTest extends AbstractDbUnit
{
    @Autowired
    private PlayerCurrentGameInstanceService playerCurrentGameInstanceService;

    @Test
    public void createGameTest()
    {
    }

}
