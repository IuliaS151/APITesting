package org.example.RFit.support;

import org.testng.annotations.AfterMethod;
import retrofit2.Response;
import org.example.RFit.models.Response.BoardResponse;
import org.example.RFit.steps.*;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected final BuildRequest build = new BuildRequest();
    protected final SendRequest send = new SendRequest();
    protected final CheckResponseIsValid check = new CheckResponseIsValid();
    protected final PrepareActualResponse prepare = new PrepareActualResponse();
    protected final PrepareExpectedResponse expected = new PrepareExpectedResponse();
    protected final CheckActualVsExpectedResponses compare = new CheckActualVsExpectedResponses();

    protected String boardId;
    protected String initialName;
    protected String initialDesc;

    protected boolean needFreshBoard() {    return true;    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        if(!needFreshBoard()) return;

        Response<BoardResponse> createInitialBoard = send.createBoard("Temporary board");
        check.statusIs(createInitialBoard, 200);

        BoardResponse created = prepare.asPojo(createInitialBoard);
        boardId = created.getId();
        initialName = created.getName();
        initialDesc = created.getDesc();
    }

    @AfterMethod(alwaysRun = true)
    public void reset() throws Exception {
        if (boardId != null && !boardId.isEmpty()) {
            send.deleteBoard(boardId);
            boardId = null;
        }
    }
}
