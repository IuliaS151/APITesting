package org.example.RFit.steps;

import org.example.RFit.models.Response.BoardResponse;
import org.example.RFit.endpoints.TrelloService;
import org.example.RFit.client.TrelloClient;
import org.example.RFit.models.Request.BoardRequest;
import retrofit2.Response;

import java.io.IOException;
import java.util.Map;

public class SendRequest {
    private  final TrelloService api = TrelloClient.api();

    public  Response<BoardResponse> createBoard(String name) throws IOException {
        return api.createBoard(name).execute();
    }

    public Response<BoardResponse> getBoard(String id) throws IOException {
        return api.getBoardData(id).execute();
    }
    public Response<BoardResponse> updateBoard(String id, String newName, String newDesc) throws IOException {
        return api.updateBoard(id, newName, newDesc).execute();
    }

    public Response<BoardResponse> updateBoardMultiParams(String id, Map<String, String> params) throws IOException {
        return api.updateBoardMultiParams(id, params).execute();
    }

    public Response<Void> deleteBoard(String id) throws IOException {
        return api.deleteBoard(id).execute();
    }
}
