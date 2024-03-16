package main.java.sk.tuke.gamestudio.game.ColorSudoku.core;
public enum Difficulty {
    EASY, MEDIUM, HARD, EXPERT;
    public static Difficulty getDifficulty(int code){
        for(Difficulty d: Difficulty.values()){
            if(d.ordinal() == code){
                return d;
            }
        }
        return null;
    }
}