package org.mk.badam7.gamecore.playercurrentgameinstance;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mk.badam7.gamecore.PlayerCurrentGameInstanceService.PlayerCurrentGameInstanceService;
import org.mk.badam7.gamedto.playercurrentgameinstance.PlayerCurrentGameInstanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-context.xml", "classpath:databaseContextforTest.xml" })
public class PlayerCurrentGameInstanceServiceTest
{
    @Autowired
    private PlayerCurrentGameInstanceService playerCurrentGameInstanceService;

    @Test
    public void createPlayerCurrentGameInstanceTest()
    {
        PlayerCurrentGameInstanceDTO playerCurrentGameInstanceDTO = playerCurrentGameInstanceService.createPlayerCurrentGameInstance(1, 1);
        assertNotNull(playerCurrentGameInstanceDTO);
    }
}
