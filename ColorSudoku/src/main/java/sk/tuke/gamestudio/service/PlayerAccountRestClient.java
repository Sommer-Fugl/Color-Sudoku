package sk.tuke.gamestudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.PlayerAccount;

public class PlayerAccountRestClient implements PlayerAccountService {
    final String url = "http://localhost:8080/api/playerAccount";

    @Autowired
    RestTemplate restTemplate;

    @Override
    public void addPlayerAccount(PlayerAccount playerAccount) throws PlayerAccountException {
        restTemplate.postForEntity(url, playerAccount, PlayerAccount.class);
    }

    @Override
    public void resetPlayerAccount() throws PlayerAccountException {
        throw new UnsupportedOperationException("Not supported via web service");
    }

    @Override
    public PlayerAccount getPlayerAccount(String game, String playerName) throws PlayerAccountException {
        return restTemplate.getForEntity(url + "/getplayeraccount/" + game + "/" + playerName, PlayerAccount.class).getBody();
    }

    @Override
    public PlayerAccount getLog(String game, String playerName, String password) throws PlayerAccountException {
        return restTemplate.getForEntity(url + "/getlog/" + game + "/" + playerName + "/" + password, PlayerAccount.class).getBody();
    }
}