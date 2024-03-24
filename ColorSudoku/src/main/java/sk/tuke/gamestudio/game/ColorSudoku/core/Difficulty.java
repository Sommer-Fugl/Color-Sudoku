package sk.tuke.gamestudio.game.ColorSudoku.Core;

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