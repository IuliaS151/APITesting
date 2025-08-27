package org.example.RFit.boards;

import org.example.RFit.models.Response.BoardResponse;
import org.example.RFit.support.BaseTest;
import org.testng.annotations.Test;
import retrofit2.Response;

public class GetBoardTest extends BaseTest {

    @Test
    public void getBoardTest() throws Exception {
        Response<BoardResponse> response = send.getBoard(boardId);

    }

}
