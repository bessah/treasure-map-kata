package com.bs.treasuremap;

import com.bs.treasuremap.game.infrastructure.InFileGameResource;
import com.bs.treasuremap.game.infrastructure.mappers.*;
import com.bs.treasuremap.game.models.IGameResource;

public class Main {
    private static final GameMapper gameMapper = new GameMapper(
            new GameMapMapper(),
            new MountainMapper(),
            new TreasuresMapper(),
            new AdventurerMapper()
    );
    private static final IGameResource gameResource = new InFileGameResource(gameMapper);

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "You should indicate a path to a game resource file when running the programme." +
                            "Example : com.bs.treasuremap.Main src/main/resources/example1.txt"
            );
        }
        String source = args[0];
        String dist = source + ".result";
        var game = gameResource.load(source);
        game.run();
        gameResource.save(game, dist);
    }
}
