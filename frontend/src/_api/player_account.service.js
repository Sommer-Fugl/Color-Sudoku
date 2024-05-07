import {_exios} from "./index";

export const addPlayerAccount = (player, game, password) =>{
   return  _exios.post('/playerAccount', {player, game, password})
};

export const getPlayerAccount = (player, game) =>{
    return _exios.get('/playerAccount/getplayerAccount/' + game + '/' + player);
};

export const getLog = (game, player, password) =>{
    return _exios.get('/playerAccount/getlog/' + game + '/' + player + '/' + password);
};