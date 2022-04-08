package com.generation.projetointegrador2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.generation.projetointegrador2.databinding.FragmentFormularioBinding
import com.generation.projetointegrador2.model.Postagem
import com.generation.projetointegrador2.model.Tema
import fragment.DatePickerFragment
import fragment.TimePickerListener
import java.time.LocalDate


class FormularioFragment : Fragment(), TimePickerListener {

    private lateinit var binding: FragmentFormularioBinding
    private var temaSelecionado = 0
    private var postagemSelecionada: Postagem? = null
    //private lateinit var mainViewModel: MainViewModel

    //Momento em que a view está sendo criada

    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //essa variável recebe o layout que será criado
        binding = FragmentFormularioBinding.inflate(layoutInflater, container, false)

        carregarDados()

        //botão sendo criado para construção da view
        binding.buttonPostagem.setOnClickListener {

            if (binding.editDescricao.text.isEmpty() || binding.editTitulo.text.isEmpty() || binding.editTextLinkImagem.text.isEmpty()) {
                Toast.makeText(context, "Preencha todos os dados", Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_formularioFragment_to_postagemFragment)
                Toast.makeText(context, "Postagem publicada!", Toast.LENGTH_SHORT).show()
            }
        //retornando a view para visualização

        }

        // puxando o DatePicker e colocando em editData
        binding.editData.setOnClickListener{
            DatePickerFragment(this).show(parentFragmentManager, "DatePicker")
        }

        binding.buttonPostagem.setOnClickListener {
            inserirNoBanco()
        }

        fun spinnerTema(tema : List<Tema>?){
                    if(tema != null){
                        binding.spinnerTema.adapter = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tema)
                        binding.spinnerTema.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val temaSelecionadoFun = binding.spinnerTema.selectedItem as Tema
                        temaSelecionado = temaSelecionadoFun.id

                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }
            }
        }

        mainViewModel.myTemaResponse.observe(viewLifecycleOwner) { response ->
            Log.d(
                "Requisicao",
                response.body().toString()
            )
            spinnerTema(response.body())
        }

        mainViewModel.dataSelecionada.observe(viewLifecycleOwner) {
            selectedDateTime -> binding.editData.setText(selectedDateTime.toString())
        }



        return binding.root

    }

    override fun onTimeSelected(date: LocalDate) {
        mainViewModel.dataSelecionada.value = date
    }

    /*  val id: Int,
    val titulo: String,
    val descricao: String,
    val imagem: String,
    val dataHora: String,
    val autor: String,
    val tema: Tema) */

    fun validarCampos(titulo: String,
                      descricao: String,
                      imagem: String,
                      dataHora: String,
                      autor: String): Boolean {
        return !(
                (titulo == "" || titulo.length < 4 || titulo.length > 65) ||
                (descricao == "" || descricao.length < 2 || descricao.length > 780) ||
                (imagem == "" || imagem.length < 4 ) ||
                (autor == "" || autor.length < 2 || autor.length > 35) ||
                dataHora == ""
                )
        }
    fun inserirNoBanco(){
        val titulo = binding.editTitulo.text.toString()
        val descricao = binding.editDescricao.text.toString()
        val imagem = binding.editTextLinkImagem.text.toString()
        val dataHora = binding.editData.text.toString()
        val autor = binding.editAutor.text.toString()
        val tema = Tema(temaSelecionado, null, null)

        if (validarCampos(titulo, descricao, imagem, dataHora, autor)){
            if (postagemSelecionada == null){
                val postagem = Postagem(
                    0, titulo, descricao, imagem, dataHora, autor, tema)
                mainViewModel.addPostagem(postagem)

            } else {
            val postagem = Postagem(postagemSelecionada?.id!!,
            titulo, descricao, imagem, dataHora, autor, tema
                )
            mainViewModel.updatePostagem(postagem)

        }
        Toast.makeText(context, "Você criou uma ação solidária!", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_formularioFragment_to_postagemFragment)


    }else {
            Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_LONG).show()
        }
    }
    private fun carregarDados(){
        postagemSelecionada = mainViewModel.postagemSelecionada
        if (postagemSelecionada != null){
            binding.editTitulo.setText(postagemSelecionada?.titulo)
            binding.editDescricao.setText(postagemSelecionada?.descricao)
            binding.editData.setText(postagemSelecionada?.dataHora)
            binding.editAutor.setText(postagemSelecionada?.autor)
            binding.editTextLinkImagem.setText(postagemSelecionada?.imagem)

        }else{
            binding.editTitulo.text = null
            binding.editDescricao.text = null
            binding.editData.text = null
            binding.editAutor.text = null
            binding.editTextLinkImagem.text = null
        }

    }

}