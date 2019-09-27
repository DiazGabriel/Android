package com.example.exemple.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exemple.R
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exemple.adapters.Article
import com.example.exemple.adapters.ArticleAdapter
import com.example.exemple.network.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticlesFragment : Fragment() {

    private val repository = ArticleRepository()

    // Ici, on associe le layout à afficher dans le fragment
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
            bindData(result)
        }
    }

    //S'execute sur le thread principal
    private suspend fun bindData(result: List<Article>) {
        withContext(Dispatchers.Main) {
            //afficher les données dans le recycler
        }
    }

            override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                super.onViewCreated(view, savedInstanceState)
                bindView(view)
            }

            private fun bindView(root:View) {
                //recupérer une liste de string depuis les ressources
                val planetes = resources.getStringArray(R.array.planetes)

                //recupérer l'instance du spinner dans la vue
                val spinner: Spinner = root.findViewById(R.id.spinner)

                //instancier l'adapteur
                val adapter = ArrayAdapter(root.context, android.R.layout.simple_spinner_item, planetes)

                //associer l'adapter au spinner
                spinner.adapter = adapter

                //Listener quand l'utilisateur selectionne un élément
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        Toast.makeText(root.context, "Vous n'avez rien selectionné", Toast.LENGTH_LONG).show()
                    }

                    override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        Toast.makeText(root.context, "Vous avez selectionné ${planetes[position]}", Toast.LENGTH_LONG).show()

                    }
                }

                //Création d'un nouvel article
                val articles = listOf(Article("Article 1", "L'article trop cool"))

                //instance du recycler
                val recyclerView: RecyclerView = root.findViewById(R.id.recycler_view)

                // créer une instance de l'adapteur
                val adapterRecycler = ArticleAdapter(articles)

                //définir l'orientation des élements (vertical)
                recyclerView.layoutManager = LinearLayoutManager(root.context)

                //associer l'adapter à la recyclerview
                recyclerView.adapter = adapterRecycler

            }
        }




