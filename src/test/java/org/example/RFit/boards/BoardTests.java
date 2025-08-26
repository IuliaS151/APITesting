package org.example.RFit.boards;

import org.example.RFit.endpoints.TrelloService;
import org.example.RFit.models.Response.BoardResponse;
import org.testng.annotations.Test;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class BoardTests {

    // Клиент Ретрофита
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.trello.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private final TrelloService apiService = retrofit.create(TrelloService.class);

    @Test
    public void createBoardTest() throws IOException {
        Response<BoardResponse> response = apiService.createBoard("Retrofit Created").execute();

        assertTrue(response.isSuccessful());
    }
}
