package sk.tuke.gamestudio.server.Controller;

import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.game.ColorSudoku.Core.*;

@RestController
@RequestMapping("api/game")
public class GameBoardController {
    GameBoard gameBoard = new GameBoard(Difficulty.getDifficulty(2));

    @PostMapping
    public void initGame(@RequestBody int difficulty) {
        gameBoard = new GameBoard(Difficulty.getDifficulty(difficulty));
    }

    @GetMapping("/getBoard/{diff}")
    public GameBoard getBoard(@PathVariable String diff) {
        gameBoard = new GameBoard(Difficulty.getDifficulty(Integer.parseInt(diff)));
        return gameBoard;
    }

    @GetMapping("/getCell/{row}/{col}")
    public Cell getBoard(@PathVariable String row, @RequestParam String col) {
        var vari = gameBoard.getCellBoard()[Integer.parseInt(row)][Integer.parseInt(col)];
        return vari;
    }

    @GetMapping("/colorTile/{color}/{row}/{column}")
    public GameBoard getColorTile(@PathVariable int color, @PathVariable int row, @PathVariable int column) {
        if(gameBoard.isAbleToSet(color,row,column)&& gameBoard.getCellBoard()[row][column].getCurrentState() != CellState.GENERATED) {
            gameBoard.getCellBoard()[row][column].fillCellWithColor(ColorCell.getColor(color));
        }
        return gameBoard;
    }


}
