package org.mk.badam7.game.player;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.PlayerDAO;
import org.mk.badam7.database.entity.Player;
import org.mk.badam7.database.enums.UserAuthorities;
import org.mk.badam7.gamecore.common.EmailSender;
import org.mk.badam7.gamedto.player.PlayerDTO;
import org.mk.badam7.gamedto.player.PlayerInDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PlayerCRUDServiceImpl implements PlayerCRUDService
{
    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private EmailSender emailSender;

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
        Player player = createPlayerEntityFromDTO(playerInDTO);
        player = playerDAO.save(player);
        sendVerificationMail(player.getEmail());
        PlayerDTO playerDTO = dozerMapper.map(player, PlayerDTO.class);

        return playerDTO;
    }

    private void validateInputDTO(PlayerInDTO playerInDTO)
    {
        String emailId = playerInDTO.getEmail();
        Player player = playerDAO.findByEmail(emailId);
        if (player != null)
        {
            throw new IllegalArgumentException("Email already registered");
        }

        String username = playerInDTO.getUsername();
        player = playerDAO.findByUsername(username);
        if (player != null)
        {
            throw new IllegalArgumentException("Username already used");
        }

    }

    private Player createPlayerEntityFromDTO(PlayerInDTO playerInDTO)
    {
        Player player = dozerMapper.map(playerInDTO, Player.class);
        player.setVerified(false);
        String encodedPassword = encodeUserPassword(playerInDTO.getUsername(), playerInDTO.getPassword());
        player.setPassword(encodedPassword);
        player.setRole(UserAuthorities.NOT_VERIFIED.getRole());
        return player;
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
