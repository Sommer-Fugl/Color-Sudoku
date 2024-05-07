package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.PlayerAccount;
import sk.tuke.gamestudio.service.PlayerAccountService;

@RestController
@RequestMapping("api/playerAccount")
public class PlayerAccountREST {
    @Autowired
    private PlayerAccountService playerAccountService;

    @PostMapping
    private void addPlayerAccount(@RequestBody PlayerAccount playerAccount) {
        this.playerAccountService.addPlayerAccount(playerAccount);
    }

    @GetMapping("/getlog/{game}/{playerName}/{password}")
    private PlayerAccount getLog(@PathVariable String game, @PathVariable String playerName, @PathVariable String password){
        return playerAccountService.getLog(game,playerName, password);
    }

    @GetMapping("/getplayeraccount/{game}/{playerName}")
    private PlayerAccount getPlayerAccount(@PathVariable String game, @PathVariable String playerName) {
        return playerAccountService.getPlayerAccount(game, playerName);
    }
}
