package com.bs.treasuremap.map.models;

import com.bs.treasuremap.adventurer.models.Adventurer;

public class Cell {
    private CellType type;
    private int treasuresNumber;
    private Adventurer adventurer;

    public Cell(CellType type) {
        this.type = type;
    }

    public boolean containsTreasures() {
        return treasuresNumber != 0;
    }

    public void withdrawTreasure() {
        this.treasuresNumber--;
    }

    public void putAdventurer(Adventurer adventurer) {
        this.adventurer = adventurer;
        if (this.containsTreasures()) {
            this.adventurer.collectTreasure(this);
        }
    }

    // getters
    public CellType getType() {
        return type;
    }

    public int getTreasuresNumber() {
        return treasuresNumber;
    }

    // setters
    public void setType(CellType type) {
        this.type = type;
    }

    public void setTreasuresNumber(int treasuresNumber) {
        this.treasuresNumber = treasuresNumber;
    }
}
