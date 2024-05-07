package sk.tuke.gamestudio.service;

public class PlayerAccountException extends RuntimeException{
    public PlayerAccountException(String string){
        super(string);
    }
    public PlayerAccountException(String string, Throwable throwable){
        super(string, throwable);
    }
}
