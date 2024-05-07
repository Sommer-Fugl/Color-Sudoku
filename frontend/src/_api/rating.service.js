import {_exios} from "./index";
import {formatDate} from "./utils";

export const addRating = function (game, player, rating){
     return _exios.post('/rating', {
        game, player, rating, ratedOn : formatDate(new Date)
    })
};

export const getAverageRating = function(game){
    return _exios.get('/rating/average/' + game);
};

export const getRating = function(game, player){
    return _exios.get('/rating/onerating/' + game +'/' + player);
};

export const getRatingPlayer = function (game){
    return _exios.get('/rating/ratinglist/' + game);
};

export const getRatingClass = function(game, player){
    return _exios.get("/rating/getratingclass/" + game + '/' + player);
};