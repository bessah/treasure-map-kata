package com.bs.treasuremap.game.infrastructure;

import com.bs.treasuremap.game.infrastructure.mappers.GameMapper;
import com.bs.treasuremap.game.models.Game;
import com.bs.treasuremap.game.models.IGameResource;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InFileGameResource implements IGameResource {
    private final GameMapper gameMapper;

    public InFileGameResource(GameMapper gameMapper) {
        this.gameMapper = gameMapper;
    }

    @Override
    public Game load(String source) {
        List<String> gameData = readGameData(source);

        return gameMapper.toGame(gameData);
    }

    @Override
    public void save(Game game, String dist) {
        List<String> gameData = gameMapper.toGameData(game);

        saveGameData(gameData, dist);
    }

    private List<String> readGameData(String source) {
        try {
            return Files.readAllLines(Paths.get(source));
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Fail to read game data from %s", source), e);
        }
    }

    private void saveGameData(List<String> gameData, String dist) {
        try {
            Files.write(Paths.get(dist), gameData);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Fail to save game data in %s", dist), e);
        }
    }
}
