package org.mk.badam7.gamecore.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.PlayerCurrentGameInstanceDAO;
import org.mk.badam7.database.dao.ResultGameDAO;
import org.mk.badam7.database.dao.ResultHandDAO;
import org.mk.badam7.database.entity.GameEntity;
import org.mk.badam7.database.entity.GameResultEntity;
import org.mk.badam7.database.entity.HandResultEntity;
import org.mk.badam7.database.entity.PlayerCurrentGameInstanceEntity;
import org.mk.badam7.gamecore.common.Badam7Util;
import org.mk.badam7.gamedto.game.GameResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameResultServiceImpl implements GameResultService
{
    @Autowired
    private ResultGameDAO resultGameDAO;

    @Autowired
    private ResultHandDAO resultHandDAO;

    @Autowired
    private PlayerCurrentGameInstanceDAO playerCurrentGameInstanceDAO;

    @Autowired
    private Badam7Util badam7Util;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public List<GameResultDTO> createGameResults(GameEntity gameEntity)
    {
        List<GameResultEntity> gameResults = new ArrayList<GameResultEntity>();
        List<PlayerCurrentGameInstanceEntity> players = playerCurrentGameInstanceDAO.findByGameEntity(gameEntity);
        List<Integer> points = new ArrayList<Integer>();
        for (PlayerCurrentGameInstanceEntity player : players)
        {
            GameResultEntity gameResult = createGameResultForPlayer(gameEntity, player);
            gameResults.add(gameResult);
            points.add(gameResult.getPoints());
        }
        setPositionsOfPlayers(gameResults, points);
        resultGameDAO.save(gameResults);
        return badam7Util.mapListOfEnitiesToDTOs(gameResults, GameResultDTO.class);
    }

    private GameResultEntity createGameResultForPlayer(GameEntity gameEntity, PlayerCurrentGameInstanceEntity player)
    {
        int totalPoints = 0;
        List<HandResultEntity> handResults = resultHandDAO.findByPlayerCurrentGameInstanceEntity(player);
        for (HandResultEntity handResult : handResults)
        {
            totalPoints = totalPoints + handResult.getPoints();
        }
        GameResultEntity gameResult = new GameResultEntity();
        gameResult.setGameEntity(gameEntity);
        gameResult.setPlayerCurrentGameInstanceEntity(player);
        gameResult.setPoints(totalPoints);
        return gameResult;
    }

    private void setPositionsOfPlayers(List<GameResultEntity> gameResults, List<Integer> points)
    {
        Collections.sort(points);
        Collections.reverse(points);
        int index = 0;
        for (GameResultEntity gameResult : gameResults)
        {
            if (gameResult.getPoints() == points.get(index))
            {
                gameResult.setPosition(index + 1);
                index++;
            }
        }
    }
}
