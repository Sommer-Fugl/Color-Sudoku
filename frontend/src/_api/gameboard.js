import {_exios} from "./index";

export const getBoard = function (difficulty){
    return _exios.get('/game/getBoard/' + difficulty);
};

export const getCell = function (row, col){
    return _exios.get('/game/getCell/' + row + '/' + col);
}

export const colorTile = function (color, row, column){
    return _exios.get('/game/colorTile/' + color + '/' + row + '/' + column);
}