package org.example.RFit.boards;

import org.example.RFit.models.Response.BoardResponse;
import org.example.RFit.support.BaseTest;
import org.testng.annotations.Test;
import retrofit2.Response;

public class CreateBoardTest extends BaseTest {
    @Override
    protected boolean needFreshBoard() {    return false;   }

    @Test
    public void createBoardTest() throws Exception {
        Response<BoardResponse> response = send.createBoard("Created by POST");
        check.statusIs(response,200);

        BoardResponse created = prepare.asPojo(response);
        BoardResponse expect = expected.boardWithoutId("Created by POST", "");

        compare.compareBoardIgnoringId(created, expect);

        boardId = created.getId();
    }
}
