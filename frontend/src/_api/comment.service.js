import {_exios} from "./index";
import {formatDate} from "./utils";

export const getComments = function (game){
    return _exios.get('/comment/getcomments/' + game);
};

export const addComment = function (game, player, comment){
    return _exios.post('/comment', {player, game, comment, commentedOn: formatDate(new Date())});
}