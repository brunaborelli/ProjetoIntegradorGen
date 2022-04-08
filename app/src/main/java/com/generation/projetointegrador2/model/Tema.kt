package com.generation.projetointegrador2.model

data class Tema(
    val id: Int,
    var descricao: String?,
    var postagem: List<Postagem>?
    // var postagem: Postagem?
    )
{
    override fun toString(): String{
        return descricao!!
    }
}