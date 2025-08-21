package org.example.steps;

import org.example.models.BoardResponse;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckActualVsExpected {

    public void compareBoard(BoardResponse actual, BoardResponse expected) {
        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getDesc()).isEqualTo(expected.getDesc());
    }

    public void compareBoardIgnoringId(BoardResponse actual, BoardResponse expected) {
        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getDesc()).isEqualTo(expected.getDesc());
    }
}
