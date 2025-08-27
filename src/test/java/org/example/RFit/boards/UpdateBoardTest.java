package org.example.RFit.boards;


import org.example.RFit.models.Response.BoardResponse;
import org.example.RFit.support.BaseTest;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.util.Map;

public class UpdateBoardTest extends BaseTest {
    @Test
    public void updateBoardTest() throws Exception {
        Response<BoardResponse> updResponse = send.updateBoard(
                boardId, "Updated by PUT", "new description");

        check.statusIs(updResponse,200);

        BoardResponse updated = prepare.asPojo(updResponse);
        BoardResponse expect = expected.boardWithId(boardId, "Updated by PUT", "new description");

        compare.compareBoard(updated, expect);
    }
}
