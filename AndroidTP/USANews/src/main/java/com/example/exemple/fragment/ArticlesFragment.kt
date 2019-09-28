package com.example.exemple.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exemple.R
import com.example.exemple.adapters.Article
import com.example.exemple.adapters.ArticleAdapter
import com.example.exemple.network.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ArticlesFragment : Fragment() {
    private val repository = ArticleRepository()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_articles, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        GlobalScope.launch {
            getData()
        }
    }

    //S'execute dans un thread secondeaire
    private suspend fun getData() {
        withContext(Dispatchers.IO) {
            val result = repository.list()
            bindData(result.articles)
        }
    }

    private lateinit var root : View

    //S'execute sur le thread principal
    private suspend fun bindData(result: List<Article>) {
        withContext(Dispatchers.Main) {

            val recyclerView: RecyclerView = view!!.findViewById(R.id.recycler_view)
            val adapterRecycler = ArticleAdapter(result)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapterRecycler
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        root = view
        bindRecyclerView(view)

        // Spinner désactivé. On verra ce qu'on en fais plus tard... ou pas
        // bindSpinner(view)
    }

    private fun bindRecyclerView(view: View){

        val listArticles = listOf(
            Article("Article 1", "Cet article est génial"),
            Article("Article 2", "Cet article est nul.. :-(")
        )

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val adapterRecycler = ArticleAdapter(listArticles)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapterRecycler
    }

    private fun bindSpinner(view: View){

        //recupérer une liste de string depuis les ressources
        val planetes = resources.getStringArray(R.array.planetes)

        //recupérer l'instance du spinner dans la vue
        val spinner : Spinner = view.findViewById(R.id.spinner)

        //instancier l'adapteur
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, planetes)

        //associer l'adapter au spinner
        spinner.adapter = adapter

        //Listener quand l'utilisateur selectionne un élément
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(view.context, "Vous n'avez rien selectionné", Toast.LENGTH_LONG)
                        .show()
            }

            override fun onItemSelected(
                    adapter: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                Toast.makeText(
                        context,
                        "Vous avez selectionné ${planetes[position]}",
                        Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}