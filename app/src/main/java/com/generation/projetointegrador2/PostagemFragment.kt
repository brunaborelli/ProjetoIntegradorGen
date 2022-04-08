package com.generation.projetointegrador2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.generation.projetointegrador2.adapter.PostagensAdapter
import com.generation.projetointegrador2.adapter.TaskItemClickListener
import com.generation.projetointegrador2.databinding.FragmentPostagemBinding
import com.generation.projetointegrador2.model.Postagem

class PostagemFragment : Fragment(), TaskItemClickListener {

    private lateinit var binding: FragmentPostagemBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    //Momento em que a view está sendo criada
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainViewModel.listPostagem()

        //essa variável recebe o layout que será criado
        binding = FragmentPostagemBinding.inflate(layoutInflater, container, false)

        //Criando a lista de postagem que irá popular o fragmento postagem
        //pra isso, é necessário passar a classe tipo *Formulario*

        //instanciando a lista de postagem com o RecryclerView localizado pelo ID.

        //criando o adapter // adapter é responsável por cuidar do RecyclerView
        val adapter = PostagensAdapter(this, mainViewModel, requireContext())

        //chamando a variável RecryclerPostagem com o método layoutManager
        //recebendo o tipo de Layout que ele terá
        binding.recycleFeed.layoutManager = LinearLayoutManager(context)

        //falando pro RecyclerView que ele está recebendo um adapter que vai cuidar dele
        binding.recycleFeed.adapter = adapter

        //falando que haverá um tamanho fixo
        binding.recycleFeed.setHasFixedSize(true)

        binding.buttonMais.setOnClickListener(){
            mainViewModel.postagemSelecionada = null
            findNavController().navigate(R.id.action_postagemFragment_to_formularioFragment)
        }


        mainViewModel.myPostagemResponse.observe(viewLifecycleOwner){
            response -> if(response != null){
                adapter.setLista(response.body()!!)
        }
        }

        return binding.root


    }

    override fun onTaskClicked(postagem: Postagem) {
        mainViewModel.postagemSelecionada = postagem
        findNavController().navigate(R.id.action_postagemFragment_to_formularioFragment)
    }

}