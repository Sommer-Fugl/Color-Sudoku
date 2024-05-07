package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.PlayerAccount;

public interface PlayerAccountService {
    void addPlayerAccount(PlayerAccount playerAccount) throws PlayerAccountException;
    void resetPlayerAccount() throws PlayerAccountException;
    PlayerAccount getPlayerAccount(String game, String playerName) throws PlayerAccountException;
    PlayerAccount getLog(String game, String playerName, String password) throws PlayerAccountException;
}