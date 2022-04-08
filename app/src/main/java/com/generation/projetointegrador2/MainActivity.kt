package com.generation.projetointegrador2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.generation.projetointegrador2.databinding.ActivityMainBinding
import com.generation.projetointegrador2.databinding.FragmentPostagemBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread.sleep(1000)

        setTheme(R.style.Theme_ProjetoIntegrador2)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()


    }
}