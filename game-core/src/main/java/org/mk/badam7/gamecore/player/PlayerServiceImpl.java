package org.mk.badam7.gamecore.player;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.PlayerDAO;
import org.mk.badam7.database.entity.PlayerEntity;
import org.mk.badam7.database.enums.UserAuthorities;
import org.mk.badam7.gamecore.common.Badam7Util;
import org.mk.badam7.gamecore.common.EmailSender;
import org.mk.badam7.gamedto.player.PlayerDTO;
import org.mk.badam7.gamedto.player.PlayerInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService
{
    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private Badam7Util badam7Util;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public PlayerDTO createPlayer(PlayerInDTO playerInDTO)
    {
        if (playerInDTO == null)
        {
            throw new IllegalArgumentException("dto cannot be null");
        }
        validateInputDTO(playerInDTO);
        PlayerEntity playerEntity = createPlayerEntityFromDTO(playerInDTO);
        playerEntity = playerDAO.save(playerEntity);
        sendVerificationMail(playerEntity.getEmail());
        PlayerDTO playerDTO = dozerMapper.map(playerEntity, PlayerDTO.class);

        return playerDTO;
    }

    @Override
    public PlayerDTO verifyPlayer(Integer playerId)
    {
        PlayerEntity playerEntity = playerDAO.findOne(playerId);
        if (playerEntity == null)
        {
            throw new IllegalArgumentException("Player does not exist");
        }
        playerEntity.setVerified(true);
        playerEntity.setRole(UserAuthorities.USER.getRole());
        playerEntity = playerDAO.save(playerEntity);
        PlayerDTO playerDTO = dozerMapper.map(playerEntity, PlayerDTO.class);
        return playerDTO;
    }

    @Override
    public PlayerDTO getPlayerById(Integer playerId)
    {
        PlayerEntity playerEntity = badam7Util.getPlayerFromId(playerId);
        PlayerDTO playerDTO = dozerMapper.map(playerEntity, PlayerDTO.class);
        return playerDTO;
    }

    private void validateInputDTO(PlayerInDTO playerInDTO)
    {
        String emailId = playerInDTO.getEmail();
        PlayerEntity playerEntity = playerDAO.findByEmail(emailId);
        if (playerEntity != null)
        {
            throw new IllegalArgumentException("Email already registered");
        }

        String username = playerInDTO.getUsername();
        playerEntity = playerDAO.findByUsername(username);
        if (playerEntity != null)
        {
            throw new IllegalArgumentException("Username already used");
        }

    }

    private PlayerEntity createPlayerEntityFromDTO(PlayerInDTO playerInDTO)
    {
        PlayerEntity playerEntity = dozerMapper.map(playerInDTO, PlayerEntity.class);
        playerEntity.setVerified(true);
        String encodedPassword = encodeUserPassword(playerInDTO.getUsername(), playerInDTO.getPassword());
        playerEntity.setPassword(encodedPassword);
        playerEntity.setRole(UserAuthorities.USER.getRole());
        return playerEntity;
    }

    private void sendVerificationMail(String email)
    {
        emailSender.sendMail(email);
    }

    private String encodeUserPassword(String username, String password)
    {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        return encoder.encodePassword(username, password);
    }

}