package com.example.planetes

import ArticleAdapter
import adaptateur.Article
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.R

class PlanetesActivity : AppCompatActivity() {

    lateinit var spinner: Spinner
    lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planetes)

        //recupérer une liste de string depuis les ressources
        val planetes = resources.getStringArray(R.array.planetes)

        // recupérer l'instance du spinner dans la vue
        spinner = findViewById(R.id.spinner)

        //instancier l'adapteur
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, planetes)

        //associer l'adapter au spinner
        spinner.adapter = adapter

        //Listener quand l'utilisateur selectionne un élément
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(baseContext, "Vous n'avez rien selectionné", Toast.LENGTH_LONG)
                        .show()
            }

            override fun onItemSelected(
                    adapter: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                if (planetes[position].toString() != "Sélectionner une planète") {
                    Toast.makeText(
                            baseContext,
                            "Vous avez selectionné ${planetes[position]}",
                            Toast.LENGTH_LONG
                    ).show()
                }
                associateAdapterToRecyclerView(planetes[position].toString())
            }
        }
    }

    fun associateAdapterToRecyclerView(string: String) {
        //instance du recycler
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        //créer une liste d'articles
        var articles = listOf<Article>()
        when (string) {
            "Mercure" -> {
                articles = listOf<Article>(
                        Article(string, getString(R.string.Mercure), R.drawable.mercure)
                )
            }
            "Vénus" -> {
                articles = listOf<Article>(
                        Article(string, getString(R.string.Vénus), R.drawable.venus)
                )
            }
            "Terre" -> {
                articles = listOf<Article>(
                        Article(string, getString(R.string.Terre), R.drawable.terre)
                )
            }
            "Mars" -> {
                articles = listOf<Article>(
                        Article(string, getString(R.string.Mars), R.drawable.mars)
                )
            }
            "Jupiter" -> {
                articles = listOf<Article>(
                        Article(string, getString(R.string.Jupiter), R.drawable.jupiter)
                )
            }
            "Saturne" -> {
                articles = listOf<Article>(
                        Article(string, getString(R.string.Saturne), R.drawable.saturne)
                )
            }
            "Uranus" -> {
                articles = listOf<Article>(
                        Article(string, getString(R.string.Uranus), R.drawable.uranus)
                )
            }
            "Neptune" -> {
                articles = listOf<Article>(
                        Article(string, getString(R.string.Neptune), R.drawable.neptune)
                )
            }
        }
        //créer une instance de l'adapteur
        val adapterRecycler = ArticleAdapter(articles)
        //définir l'orientation des élements (vertical)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
        //associer l'adapter à la recyclerview
        recyclerView.adapter = adapterRecycler
    }
}
