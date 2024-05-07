package sk.tuke.gamestudio.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.PlayerAccount;

@Transactional
public class PlayerAccountServiceJPA implements PlayerAccountService{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addPlayerAccount(PlayerAccount playerAccount) throws PlayerAccountException {
            entityManager.merge(playerAccount);
    }

    @Override
    public void resetPlayerAccount() throws PlayerAccountException {
        entityManager.createNamedQuery("PlayerAccount.reset").executeUpdate();
    }

    @Override
    public PlayerAccount getPlayerAccount(String game, String playerName) throws PlayerAccountException {
        var vari = entityManager.createNamedQuery("PlayerAccount.getAccount").setParameter("game", game).setParameter("name", playerName).getResultList().stream().findFirst().orElse(null);
        return (PlayerAccount) vari;
    }

    @Override
    public PlayerAccount getLog(String game, String playerName, String password) throws PlayerAccountException {
        return (PlayerAccount) entityManager.createNamedQuery("PlayerAccount.getLog")
                .setParameter("game", game)
                .setParameter("name", playerName)
                .setParameter("password", password)
                    .getResultList()
                    .stream()
                        .findFirst().orElse(null);
    }
}
