package com.bs.treasuremap.adventurer.models;

import com.bs.treasuremap.common.models.Orientation;
import com.bs.treasuremap.common.models.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdventurerTest {
    @Test
    void orientation_should_be_EAST_when_turnRight_and_previous_orientation_is_NORTH() {
        turnRightTest(Orientation.NORTH, Orientation.EAST);
    }

    @Test
    void orientation_should_be_SOUTH_when_turnRight_and_previous_orientation_is_EAST() {
        turnRightTest(Orientation.EAST, Orientation.SOUTH);
    }

    @Test
    void orientation_should_be_WEST_when_turnRight_and_previous_orientation_is_SOUTH() {
        turnRightTest(Orientation.SOUTH, Orientation.WEST);
    }

    @Test
    void orientation_should_be_NORTH_when_turnRight_and_previous_orientation_is_WEST() {
        turnRightTest(Orientation.WEST, Orientation.NORTH);
    }

    @Test
    void orientation_should_be_WEST_when_turnLeft_and_previous_orientation_is_NORTH() {
        turnLeftTest(Orientation.NORTH, Orientation.WEST);
    }

    @Test
    void orientation_should_be_NORTH_when_turnLeft_and_previous_orientation_is_EAST() {
        turnLeftTest(Orientation.EAST, Orientation.NORTH);
    }

    @Test
    void orientation_should_be_EAST_when_turnLeft_and_previous_orientation_is_SOUTH() {
        turnLeftTest(Orientation.SOUTH, Orientation.EAST);
    }

    @Test
    void orientation_should_be_SOUTH_when_turnLeft_and_previous_orientation_is_WEST() {
        turnLeftTest(Orientation.WEST, Orientation.SOUTH);
    }

    private void turnRightTest(Orientation previousOrientation, Orientation expectedOrientation) {
        // Given
        Adventurer adventurer = adventurer(previousOrientation);
        // When
        adventurer.turnRight();
        // Then
        assertThat(adventurer.getOrientation()).isEqualTo(expectedOrientation);
    }

    private void turnLeftTest(Orientation previousOrientation, Orientation expectedOrientation) {
        // Given
        Adventurer adventurer = adventurer(previousOrientation);
        // When
        adventurer.turnLeft();
        // Then
        assertThat(adventurer.getOrientation()).isEqualTo(expectedOrientation);
    }

    Adventurer adventurer(Orientation orientation) {
        return new Adventurer("Lara", new Position(1, 1), orientation, null);
    }
}