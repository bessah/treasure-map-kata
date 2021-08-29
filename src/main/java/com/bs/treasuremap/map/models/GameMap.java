package com.bs.treasuremap.map.models;

import com.bs.treasuremap.adventurer.models.Adventurer;
import com.bs.treasuremap.common.models.Position;

import java.util.List;
import java.util.Map;

public class GameMap {
    private final Cell[][] cells;

    public GameMap(int width, int height) {
        this.cells = new Cell[height][width];
        this.initCellsAsPlains();
    }

    private void initCellsAsPlains() {
        for (var i = 0; i < cells.length; i++) {
            for (var j = 0; j < cells[0].length; j++) {
                this.cells[i][j] = new Cell(CellType.PLAIN);
            }
        }
    }

    public void putMountains(List<Position> positions) {
        for (Position pos : positions) {
            getCell(pos).setType(CellType.MOUNTAIN);
        }
    }

    public void putTreasures(Map<Position, Integer> treasures) {
        for (Map.Entry<Position, Integer> treasure : treasures.entrySet()) {
            getCell(treasure.getKey()).setTreasuresNumber(treasure.getValue());
        }
    }

    public void putAdventurers(List<Adventurer> adventurers) {
        for (Adventurer adventurer : adventurers) {
            getCell(adventurer.getPosition()).putAdventurer(adventurer);
        }
    }

    // getters
    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    private Cell getCell(Position position) {
        return this.cells[position.getY()][position.getX()];
    }
}
