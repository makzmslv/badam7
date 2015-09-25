package org.mk.badam7.gamecore.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.CardDAO;
import org.mk.badam7.database.dao.GameDAO;
import org.mk.badam7.database.dao.GameDetailsDAO;
import org.mk.badam7.database.dao.HandCurrentCardDAO;
import org.mk.badam7.database.dao.HandDAO;
import org.mk.badam7.database.dao.PlayerCurrentGameInstanceDAO;
import org.mk.badam7.database.dao.PlayerCurrentHandCardDAO;
import org.mk.badam7.database.dao.ResultGameDAO;
import org.mk.badam7.database.dao.ResultHandDAO;
import org.mk.badam7.database.entity.CardEntity;
import org.mk.badam7.database.entity.GameDetailsEntity;
import org.mk.badam7.database.entity.GameEntity;
import org.mk.badam7.database.entity.GameResultEntity;
import org.mk.badam7.database.entity.HandEntity;
import org.mk.badam7.database.entity.HandResultEntity;
import org.mk.badam7.database.entity.PlayerCurrentGameInstanceEntity;
import org.mk.badam7.database.entity.PlayerCurrentHandCardEntity;
import org.mk.badam7.database.enums.GameStatus;
import org.mk.badam7.database.enums.GameType;
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
    private HandCurrentCardDAO handCurrentCardDAO;

    @Autowired
    private GameDetailsDAO gameDetailsDAO;

    @Autowired
    private PlayerCurrentHandCardDAO playerCurrentHandCardDAO;

    @Autowired
    private ResultHandDAO resultHandDAO;

    @Autowired
    private ResultGameDAO resultGameDAO;

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
        List<Integer> validStatuses = new ArrayList<Integer>();
        validStatuses.add(GameStatus.WAITING_FOR_PLAYERS.getCode());
        validStatuses.add(GameStatus.CREATED.getCode());
        List<GameEntity> activeGames = gameDAO.findByStatusIn(validStatuses);
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
        GameDetailsEntity gameDetailsEntity = badam7Util.getGameDetailsFromGameId(gameId);
        GameDetailsDTO gameDetailsDTO = new GameDetailsDTO();
        GameEntity gameEntity;
        if (gameDetailsEntity == null)
        {
            gameEntity = badam7Util.getGameFromId(gameId);
            gameDetailsDTO.setGameStatus(gameEntity.getStatus());
            gameDetailsDTO.setGameId(gameId);
            gameDetailsDTO.setCurrentHandId(0);
            gameDetailsDTO.setCurrentPlayerId(0);
        }
        else
        {
            gameDetailsDTO = dozerMapper.map(gameDetailsEntity, GameDetailsDTO.class);
            gameEntity = gameDetailsEntity.getGameEntity();
            HandEntity handEntity = badam7Util.getHandFromId(gameDetailsEntity.getCurrentHandId());
            HandResultEntity handResultEntity = resultHandDAO.findByHandEntityAndPointsIsMax(handEntity);
            if (handResultEntity != null)
            {
                gameDetailsDTO.setHandWinnerId(handResultEntity.getPlayerCurrentGameInstanceEntity().getPlayer().getId());
            }
            GameResultEntity gameResultEntity = resultGameDAO.findByGameEntityAndPosition(gameDetailsEntity.getGameEntity(), 1);
            if (gameResultEntity != null)
            {
                gameDetailsDTO.setGameWinnerId(gameResultEntity.getPlayerCurrentGameInstanceEntity().getPlayer().getId());
            }
        }
        List<PlayerCurrentGameInstanceEntity> players = playerCurrentGameInstanceDAO.findByGameEntity(gameEntity);
        Map<Integer, Integer> playerIds = getPlayerIds(players);
        gameDetailsDTO.setPlayerIds(playerIds);
        return gameDetailsDTO;
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
        GameDetailsDTO gameDetailsDTO = createGameDetailsEntity(gameEntity, players, hand.getId());
        return gameDetailsDTO;
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
        gameEntity.setNoOfPlayers(gameInDTO.getNoOfPlayers());
        gameEntity.setStatus(GameStatus.CREATED.getCode());

        return gameEntity;
    }

    private GameDetailsDTO createGameDetailsEntity(GameEntity gameEntity, List<PlayerCurrentGameInstanceEntity> players, Integer handId)
    {
        GameDetailsEntity gameDetails = badam7Util.getGameDetailsFromGameId(gameEntity.getId());
        if (gameDetails == null)
        {
            gameDetails = new GameDetailsEntity();
            gameDetails.setGameEntity(gameEntity);
        }
        gameDetails.setCurrentHandId(handId);
        gameDetails.setCurrentPlayerId(getFirstPlayerId(handId));
        gameDetails = gameDetailsDAO.save(gameDetails);
        GameDetailsDTO gameDetailsDTO = dozerMapper.map(gameDetails, GameDetailsDTO.class);
        Map<Integer, Integer> playerIds = getPlayerIds(players);
        gameDetailsDTO.setPlayerIds(playerIds);
        return gameDetailsDTO;
    }

    private Map<Integer, Integer> getPlayerIds(List<PlayerCurrentGameInstanceEntity> players)
    {
        Map<Integer, Integer> playerIds = new HashMap<Integer, Integer>();
        for (PlayerCurrentGameInstanceEntity player : players)
        {
            playerIds.put(player.getPlayer().getId(), player.getId());
        }
        return playerIds;
    }

    private Integer getFirstPlayerId(Integer handId)
    {
        HandEntity handEntity = badam7Util.getHandFromId(handId);
        CardEntity cardEntity = cardDAO.findOne(6);
        PlayerCurrentHandCardEntity playerCurrentCard = playerCurrentHandCardDAO.findByHandEntityAndCardEntity(handEntity, cardEntity);
        return playerCurrentCard.getPlayerCurrentGameInstanceEntity().getPlayer().getId();
    }
}
