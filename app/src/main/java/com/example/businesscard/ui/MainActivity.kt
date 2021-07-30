package com.example.businesscard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.businesscard.App
import com.example.businesscard.util.Image
import com.example.businesscard.ViewModelCard
import com.example.businesscard.ViewModelCardFactory
import com.example.businesscard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModelCard by viewModels {
        ViewModelCardFactory((application as App).repository)
    }

    lateinit var adapter: AdapterCard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AdapterCard()
        binding.rvItems.adapter = adapter

        listeners()
        load()

    }

    private fun listeners() {
        binding.fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddCardActivity::class.java))
        }

        adapter.listenerShare = { card ->
            Image.share(this@MainActivity, card)
        }
    }

    private fun load() {
        viewModel.getAll().observe(this){
            it?.let {
                adapter.setData(it)
            }
            adapter.notifyDataSetChanged()
        }
    }
}