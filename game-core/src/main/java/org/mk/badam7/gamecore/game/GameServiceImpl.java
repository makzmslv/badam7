package org.mk.badam7.gamecore.game;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.CardDAO;
import org.mk.badam7.database.dao.GameDAO;
import org.mk.badam7.database.dao.HandDAO;
import org.mk.badam7.database.dao.PlayerCurrentGameInstanceDAO;
import org.mk.badam7.database.entity.CardEntity;
import org.mk.badam7.database.entity.GameEntity;
import org.mk.badam7.database.entity.HandEntity;
import org.mk.badam7.database.entity.PlayerCurrentGameInstanceEntity;
import org.mk.badam7.database.enums.GameStatus;
import org.mk.badam7.database.enums.GameType;
import org.mk.badam7.database.enums.HandStatus;
import org.mk.badam7.gamecore.common.Badam7Util;
import org.mk.badam7.gamecore.common.CardShuffler;
import org.mk.badam7.gamecore.hand.HandService;
import org.mk.badam7.gamecore.library.Badam7Constants;
import org.mk.badam7.gamecore.playercurrenthand.PlayerCurrentHandCardService;
import org.mk.badam7.gamedto.game.GameDTO;
import org.mk.badam7.gamedto.game.GameDetailsDTO;
import org.mk.badam7.gamedto.game.GameInDTO;
import org.mk.badam7.gamedto.game.GameUpdateDTO;
import org.mk.badam7.gamedto.hand.HandDTO;
import org.mk.badam7.gamedto.playercurrenthand.PlayerCurrentHandCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService
{

    @Autowired
    private GameDAO gameDAO;

    @Autowired
    private PlayerCurrentGameInstanceDAO playerCurrentGameInstanceDAO;

    @Autowired
    private CardDAO cardDAO;

    @Autowired
    private HandDAO handDAO;

    @Autowired
    private HandService handService;

    @Autowired
    private GameResultService gameResultService;

    @Autowired
    private PlayerCurrentHandCardService playerCurrentHandCardService;

    @Autowired
    private Badam7Util badam7Util;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public List<GameDTO> getGamesToJoin()
    {
        List<GameEntity> activeGames = gameDAO.findByStatus(GameStatus.WAITING_FOR_PLAYERS.getCode());
        return badam7Util.mapListOfEnitiesToDTOs(activeGames, GameDTO.class);
    }

    @Override
    public GameDTO createGame(GameInDTO gameInDTO)
    {
        validateInput(gameInDTO);

        GameEntity gameEntity = createEntityFromDTO(gameInDTO);
        gameEntity = gameDAO.save(gameEntity);

        GameDTO gameDTO = dozerMapper.map(gameEntity, GameDTO.class);
        return gameDTO;
    }

    @Override
    public GameDTO updateGame(Integer gameId, GameUpdateDTO gameUpdateDTO)
    {
        validateUpdateOperation(gameId, gameUpdateDTO);
        GameEntity gameEntity = gameDAO.findOne(gameId);
        gameEntity.setStatus(gameUpdateDTO.getStatus());
        gameEntity = gameDAO.save(gameEntity);
        GameDTO gameDTO = dozerMapper.map(gameEntity, GameDTO.class);
        return gameDTO;
    }

    @Override
    public GameDetailsDTO getById(Integer gameId)
    {
        GameEntity gameEntity = badam7Util.getGameFromId(gameId);
        List<PlayerCurrentGameInstanceEntity> players = playerCurrentGameInstanceDAO.findByGameEntity(gameEntity);
        Integer hand = 0;
        HandEntity handEntity = handDAO.findByGameEntityAndStatus(gameEntity, HandStatus.IN_PROGRESS.getStatusCode());
        if (handEntity != null)
        {
            hand = handEntity.getId();
        }
        GameDetailsDTO gameDTO = createGameDetailsDTO(players, hand);
        return gameDTO;
    }

    @Override
    public GameDetailsDTO startHand(Integer gameId)
    {
        GameEntity gameEntity = badam7Util.getGameFromId(gameId);
        GameDetailsDTO gameDetailsDTO;
        if (canHandStart(gameEntity))
        {
            gameDetailsDTO = startHand(gameId, gameEntity);
        }
        else
        {
            throw new IllegalArgumentException("Game cannot be started");
        }
        return gameDetailsDTO;
    }

    @Override
    public GameDTO endGame(Integer gameId)
    {
        GameEntity gameEntity = badam7Util.getGameFromId(gameId);
        gameResultService.createGameResults(gameEntity);
        gameEntity.setStatus(GameStatus.COMPLETED.getCode());
        gameEntity = gameDAO.save(gameEntity);
        return dozerMapper.map(gameEntity, GameDTO.class);
    }

    private GameDetailsDTO startHand(Integer gameId, GameEntity gameEntity)
    {
        List<PlayerCurrentGameInstanceEntity> players = playerCurrentGameInstanceDAO.findByGameEntity(gameEntity);
        HandDTO hand = handService.createHand(gameId);
        List<CardEntity> cards = cardDAO.findAll();
        cards = CardShuffler.shuffleCards(cards);
        dealCards(cards, players, hand);
        gameEntity.setStatus(GameStatus.IN_PROGRESS.getCode());
        gameEntity = gameDAO.save(gameEntity);
        GameDetailsDTO gameDetailsDTO = createGameDetailsDTO(players, hand.getId());
        return gameDetailsDTO;
    }

    private List<Integer> getPlayerIds(List<PlayerCurrentGameInstanceEntity> players)
    {
        List<Integer> playerIds = new ArrayList<Integer>();
        for (PlayerCurrentGameInstanceEntity player : players)
        {
            playerIds.add(player.getId());
        }
        return playerIds;
    }

    private void validateUpdateOperation(Integer gameId, GameUpdateDTO gameUpdateDTO)
    {
        if (gameId == null)
        {
            throw new IllegalArgumentException("GameId cannot be null");
        }

        GameEntity gameEntity = badam7Util.getGameFromId(gameId);
        Integer gameStatus = gameEntity.getStatus();
        if (!canGameBeUpdated(gameStatus))
        {
            throw new IllegalArgumentException("Game cannot be updated");
        }

        List<Integer> listOfGameStatuses = GameStatus.getAllStatuses();
        if (!listOfGameStatuses.contains(gameStatus))
        {
            throw new IllegalArgumentException("Invalid Update Status");
        }

        if (gameStatus > gameUpdateDTO.getStatus())
        {
            throw new IllegalArgumentException("Invalid Status");
        }
    }

    private boolean canHandStart(GameEntity gameEntity)
    {
        int gameStatus = gameEntity.getStatus();
        if (gameStatus == GameStatus.WAITING_TO_START.getCode())
        {
            return true;
        }
        return false;
    }

    private boolean canGameBeUpdated(Integer gameStatus)
    {
        if (gameStatus == GameStatus.COMPLETED.getCode())
        {
            return false;
        }

        if (gameStatus == GameStatus.ABANDONED.getCode())
        {
            return false;
        }
        return true;
    }

    private void validateInput(GameInDTO gameInDTO)
    {
        if (gameInDTO == null)
        {
            throw new IllegalArgumentException("Input DTO null");
        }

        if (gameInDTO.getGameType() != GameType.BADAM7.getType())
        {
            throw new IllegalArgumentException("Incorrect Game Type");
        }

        if (gameInDTO.getNoOfPlayers() < Badam7Constants.MIN_NO_OF_PLAYERS)
        {
            throw new IllegalArgumentException("Incorrect no of players");
        }

        if (gameInDTO.getNoOfHands() > Badam7Constants.MAX_HANDS)
        {
            throw new IllegalArgumentException("Incorrect No of Hands");
        }

    }

    private void dealCards(List<CardEntity> cards, List<PlayerCurrentGameInstanceEntity> players, HandDTO hand)
    {
        int noOfPlayers = players.size() - 1;
        int currentPlayer = 0;
        for (CardEntity card : cards)
        {
            if (currentPlayer > noOfPlayers)
            {
                currentPlayer = 0;
            }
            PlayerCurrentGameInstanceEntity player = players.get(currentPlayer);
            PlayerCurrentHandCardDTO playerCurrentHandCardDTO = createPlayerCurrentHandCardDTO(hand, card, player);
            playerCurrentHandCardService.createPlayerCurrentHandCard(playerCurrentHandCardDTO);
            currentPlayer++;
        }

    }

    private PlayerCurrentHandCardDTO createPlayerCurrentHandCardDTO(HandDTO hand, CardEntity card, PlayerCurrentGameInstanceEntity player)
    {
        PlayerCurrentHandCardDTO playerCurrentHandCardDTO = new PlayerCurrentHandCardDTO();
        playerCurrentHandCardDTO.setCardId(card.getId());
        playerCurrentHandCardDTO.setHandId(hand.getId());
        playerCurrentHandCardDTO.setPlayerCurrentGameInstanceId(player.getId());
        return playerCurrentHandCardDTO;
    }

    private GameEntity createEntityFromDTO(GameInDTO gameInDTO)
    {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setGameType(gameInDTO.getGameType());
        gameEntity.setNoOfHands(gameInDTO.getNoOfHands());
        gameEntity.setNoOfPlayers(gameInDTO.getNoOfHands());
        gameEntity.setStatus(GameStatus.CREATED.getCode());

        return gameEntity;
    }

    private GameDetailsDTO createGameDetailsDTO(List<PlayerCurrentGameInstanceEntity> players, Integer handId)
    {
        GameDetailsDTO gameDetailsDTO = new GameDetailsDTO();
        gameDetailsDTO.setCurrentHandId(handId);
        List<Integer> playerIds = getPlayerIds(players);
        gameDetailsDTO.setPlayerIds(playerIds);
        return gameDetailsDTO;
    }

}
