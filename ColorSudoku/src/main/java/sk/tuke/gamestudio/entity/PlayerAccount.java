package sk.tuke.gamestudio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

import java.io.Serializable;

@Entity
@NamedQuery(
        name = "PlayerAccount.getAccount",
        query = "SELECT pa FROM PlayerAccount pa WHERE pa.game =: game AND pa.name =: name"
)
@NamedQuery(
        name = "PlayerAccount.getLog",
        query = "SELECT pa FROM PlayerAccount pa WHERE pa.game =: game AND pa.name =: name AND pa.password =: password"
)
@NamedQuery(
        name = "PlayerAccount.reset",
        query = "DELETE FROM PlayerAccount"
)
public class PlayerAccount implements Serializable {
    @Id
    @GeneratedValue
    private int ident;
    private String name;
    private String password;
    private String game = "ColorSudoku";

    public PlayerAccount(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public PlayerAccount() {
    }

    public int getIdent() {
        return ident;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}