package com.sergio.marveltest.utils;

public interface Constants {
     String PUBLIC_API_KEY = "ceae1c116ab7186fde35e8cb983f8289";
     String BASE_URL = "https://gateway.marvel.com/v1/public/";
     String HASH = "7e7adee96174b90faafc5ff0d37ac681";
     String TS = "1";
     String URL = BASE_URL + "characters?apikey=" + PUBLIC_API_KEY + "&ts=" + TS + "&hash=" + HASH;
     //https://gateway.marvel.com/v1/public/characters?apikey=ceae1c116ab7186fde35e8cb983f8289&ts=1&hash=7e7adee96174b90faafc5ff0d37ac681
}
