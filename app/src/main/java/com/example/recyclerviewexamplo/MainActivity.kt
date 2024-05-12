package com.example.recyclerviewexamplo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewexamplo.adapter.SuperHeroAdapter
import com.example.recyclerviewexamplo.databinding.ActivityMainBinding
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var superHeroMutableList: MutableList<SuperHero> =
        superHeroProvider.superheroList.toMutableList()
    private lateinit var adapter: SuperHeroAdapter
    private var llmanager =  LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAddSuperHero.setOnClickListener { createSuperHero() }
        initRecyclerView()
    }

    private fun createSuperHero() {
        val superHero = SuperHero(
            "incognito",
            "AristidevCorporation",
            "???????",
            "https://pbs.twimg.com/profile_images/1037281659727634432/5x2XVPwB_400x400.jpg"
        )

        superHeroMutableList.add(index = 3,superHero)
        adapter.notifyItemInserted(3)
        llmanager.scrollToPositionWithOffset(3,20)
    }

    private fun initRecyclerView() {
        adapter = SuperHeroAdapter(
            superheroList = superHeroMutableList,
            onClickListener = { superHero -> onItemSelected(superHero) },
            onClickDelete = { position -> onDeletedItem(position) }
        )
        binding.recyclerSuperHero.layoutManager = llmanager
        binding.recyclerSuperHero.adapter = adapter

    }

    private fun onDeletedItem(position: Int) {
        superHeroMutableList.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    private fun onItemSelected(SuperHero: SuperHero) {
        Toast.makeText(this, SuperHero.superhero, Toast.LENGTH_SHORT).show()
    }
}


