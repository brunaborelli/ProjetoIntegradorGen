package com.generation.projetointegrador2.model

data class Postagem(

    val id: Int,
    val titulo: String,
    val descricao: String,
    val imagem: String,
    val dataHora: String,
    val autor: String,
    val tema: Tema)
    {

}
