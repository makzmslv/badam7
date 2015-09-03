package org.mk.badam7.gamecore.playercurrenthand;

import java.util.List;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.HandCurrentCardDAO;
import org.mk.badam7.database.dao.PlayerCurrentHandCardDAO;
import org.mk.badam7.database.entity.CardEntity;
import org.mk.badam7.database.entity.HandCurrentCardEntity;
import org.mk.badam7.database.entity.HandEntity;
import org.mk.badam7.database.entity.PlayerCurrentGameInstanceEntity;
import org.mk.badam7.database.entity.PlayerCurrentHandCardEntity;
import org.mk.badam7.database.enums.PlayerCurrentHandCardStatus;
import org.mk.badam7.gamecore.common.Badam7Util;
import org.mk.badam7.gamecore.hand.HandCurrentCardService;
import org.mk.badam7.gamecore.hand.HandService;
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
    public PlayerCurrentHandCardDTO playCard(Integer playerCurrentHandCardId)
    {
        PlayerCurrentHandCardEntity playerCurrentHandCardEntity = playerCurrentHandCardDAO.findOne(playerCurrentHandCardId);
        if (playerCurrentHandCardEntity == null)
        {
            throw new IllegalArgumentException("Input DTO null");
        }
        if (canCardBePlaced(playerCurrentHandCardEntity))
        {
            HandCurrentCardDTO handCurrentCardDTO = new HandCurrentCardDTO();
            handCurrentCardDTO.setCardId(playerCurrentHandCardEntity.getCardEntity().getId());
            handCurrentCardDTO.setHandId(playerCurrentHandCardEntity.getHandEntity().getId());
            handCurrentCardService.createHandCurrentCard(handCurrentCardDTO);
            playerCurrentHandCardEntity.setStatus(PlayerCurrentHandCardStatus.PLACED_ON_TABLE.getStatusCode());
            playerCurrentHandCardDAO.save(playerCurrentHandCardEntity);
            if (hasPlayerWonHand(playerCurrentHandCardEntity))
            {
                handService.endHand(playerCurrentHandCardEntity.getHandEntity().getId());
            }
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
        List<PlayerCurrentHandCardEntity> playerCurrentCards = playerCurrentHandCardDAO.findByHandEntityAndPlayerCurrentGameInstanceEntity(handEntity, playerCurrentGameInstanceEntity);
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
            Integer maxCardValue = handCurrentCardDAO.getByCardSuiteAndCardValueMax(cardEntity.getSuite()) + 1;
            Integer minCardValue = handCurrentCardDAO.getByCardSuiteAndCardValueMax(cardEntity.getSuite()) - 1;
            if (cardEntity.getValue() == maxCardValue || cardEntity.getValue() == minCardValue)
            {
                return true;
            }
        }
        return false;
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
        if (cardEntity.getSuite() == "H" && cardEntity.getValue() == 7)
        {
            return true;
        }
        return false;
    }

    private boolean hasPlayerWonHand(PlayerCurrentHandCardEntity playerCurrentHandCardEntity)
    {
        HandEntity handEntity = badam7Util.getHandFromId(playerCurrentHandCardEntity.getHandEntity().getId());
        PlayerCurrentGameInstanceEntity playerCurrentGameInstanceEntity = badam7Util.getPlayerCurrentGameInstanceFromId(playerCurrentHandCardEntity.getPlayerCurrentGameInstanceEntity().getId());
        List<PlayerCurrentHandCardEntity> playerCurrentCards = playerCurrentHandCardDAO.findByHandEntityAndPlayerCurrentGameInstanceEntity(handEntity, playerCurrentGameInstanceEntity);
        if (playerCurrentCards.isEmpty())
        {
            return true;
        }
        return false;
    }
}
