package com.generation.projetointegrador2.repository

import com.generation.projetointegrador2.api.RetrofitInstance
import com.generation.projetointegrador2.model.Postagem
import com.generation.projetointegrador2.model.Tema
import retrofit2.Response

class Repository {

    suspend fun listTema(): Response<List<Tema>>{
        return RetrofitInstance.api.listTema()
    }

    suspend fun addPostagem(postagem: Postagem): Response<Postagem>{
        return RetrofitInstance.api.addPostagem(postagem)
    }

    suspend fun listPostagem(): Response<List<Postagem>>{
        return RetrofitInstance.api.listPostagem()
    }
    suspend fun updatePostagem(postagem: Postagem): Response<Postagem>{
        return RetrofitInstance.api.updatePostagem(postagem)
    }
    suspend fun deletePostagem(id: Int): Response<Postagem>{
        return RetrofitInstance.api.deletePostagem(id)
    }
}