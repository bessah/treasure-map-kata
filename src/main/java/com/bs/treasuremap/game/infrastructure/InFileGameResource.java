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

    private List<String> readGameData(String source) {
        try {
            return Files.readAllLines(Paths.get(source));
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Fail to read game data from %s", source), e);
        }
    }
}
