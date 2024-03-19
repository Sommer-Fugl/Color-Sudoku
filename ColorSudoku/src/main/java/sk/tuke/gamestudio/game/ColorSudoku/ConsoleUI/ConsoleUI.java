package main.java.sk.tuke.gamestudio.game.ColorSudoku.ConsoleUI;

import main.java.sk.tuke.gamestudio.entity.Comment;
import main.java.sk.tuke.gamestudio.entity.Rating;
import main.java.sk.tuke.gamestudio.entity.Score;
import main.java.sk.tuke.gamestudio.game.ColorSudoku.Player.Player;
import main.java.sk.tuke.gamestudio.game.ColorSudoku.core.*;
import main.java.sk.tuke.gamestudio.service.CommentServiceJDBC;
import main.java.sk.tuke.gamestudio.service.RatingServiceJDBC;
import main.java.sk.tuke.gamestudio.service.ScoreServiceJDBC;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private boolean playing = true;
    private Player player;
    private static final String gameName = "ColorSudoku";
    private Timestamp actualDate = new Timestamp(System.currentTimeMillis());
    private boolean justStarted = true;

    private static final String colorEnd = "\u001B[0m";
    private static final String PURPLE = "\u001B[95m";
    private static final String ORANGE = "\u001B[93m";
    private static final String Yellow = "\033[0;33m";
    private static final String CYAN = "\033[0;36m";

    public void play(){
        GameBoard board = new GameBoard();
        while(playing){
            if(justStarted) {
                board = StartedScreen(board);//showing start massage
                justStarted = false;
                player.setPlayerPoints(500);
            }
            else{
                create(board);
                board = HandleInput(board);
                board.checkBoardState();
                board.setState(GameState.FAILED);
                if(board.getState() == GameState.SOLVED || board.getState() == GameState.FAILED) {
                    board = askAfterGame(board);
                }
            }
        }
    }

    private void create(GameBoard board) {
        introduceColor();
        System.out.print(PURPLE+"    A   B   C   D   E   F   G   H   I\n" + colorEnd);
        System.out.println(ORANGE +"   ———————————————————————————————————" + colorEnd);
        for(int i=0; i<9; i++){
            System.out.print(PURPLE+(char) ('A' + i) + colorEnd + ORANGE+" |" + colorEnd);
            for(int ki=0; ki<9; ki++) {
                if(board.getCellBoard()[i][ki].getCurrentState() == CellState.EMPTY)
                    System.out.print(" " + ORANGE+"  |"+colorEnd);
                else
                    System.out.print(ColorCell.colorHandler(board.getCellBoard()[i][ki].getCurrentColor().ordinal())+ "   "
                            + colorEnd + ORANGE + "|" + colorEnd);
            }
            System.out.print(ORANGE +"\n   ———————————————————————————————————"+ colorEnd);
            System.out.println();
        }
        playerStats();
        showAverageRate();
    }

    private void showAverageRate(){
        RatingServiceJDBC ratingServiceJDBC = new RatingServiceJDBC();
        System.out.print(CYAN + "Average game rating is " + colorEnd);
        int avRate = ratingServiceJDBC.getAverageRating(gameName);

        for(int i=0; i< avRate; i++){
            System.out.print(Yellow + "★" + colorEnd);
        }

        for(int i=avRate; i< 5; i++){
            System.out.print(Yellow + "☆" + colorEnd);
        }

        ratingServiceJDBC.getAverageRating(gameName);
        System.out.println();
    }

    private void playerStats(){
        System.out.print("   "+ORANGE+"|"+player.getPlayerName() + "|" + colorEnd);
        for(int i=player.getPlayerName().length(); i<35-7; i++)
            System.out.print(" ");
        System.out.print(CYAN + "|"+ player.getPlayerPoints() + "|" + colorEnd);
        System.out.println(ORANGE +"\n   ———————————————————————————————————"+ colorEnd);
    }

    private String writePlayerName(){
        System.out.print(ORANGE+"Write your name: "+colorEnd);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void introduceColor(){
        for(int i=0; i<9; i++){
            System.out.print(ColorCell.colorHandler(i) + "   " + colorEnd + " — " + i +" ");
            if(i == 4)
                System.out.println();
        }
        System.out.println();
    }

    private GameBoard HandleInput(GameBoard board){

        Scanner scanner = new Scanner(System.in);

        System.out.print(ORANGE+"\nChoose the row: " + colorEnd);
        String row = scanner.nextLine();
        while (!row.matches(".*[a-iA-I].*")) {
            System.out.print(ORANGE+"\nA-I letter needed! Choose the row: "+colorEnd);
            row = scanner.nextLine();
        }

        System.out.print(ORANGE + "\nChoose the column: " + colorEnd);
        String column = scanner.nextLine();
        while (!column.matches(".*[a-iA-I].*")) {
            System.out.print(CYAN+"\nA-I letter needed! Choose the column: "+colorEnd);
            column = scanner.nextLine();
        }

        System.out.print(ORANGE + "\nChoose the color: " + colorEnd);
        String color = scanner.nextLine();
        while(!color.matches("[0-8]+")){
            System.out.print(CYAN+"\nDecimal needed! Choose the color: "+colorEnd);
            color = scanner.nextLine();
        }

        int x = stringToInt(row);
        int y = stringToInt(column);
        int colInt = color.charAt(0)-'0';

        if(board.isAbleToSet(colInt,x,y)&& board.getCellBoard()[x][y].getCurrentState() != CellState.GENERATED){
            board.getCellBoard()[x][y].fillCellWithColor(ColorCell.getColor(colInt));//changes cell state and color
            handlePoints(false, board);
        } else if (colInt > 8){
            System.out.println(ORANGE+"You have chosen not a color! Pick another one in range [0-8]" + colorEnd);
        }
        else {
            handlePoints(true, board);
        }

        return board;
    }

    private void handlePoints(Boolean toDecrease, GameBoard board){
        if(toDecrease)
            player.setPlayerPoints(player.getPlayerPoints()-50);
        else
            player.setPlayerPoints(player.getPlayerPoints()+150);

        if(player.getPlayerPoints() <= 0){
            player.setPlayerPoints(0);//fails game if you get 0 points
            board.setState(GameState.FAILED);
        }
    }

    private void showHallOfFame(){
        ScoreServiceJDBC scoreServiceJDBC = new ScoreServiceJDBC();
        List<Score> topScores = scoreServiceJDBC.getTopScores("ColorSudoku");
        System.out.println(ORANGE+"Hall of Fame:"+ colorEnd);
        for(Score score: topScores){
            System.out.println(CYAN+ score.getPlayer() +colorEnd+ " with a score: " + ORANGE + score.getPoints() + colorEnd);
        }
        System.out.println();
    }

    private GameBoard askAfterGame(GameBoard board){
        create(board);

        if(board.getState() == GameState.FAILED){
            System.out.println(ORANGE + "It's a pity that you lose! Next time try harder, think better!" + colorEnd);
        }
        else
            System.out.println(CYAN + "We congratulate you with the winning of the game! But you still didn't prove that you are the smarter!" + colorEnd);

        System.out.print(CYAN + "Would you like to see Hall of fame?" + colorEnd + ORANGE + " [y/n]" + colorEnd);
        boolean service = askForService();
        if(service)
            showHallOfFame();

        System.out.print(CYAN+ "Would you like to see comments?" + colorEnd + ORANGE+ "[y/n]" + colorEnd);
        service = askForService();
        if(service)
            showComments();

        System.out.println(CYAN+"Thank you for playing Color Sudoku!)" + colorEnd);
        System.out.print(CYAN+"Would you like to play again the game?"+ colorEnd + ORANGE+"[y/n]"+colorEnd);

        service = askForService();

        if(service){
            playing = true;
            justStarted = true;
            board = new GameBoard();
        } else{
            writeComment();
            rateGame();
            playing = false;
            System.out.println(ORANGE+"Thank you for playing my game!Have a good day!"+colorEnd);
        }

        Score score = new Score(gameName, player.getPlayerName(), player.getPlayerPoints(), actualDate);
        ScoreServiceJDBC scoreServiceJDBC = new ScoreServiceJDBC();
        scoreServiceJDBC.addScore(score);

        return board;
    }

    private void showComments(){
        CommentServiceJDBC commentServiceJDBC = new CommentServiceJDBC();
        List<Comment> comments = commentServiceJDBC.getComments(gameName);
        System.out.println(CYAN + "Players comments:" + colorEnd);
        for(Comment comment: comments){
            System.out.println(ORANGE+comment.toString()+colorEnd);
        }
        System.out.println();
    }

    private boolean askForService(){
        Scanner scanner = new Scanner(System.in);
        String sc = scanner.nextLine();
        while(!sc.matches("^(?:yes|no|y|n|Y|N)$")){
            System.out.print(ORANGE+"\ny/n needed!"+colorEnd);
            sc = scanner.nextLine();
        }
        return sc.matches("^(?:yes|y|Y)$");
    }

    private void rateGame(){
        System.out.print(ORANGE+"Would you like to rate the game(yes/no)? " + colorEnd);
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine().toLowerCase();
        while (!answer.matches("^(?:yes|no|y|n)$")) {
            System.out.println(ORANGE+"Wrong command! Type (yes/no)"+colorEnd);
            answer = scanner.nextLine().toLowerCase();
        }
        if (answer.matches("^(?:yes|y)$"))
        {
            System.out.print(CYAN+"Rate our game(0-5): "+colorEnd);
            answer = scanner.nextLine();
            while(!answer.matches("[0-5]+")){
                System.out.println(ORANGE+"Wrong range! Rate is in range 0-5"+colorEnd);
                answer = scanner.nextLine();
            }
            int rate = answer.charAt(0)-48;
            Rating rating = new Rating(player.getPlayerName(), gameName, rate,actualDate);
            RatingServiceJDBC ratingServiceJDBC = new RatingServiceJDBC();
            ratingServiceJDBC.setRating(rating);
        }
        else
            System.out.println(CYAN+"We are waiting for comment next time!"+colorEnd);
    }

    private void writeComment() {
        System.out.print(ORANGE+"Would you like to leave a comment(yes/no)?: " +colorEnd);
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine().toLowerCase();
        while (!answer.matches("^(?:yes|no|y|n)$")) {
            System.out.println(CYAN+"Wrong command! Type (yes/no)"+colorEnd);
            answer = scanner.nextLine().toLowerCase();
        }
        if (answer.matches("^(?:yes|y)$"))
        {
            System.out.print(ORANGE+"Write your comment: "+colorEnd);
            String com = scanner.nextLine();
            Comment comment = new Comment(player.getPlayerName(),gameName, com, actualDate);
            CommentServiceJDBC commentServiceJDBC = new CommentServiceJDBC();
            commentServiceJDBC.addComment(comment);
        }
        else
            System.out.println(ORANGE+"We are waiting for comment next time!"+ colorEnd);
    }

    private GameBoard StartedScreen(GameBoard board) throws MatchException{
        System.out.println(ORANGE+"Welcome, to the Color Sudoku!" + colorEnd);
        String  playerName = writePlayerName();
        player = new Player(playerName);
        while(board.getGameDifficulty() == null){
            System.out.println(ORANGE+"0 — EASY " + "1 — MEDIUM " + "2 — HARD " + "3 — EXPERT"+colorEnd);
            System.out.print(CYAN+"\nChoose difficulty: "+colorEnd);
            Scanner sc = new Scanner(System.in);
            String difNum = sc.nextLine();
            while(!difNum.matches("[0-3]+")) {
                System.out.println(ORANGE+"You've chosen wrong difficulty!It's in range 0 — 3"+ colorEnd);
                difNum = sc.nextLine();
            }
            board = new GameBoard(Difficulty.getDifficulty(difNum.charAt(0)-48));
        }
        return board;
    }
    private int stringToInt(String string){
        return (int)string.toUpperCase().charAt(0)-65;
    }
}