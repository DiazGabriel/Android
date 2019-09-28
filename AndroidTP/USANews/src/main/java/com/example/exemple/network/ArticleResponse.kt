package com.example.exemple.network

import com.example.exemple.adapters.Article

data class ArticleResponse(
        val status : String,
        val totalResults: Int,
        val articles : List<Article>
)