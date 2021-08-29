package com.bs.treasuremap.game.models;

public interface IGameResource {
    Game load(String source);

    void save(Game game, String dist);
}
