package com.bs.treasuremap.game.infrastructure;

import com.bs.treasuremap.game.infrastructure.mappers.GameMapper;
import com.bs.treasuremap.game.models.Game;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class InFileGameResourceTest {
    GameMapper gameMapper = Mockito.mock(GameMapper.class);
    InFileGameResource inFileGameResource = new InFileGameResource(gameMapper);

    @Test
    void load_should_succeed_when_provided_source_exist() {
        // Given
        String source = "src/test/resources/example1.txt";
        Game game = Mockito.mock(Game.class);
        when(gameMapper.toGame(anyList())).thenReturn(game);
        // When
        Game actual = inFileGameResource.load(source);
        // Then
        assertThat(actual).isEqualTo(game);
    }

    @Test
    void load_should_fail_when_provided_source_is_wrong() {
        // Given
        String source = "src/test/resources/fakeExample.txt";
        Game game = Mockito.mock(Game.class);
        when(gameMapper.toGame(anyList())).thenReturn(game);
        // When
        ThrowableAssert.ThrowingCallable throwingCallable = () ->
                inFileGameResource.load(source);
        // Then
        assertThatCode(throwingCallable).isInstanceOf(IllegalArgumentException.class)
                .extracting(IllegalArgumentException.class::cast)
                .extracting(IllegalArgumentException::getMessage)
                .isEqualTo("Fail to read game data from src/test/resources/fakeExample.txt");
    }

    @Test
    void should_save_game_data_on_provided_file() throws Exception {
        // Given
        Game game = Mockito.mock(Game.class);
        String dist = "src/test/resources/fakeExample.txt.result";
        List<String> gameData = List.of(
                "Data 1",
                "Data 2"
        );
        when(gameMapper.toGameData(game)).thenReturn(gameData);
        // When
        inFileGameResource.save(game, dist);
        // Then
        List<String> actual = Files.readAllLines(Paths.get(dist));
        assertThat(actual).isEqualTo(gameData);
    }
}