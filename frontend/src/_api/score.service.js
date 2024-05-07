import {_exios} from "./index";
import {formatDate} from "./utils";

export const addScore = (score, game, player) => {
    return _exios.post('/score', {game, player, points: Number(score) , playedOn: formatDate(new Date)})
};

export const getTopScores = (game) => {
    return _exios.get('/score/topscores/' + game);
};

export const getAllScores = (game) =>{
    return _exios.get('/score/allscores/' + game);
};