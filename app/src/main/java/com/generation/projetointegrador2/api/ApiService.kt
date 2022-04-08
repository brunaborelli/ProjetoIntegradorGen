package com.generation.projetointegrador2.api

import com.generation.projetointegrador2.model.Postagem
import com.generation.projetointegrador2.model.Tema
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("temas")
    suspend fun listTema(): Response<List<Tema>>

    @POST("postagens")
    suspend fun addPostagem(@Body postagem: Postagem): Response<Postagem>

    @GET("postagens")
    suspend fun listPostagem():Response <List<Postagem>>

    @PUT("postagens")
    suspend fun updatePostagem(
        @Body postagem: Postagem
    ): Response<Postagem>

    @DELETE("postagens/{id}")
    suspend fun deletePostagem(
        @Path("id") valor: Int
    ): Response<Postagem>
}