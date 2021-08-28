package com.bs.treasuremap.game.infrastructure.mappers;

import com.bs.treasuremap.common.models.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreasuresMapper {
    public Map<Position, Integer> toTreasures(List<String[]> treasuresLines) {
        return treasuresLines.stream().collect(Collectors.toMap(
                line -> new Position(Integer.parseInt(line[1]), Integer.parseInt(line[2])),
                line -> Integer.parseInt(line[3])
        ));
    }
}
