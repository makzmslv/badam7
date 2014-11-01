package org.mk.badam7.game.player;

import org.dozer.Mapper;
import org.mk.badam7.database.dao.PlayerDAO;
import org.mk.badam7.database.entity.Player;
import org.mk.badam7.database.enums.UserAuthorities;
import org.mk.badam7.gamedto.player.PlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerCRUDServiceImpl implements PlayerCRUDService
{
    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerInDTO)
    {
        if (playerInDTO == null)
        {
            throw new IllegalArgumentException("dto cannot be null");
        }

        Player player = dozerMapper.map(playerInDTO, Player.class);
        player.setVerified(false);
        player.setRole(UserAuthorities.NOT_VERIFIED.getRole());
        player = playerDAO.save(player);
        sendVerificationMail(player.getEmail());
        PlayerDTO playerDTO = dozerMapper.map(player, PlayerDTO.class);

        return playerDTO;
    }

    private void sendVerificationMail(String email)
    {
        // TODO Auto-generated method stub

    }
}
