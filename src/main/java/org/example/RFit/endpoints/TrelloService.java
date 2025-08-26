package org.example.RFit.endpoints;

import org.example.RFit.models.Response.BoardResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.optional;

public interface TrelloService {

    // Создание новой доски
    @POST("boards")
    Call<BoardResponse> createBoard(
            @Query("name") String boardName);

    // Получение доски по id
    @GET("boards/{id}")
    Call<BoardResponse> getBoardData(
            @Path("id") String boardId);

    // Обновление доски
    @PUT("boards/{id}")
    Call<BoardResponse> updateBoard(
            @Path("id") String boardId,
            @Query("name") String newName,
            @Query("desc") String newDesc);

    @PUT("boards/{id}")
    Call<BoardResponse> updateBoardMultiParams(
            @Path("id") String boardId,
            @QueryMap Map<String, String> params);

    // Удаление доски
    @DELETE("boards/{id}")
    Call<Void> deleteBoard(
            @Path("id") String boardId);
}
