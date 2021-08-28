package com.bs.treasuremap.map.models;

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

    // getters
    public Cell getCell(int i, int j) {
        return cells[i][j];
    }
}
