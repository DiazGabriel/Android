package com.example.exemple.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exemple.R

class ArticleAdapter(private val dataset: List<Article>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
       return dataset.size
    }

    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {

        fun bind(item: Article) {
            val txtTitle = root.findViewById<TextView>(R.id.Titre)
            val txtDesc = root.findViewById<TextView>(R.id.Texte)
            txtTitle.text = item.title
            txtDesc.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.adapter, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }

}

data class Article(val description: String, val title: String)