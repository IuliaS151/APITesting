package org.example.RFit.boards;

import org.example.RFit.models.Response.BoardResponse;
import org.example.RFit.support.BaseTest;
import org.testng.annotations.Test;
import retrofit2.Response;

public class GetBoardTest extends BaseTest {

    @Test
    public void getBoardTest() throws Exception {
        Response<BoardResponse> getResponse = send.getBoard(boardId);
        check.statusIs(getResponse, 200);

        BoardResponse got = prepare.asPojo(getResponse);
        BoardResponse expect = expected.boardWithId(boardId, initialName, initialDesc);

        compare.compareBoard(got, expect);
    }

}
