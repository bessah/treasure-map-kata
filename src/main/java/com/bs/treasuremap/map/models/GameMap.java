package com.bs.treasuremap.map.models;

import com.bs.treasuremap.adventurer.models.Adventurer;
import com.bs.treasuremap.common.models.Position;

import java.util.ArrayList;
import java.util.HashMap;
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

    public boolean has(Position position) {
        if (position.getX() < 0 || position.getX() >= this.cells[0].length) {
            return false;
        }
        if (position.getY() < 0 || position.getY() >= this.cells.length) {
            return false;
        }
        return true;
    }

    public boolean isFree(Position position) {
        return getCell(position).isFree();
    }

    public void moveAdventurer(Adventurer adventurer, Position from, Position to) {
        getCell(from).removeAdventurer(); // remove adventurer from the old position
        getCell(to).putAdventurer(adventurer); // put adventurer in the new position
    }

    // getters
    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    public Cell getCell(Position position) {
        return this.cells[position.getY()][position.getX()];
    }

    public int getWidth() {
        return this.cells[0].length;
    }

    public int getHeight() {
        return this.cells.length;
    }

    public List<Position> getMountainsPositions() {
        List<Position> mountainsPositions = new ArrayList<>();
        for (var i = 0; i < cells.length; i++) {
            for (var j = 0; j < cells[0].length; j++) {
                var position = new Position(j, i);
                var cell = getCell(position);
                if (cell.getType() == CellType.MOUNTAIN) {
                    mountainsPositions.add(position);
                }
            }
        }
        return mountainsPositions;
    }

    public Map<Position, Integer> getTreasures() {
        Map<Position, Integer> treasures = new HashMap<>();
        for (var i = 0; i < cells.length; i++) {
            for (var j = 0; j < cells[0].length; j++) {
                var position = new Position(j, i);
                var cell = getCell(position);
                if (cell.containsTreasures()) {
                    treasures.put(position, cell.getTreasuresNumber());
                }
            }
        }
        return treasures;
    }
}
