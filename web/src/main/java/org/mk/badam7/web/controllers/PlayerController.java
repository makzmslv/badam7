package org.mk.badam7.web.controllers;

import java.util.List;

import org.mk.badam7.gamecore.PlayerCurrentGameInstanceService.PlayerCurrentGameInstanceService;
import org.mk.badam7.gamecore.player.PlayerService;
import org.mk.badam7.gamecore.playercurrenthand.PlayerCurrentHandCardService;
import org.mk.badam7.gamedto.player.PlayerDTO;
import org.mk.badam7.gamedto.player.PlayerInDTO;
import org.mk.badam7.gamedto.playercurrentgameinstance.PlayerCurrentGameInstanceDTO;
import org.mk.badam7.gamedto.playercurrenthand.PlayerCurrentHandCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;

@Api(value = "players", description = "players")
@Controller
@RequestMapping(value = "/players")
public class PlayerController
{
    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerCurrentGameInstanceService playerCurrentGameInstanceService;

    @Autowired
    private PlayerCurrentHandCardService playerCurrentHandCardService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public PlayerDTO createPlayer(@RequestBody PlayerInDTO playerInDTO)
    {
        return playerService.createPlayer(playerInDTO);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public PlayerDTO getById(@RequestParam String username)
    {
        return playerService.getByUsername(username);
    }

    @RequestMapping(value = "/playercurrentgameinstance", method = RequestMethod.POST)
    @ResponseBody
    public PlayerCurrentGameInstanceDTO joinGame(@RequestParam Integer id, @RequestParam Integer gameId)
    {
        return playerCurrentGameInstanceService.createPlayerCurrentGameInstance(gameId, id);
    }

    @RequestMapping(value = "/{id}/playercurrentgameinstance", method = RequestMethod.GET)
    @ResponseBody
    public List<PlayerCurrentHandCardDTO> getAllPlayerCurrentHandCards(@PathVariable Integer id, @RequestParam Integer currentHandId, @RequestParam Integer playerCurrentGameInstanceId)
    {
        return playerCurrentHandCardService.getAllPlayerCurrentHandCards(currentHandId, playerCurrentGameInstanceId);
    }

    @RequestMapping(value = "/{id}/playercurrentgameinstance", method = RequestMethod.PUT)
    @ResponseBody
    public PlayerCurrentHandCardDTO playCard(@PathVariable Integer id, @RequestParam Integer playerCurrentHandCardId)
    {
        return playerCurrentHandCardService.playCard(playerCurrentHandCardId);
    }

}
