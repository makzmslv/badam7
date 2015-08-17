package org.mk.badam7.gamecore.PlayerCurrentGameInstanceService;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.GameDAO;
import org.mk.badam7.database.dao.PlayerCurrentGameInstanceDAO;
import org.mk.badam7.database.dao.PlayerDAO;
import org.mk.badam7.database.entity.GameEntity;
import org.mk.badam7.database.entity.PlayerEntity;
import org.mk.badam7.database.entity.PlayerCurrentGameInstanceEntity;
import org.mk.badam7.database.enums.GameStatus;
import org.mk.badam7.database.enums.PlayerCurrentGameInstanceStatus;
import org.mk.badam7.gamecore.playercurrentgameinstance.PlayerCurrentGameInstanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerCurrentGameInstanceServiceImpl implements PlayerCurrentGameInstanceService
{

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private PlayerCurrentGameInstanceDAO playerCurrentGameInstanceDAO;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public PlayerCurrentGameInstanceDTO createPlayerCurrentGameInstance(Integer gameId, Integer playerId)
    {
        GameEntity gameEntity = getGame(gameId);
        PlayerEntity playerEntity = getPlayer(playerId);
        PlayerCurrentGameInstanceEntity playerCurrentGameInstanceEntity = createPlayerGameInstanceEntity(gameEntity, playerEntity);
        playerCurrentGameInstanceEntity = playerCurrentGameInstanceDAO.save(playerCurrentGameInstanceEntity);
        updateGameStatus(gameEntity, playerCurrentGameInstanceEntity.getPlayerNo());
        PlayerCurrentGameInstanceDTO playerCurrentGameInstanceDTO = dozerMapper.map(playerCurrentGameInstanceEntity, PlayerCurrentGameInstanceDTO.class);

        return playerCurrentGameInstanceDTO;
    }

    private PlayerEntity getPlayer(Integer playerId)
    {
        PlayerEntity playerEntity = playerDAO.findOne(playerId);
        if (playerEntity == null)
        {
            throw new IllegalArgumentException("Player does not exist");
        }
        return playerEntity;
    }

    private GameEntity getGame(Integer gameId)
    {
        GameEntity gameEntity = gameDAO.findOne(gameId);
        if (gameEntity == null)
        {
            throw new IllegalArgumentException("Game with given id does not exist");
        }
        return gameEntity;
    }

    private PlayerCurrentGameInstanceEntity createPlayerGameInstanceEntity(GameEntity gameEntity, PlayerEntity playerEntity)
    {
        PlayerCurrentGameInstanceEntity playerCurrentGameInstanceEntity = new PlayerCurrentGameInstanceEntity();
        playerCurrentGameInstanceEntity.setGame(gameEntity);
        playerCurrentGameInstanceEntity.setPlayer(playerEntity);
        playerCurrentGameInstanceEntity.setStatus(PlayerCurrentGameInstanceStatus.JOINED_GAME.getStatusCode());
        Integer playerNo = playerCurrentGameInstanceDAO.getPlayerCountForGame(gameEntity);
        playerCurrentGameInstanceEntity.setPlayerNo(playerNo + 1);

        return playerCurrentGameInstanceEntity;
    }

    private void updateGameStatus(GameEntity gameEntity, Integer playerNo)
    {
        Integer gameStatus = gameEntity.getStatus();
        if (gameStatus == GameStatus.CREATED.getCode())
        {
            gameEntity.setStatus(GameStatus.WAITING_FOR_PLAYERS.getCode());
            gameDAO.save(gameEntity);
        }

        if (gameEntity.getNoOfPlayers() == playerNo)
        {
            if (gameStatus == GameStatus.CREATED.getCode())
            {
                gameEntity.setStatus(GameStatus.WAITING_TO_START.getCode());
                gameDAO.save(gameEntity);
            }
        }

    }
}
