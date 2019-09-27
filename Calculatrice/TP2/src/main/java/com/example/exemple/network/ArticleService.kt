package com.example.exemple.network

import com.example.exemple.adapters.Article
import retrofit2.Call
import retrofit2.http.GET

interface ArticleService {
    @GET("everything? domaines = wsj.com & apiKey = 53006b6b094544f4833ce5d0ee66e18b")
    fun list(): Call<List<Article>>
}