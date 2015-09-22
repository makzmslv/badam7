package org.mk.badam7.gamecore.game;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mk.badam7.database.dao.CardDAO;
import org.mk.badam7.database.entity.CardEntity;
import org.mk.badam7.database.enums.GameType;
import org.mk.badam7.gamecore.dbunit.AbstractDbUnit;
import org.mk.badam7.gamecore.dbunit.ExpectedDataSet;
import org.mk.badam7.gamecore.dbunit.SetUpDataSet;
import org.mk.badam7.gamedto.game.GameDetailsDTO;
import org.mk.badam7.gamedto.game.GameInDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class GameCRUDServiceTest extends AbstractDbUnit
{
    @Autowired
    private GameService gameService;

    @Autowired
    private CardDAO cardDAO;

    @Test
    @SetUpDataSet(xmlPath = "01_FindGameById.xml")
    public void getGameTest()
    {
        GameDetailsDTO gameDTO = gameService.getById(1);
        CardEntity card = cardDAO.findOne(1);
        if (isIt7OfHearts(card))
        {
            System.out.println("here");
        }
        else
        {
            System.out.println("there");
        }

        assertNotNull(gameDTO);
        System.out.println(gameDTO);
    }

    @Test
    @SetUpDataSet(xmlPath = "01_FindGameById.xml")
    @ExpectedDataSet(xmlPath = "02_Create_Game.xml")
    public void createGameTest()
    {
        GameInDTO gameInDTO = new GameInDTO();
        gameInDTO.setGameType(GameType.BADAM7.getType());
        gameInDTO.setNoOfHands(4);
        gameInDTO.setNoOfPlayers(4);
        gameService.createGame(gameInDTO);
    }

    private boolean isIt7OfHearts(CardEntity cardEntity)
    {
        if (cardEntity.getSuite().equals("H") && cardEntity.getValue() == 7)
        {
            return true;
        }
        return false;
    }

}