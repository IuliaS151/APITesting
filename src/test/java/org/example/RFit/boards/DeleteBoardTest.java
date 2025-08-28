package org.example.RFit.boards;

import org.example.RFit.models.Response.BoardResponse;
import org.example.RFit.support.BaseTest;
import org.testng.annotations.Test;
import retrofit2.Response;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.io.IOException;

public class DeleteBoardTest extends BaseTest {
    @Test
    public void deleteBoardTest() throws IOException {
        Response<Void> response = send.deleteBoard(boardId);
        check.statusIs(response, 200);

        Response<?> getAfter = send.getBoard(boardId);
        assertEquals(getAfter.code(), 404);
        assertFalse(getAfter.isSuccessful());

        boardId = null;
    }
}
