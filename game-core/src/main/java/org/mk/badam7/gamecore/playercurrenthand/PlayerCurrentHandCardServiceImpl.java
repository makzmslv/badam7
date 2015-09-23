package org.mk.badam7.gamecore.playercurrenthand;

import java.util.List;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.GameDetailsDAO;
import org.mk.badam7.database.dao.HandCurrentCardDAO;
import org.mk.badam7.database.dao.PlayerCurrentGameInstanceDAO;
import org.mk.badam7.database.dao.PlayerCurrentHandCardDAO;
import org.mk.badam7.database.entity.CardEntity;
import org.mk.badam7.database.entity.GameDetailsEntity;
import org.mk.badam7.database.entity.HandCurrentCardEntity;
import org.mk.badam7.database.entity.HandEntity;
import org.mk.badam7.database.entity.PlayerCurrentGameInstanceEntity;
import org.mk.badam7.database.entity.PlayerCurrentHandCardEntity;
import org.mk.badam7.database.entity.PlayerEntity;
import org.mk.badam7.database.enums.PlayerCurrentHandCardStatus;
import org.mk.badam7.database.enums.Suite;
import org.mk.badam7.gamecore.common.Badam7Util;
import org.mk.badam7.gamecore.hand.HandCurrentCardService;
import org.mk.badam7.gamecore.hand.HandService;
import org.mk.badam7.gamecore.library.Badam7Constants;
import org.mk.badam7.gamedto.hand.HandCurrentCardDTO;
import org.mk.badam7.gamedto.playercurrenthand.PlayerCurrentHandCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerCurrentHandCardServiceImpl implements PlayerCurrentHandCardService
{
    @Autowired
    private PlayerCurrentHandCardDAO playerCurrentHandCardDAO;

    @Autowired
    private HandCurrentCardDAO handCurrentCardDAO;

    @Autowired
    private PlayerCurrentGameInstanceDAO playerCurrentGameInstanceDAO;

    @Autowired
    private GameDetailsDAO gameDetailsDAO;

    @Autowired
    private HandCurrentCardService handCurrentCardService;

    @Autowired
    private HandService handService;

    @Autowired
    private Badam7Util badam7Util;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public PlayerCurrentHandCardDTO createPlayerCurrentHandCard(PlayerCurrentHandCardDTO playerCurrentHandCardDTO)
    {
        PlayerCurrentHandCardEntity playerCurrentHandCardEntity = createPlayerCurrentHandCardEntity(playerCurrentHandCardDTO);
        playerCurrentHandCardEntity = playerCurrentHandCardDAO.save(playerCurrentHandCardEntity);
        return dozerMapper.map(playerCurrentHandCardEntity, PlayerCurrentHandCardDTO.class);
    }

    @Override
    public PlayerCurrentHandCardDTO playCard(Integer playerId, Integer playerCurrentHandCardId, boolean skipChance)
    {
        if (skipChance)
        {
            if (canChanceBeSkipped(playerId, playerCurrentHandCardId))
            {
                GameDetailsEntity gameDetailsEntity = badam7Util.getGameDetailsFromGameId(playerCurrentHandCardId);
                updateGameDetails(gameDetailsEntity);
                return new PlayerCurrentHandCardDTO();
            }
            else
            {
                throw new IllegalArgumentException("Cards available to play");
            }
        }
        PlayerCurrentHandCardEntity playerCurrentHandCardEntity = playerCurrentHandCardDAO.findOne(playerCurrentHandCardId);
        if (playerCurrentHandCardEntity == null)
        {
            throw new IllegalArgumentException("Input DTO null");
        }
        GameDetailsEntity gameDetailsEntity = gameDetailsDAO.findByGameEntity(playerCurrentHandCardEntity.getHandEntity().getGameEntity());
        if (playerId != gameDetailsEntity.getCurrentPlayerId())
        {
            throw new IllegalArgumentException("Not your chance");
        }
        if (canCardBePlaced(playerCurrentHandCardEntity))
        {
            HandCurrentCardDTO handCurrentCardDTO = new HandCurrentCardDTO();
            handCurrentCardDTO.setCardId(playerCurrentHandCardEntity.getCardEntity().getId());
            handCurrentCardDTO.setHandId(playerCurrentHandCardEntity.getHandEntity().getId());
            handCurrentCardDTO.setPlayerCurrentGameInstanceId(playerCurrentHandCardEntity.getPlayerCurrentGameInstanceEntity().getId());
            handCurrentCardService.createHandCurrentCard(handCurrentCardDTO);
            playerCurrentHandCardEntity.setStatus(PlayerCurrentHandCardStatus.PLACED_ON_TABLE.getStatusCode());
            playerCurrentHandCardDAO.save(playerCurrentHandCardEntity);
            if (hasPlayerWonHand(playerCurrentHandCardEntity))
            {
                handService.endHand(playerCurrentHandCardEntity.getHandEntity().getId());
            }
            updateGameDetails(gameDetailsEntity);
        }
        else
        {
            throw new IllegalArgumentException("card cannot be placed");
        }
        return dozerMapper.map(playerCurrentHandCardEntity, PlayerCurrentHandCardDTO.class);
    }

    @Override
    public List<PlayerCurrentHandCardDTO> getAllPlayerCurrentHandCards(Integer currentHandId, Integer playerCurrentGameInstanceId)
    {
        HandEntity handEntity = badam7Util.getHandFromId(currentHandId);
        PlayerCurrentGameInstanceEntity playerCurrentGameInstanceEntity = badam7Util.getPlayerCurrentGameInstanceFromId(playerCurrentGameInstanceId);
        List<PlayerCurrentHandCardEntity> playerCurrentCards = playerCurrentHandCardDAO.findByHandEntityAndPlayerCurrentGameInstanceEntityAndStatus(handEntity, playerCurrentGameInstanceEntity,
                PlayerCurrentHandCardStatus.IN_HAND.getStatusCode());
        return badam7Util.mapListOfEnitiesToDTOs(playerCurrentCards, PlayerCurrentHandCardDTO.class);
    }

    private PlayerCurrentHandCardEntity createPlayerCurrentHandCardEntity(PlayerCurrentHandCardDTO playerCurrentHandCardDTO)
    {
        PlayerCurrentHandCardEntity playerCurrentHandCardEntity = new PlayerCurrentHandCardEntity();
        playerCurrentHandCardEntity.setCardEntity(badam7Util.getCardFromId(playerCurrentHandCardDTO.getCardId()));
        playerCurrentHandCardEntity.setHandEntity(badam7Util.getHandFromId(playerCurrentHandCardDTO.getHandId()));
        playerCurrentHandCardEntity.setPlayerCurrentGameInstanceEntity(badam7Util.getPlayerCurrentGameInstanceFromId(playerCurrentHandCardDTO.getPlayerCurrentGameInstanceId()));
        playerCurrentHandCardEntity.setStatus(PlayerCurrentHandCardStatus.IN_HAND.getStatusCode());
        return playerCurrentHandCardEntity;
    }

    private boolean canCardBePlaced(PlayerCurrentHandCardEntity playerCurrentHandCardEntity)
    {
        CardEntity cardEntity = playerCurrentHandCardEntity.getCardEntity();
        if (isItTheFirstCard(playerCurrentHandCardEntity.getHandEntity()))
        {
            if (isIt7OfHearts(cardEntity))
            {
                return true;
            }
        }
        else
        {
            Integer maxCardValue = handCurrentCardDAO.getByCardSuiteAndCardValueMax(cardEntity.getSuite());
            Integer minCardValue = handCurrentCardDAO.getByCardSuiteAndCardValueMin(cardEntity.getSuite());
            if (maxCardValue == null && minCardValue == null)
            {
                return true;
            }
            else
            {
                if (maxCardValue != null)
                {
                    maxCardValue = maxCardValue + 1;
                }
                else
                {
                    maxCardValue = 0;
                }
                if (minCardValue != null)
                {
                    minCardValue = minCardValue - 1;
                }
                else
                {
                    minCardValue = 0;
                }
            }

            if (cardEntity.getValue() == maxCardValue || cardEntity.getValue() == minCardValue)
            {
                return true;
            }
        }
        return false;
    }

    private boolean canChanceBeSkipped(Integer playerId, Integer gameId)
    {
        boolean canChanceBeSkipped = true;
        PlayerEntity playerEntity = badam7Util.getPlayerFromId(playerId);
        GameDetailsEntity gameDetailsEntity = badam7Util.getGameDetailsFromGameId(gameId);
        HandEntity handEntity = badam7Util.getHandFromId(gameDetailsEntity.getCurrentHandId());
        if (isItTheFirstCard(handEntity))
        {
            return false;
        }
        PlayerCurrentGameInstanceEntity playerCurrentGameInstanceEntity = playerCurrentGameInstanceDAO.findByGameEntityAndPlayerEntity(gameDetailsEntity.getGameEntity(), playerEntity);
        for (String suite : Suite.getAllSuites())
        {
            Integer nextCardValueMax = handCurrentCardDAO.getByCardSuiteAndCardValueMax(suite);
            if (nextCardValueMax != null)
            {
                nextCardValueMax++;
                PlayerCurrentHandCardEntity playerCurrentCard = playerCurrentHandCardDAO.getByHandEntityAndPlayerAndCardSuiteAndCardValue(handEntity, playerCurrentGameInstanceEntity, suite,
                        nextCardValueMax);
                if (playerCurrentCard != null)
                {
                    canChanceBeSkipped = false;
                    break;
                }
            }
            else
            {
                PlayerCurrentHandCardEntity playerCurrentCard = playerCurrentHandCardDAO.getByHandEntityAndPlayerAndCardSuiteAndCardValue(handEntity, playerCurrentGameInstanceEntity, suite, 7);
                if (playerCurrentCard != null)
                {
                    canChanceBeSkipped = false;
                    break;
                }
            }
            Integer nextCardValueMin = handCurrentCardDAO.getByCardSuiteAndCardValueMin(suite);
            if (nextCardValueMin != null)
            {
                nextCardValueMin--;
                PlayerCurrentHandCardEntity playerCurrentCard = playerCurrentHandCardDAO.getByHandEntityAndPlayerAndCardSuiteAndCardValue(handEntity, playerCurrentGameInstanceEntity, suite,
                        nextCardValueMin);
                if (playerCurrentCard != null)
                {
                    canChanceBeSkipped = false;
                    break;
                }
            }
            else
            {
                PlayerCurrentHandCardEntity playerCurrentCard = playerCurrentHandCardDAO.getByHandEntityAndPlayerAndCardSuiteAndCardValue(handEntity, playerCurrentGameInstanceEntity, suite, 7);
                if (playerCurrentCard != null)
                {
                    canChanceBeSkipped = false;
                    break;
                }
            }
        }
        return canChanceBeSkipped;
    }

    private boolean isItTheFirstCard(HandEntity handEntity)
    {
        List<HandCurrentCardEntity> handCurrentCardsCardEntities = handCurrentCardDAO.findByHandEntity(handEntity);
        if (handCurrentCardsCardEntities.isEmpty())
        {
            return true;
        }
        return false;
    }

    private boolean isIt7OfHearts(CardEntity cardEntity)
    {
        if (cardEntity.getSuite().equals("H") && cardEntity.getValue() == 7)
        {
            return true;
        }
        return false;
    }

    private boolean hasPlayerWonHand(PlayerCurrentHandCardEntity playerCurrentHandCardEntity)
    {
        HandEntity handEntity = badam7Util.getHandFromId(playerCurrentHandCardEntity.getHandEntity().getId());
        PlayerCurrentGameInstanceEntity playerCurrentGameInstanceEntity = badam7Util.getPlayerCurrentGameInstanceFromId(playerCurrentHandCardEntity.getPlayerCurrentGameInstanceEntity().getId());
        List<PlayerCurrentHandCardEntity> playerCurrentCards = playerCurrentHandCardDAO.findByHandEntityAndPlayerCurrentGameInstanceEntityAndStatus(handEntity, playerCurrentGameInstanceEntity,
                PlayerCurrentHandCardStatus.IN_HAND.getStatusCode());
        if (playerCurrentCards.isEmpty())
        {
            return true;
        }
        return false;
    }

    private void updateGameDetails(GameDetailsEntity gameDetailsEntity)
    {
        List<PlayerCurrentGameInstanceEntity> players = playerCurrentGameInstanceDAO.findByGameEntity(gameDetailsEntity.getGameEntity());
        Integer nextPlayerId = getNextPlayerId(gameDetailsEntity.getCurrentPlayerId(), players);
        gameDetailsEntity.setCurrentPlayerId(nextPlayerId);
        gameDetailsDAO.save(gameDetailsEntity);
    }

    private Integer getNextPlayerId(Integer currentPlayerId, List<PlayerCurrentGameInstanceEntity> players)
    {
        Integer nextPlayerId = 0;
        Integer currentPlayerNo = 0;
        for (PlayerCurrentGameInstanceEntity player : players)
        {
            if (player.getPlayer().getId() == currentPlayerId)
            {
                currentPlayerNo = player.getPlayerNo();
            }
        }
        Integer nextPlayerNo = Badam7Constants.nextPlayerMap.get(currentPlayerNo);
        for (PlayerCurrentGameInstanceEntity player : players)
        {
            if (player.getPlayerNo() == nextPlayerNo)
            {
                nextPlayerId = player.getPlayer().getId();
            }
        }
        return nextPlayerId;
    }

}
