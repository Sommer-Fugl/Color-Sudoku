import axios from 'axios'

export const _exios = axios.create(
    {baseURL : 'http://localhost:3000/api'}
);