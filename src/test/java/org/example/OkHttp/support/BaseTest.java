package org.example.OkHttp.support;

import okhttp3.Response;
import org.example.OkHttp.models.BoardResponse;
import org.example.OkHttp.steps.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected final BuildRequest build = new BuildRequest();
    protected final SendRequest send = new SendRequest();
    protected final CheckResponseIsValid check = new CheckResponseIsValid();
    protected final PrepareActualResponse prepare = new PrepareActualResponse();
    protected final PrepareExpectedResponse expected = new PrepareExpectedResponse();
    protected final CheckActualVsExpected compare = new CheckActualVsExpected();

    protected String boardId;
    protected String initialName;
    protected String initialDesc;

    protected boolean needFreshBoard() {    return true;    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        if(!needFreshBoard()) return;

        Response response = send.createBoard(initialName, build.createBoardParams(initialDesc, Boolean.FALSE));
        check.statusIs(response, 200);
        BoardResponse board = send.asBoard(response);

        boardId = board.getId();
        initialName = board.getName();
        initialDesc = board.getDesc();
    }

    @AfterMethod(alwaysRun = true)
    public void reset() throws Exception {
        if (boardId != null && !boardId.isEmpty()) {
            send.deleteBoard(boardId);

            boardId = null;
        }
    }
}
