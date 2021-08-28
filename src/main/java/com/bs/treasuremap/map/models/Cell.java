package com.bs.treasuremap.map.models;

public class Cell {
    private CellType type;
    private int treasuresNumber;

    public Cell(CellType type) {
        this.type = type;
    }

    // getters
    public CellType getType() {
        return type;
    }
}