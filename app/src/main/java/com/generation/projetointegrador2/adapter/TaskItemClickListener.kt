package com.generation.projetointegrador2.adapter

import com.generation.projetointegrador2.model.Postagem

interface TaskItemClickListener {
    fun onTaskClicked(postagem: Postagem)
}