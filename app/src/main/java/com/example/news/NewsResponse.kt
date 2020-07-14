package com.example.news

data class NewsResponse(
    val status: String,
    val articles: ArrayList<Articles>
)

data class Source(
    val name: String
)

data class Articles(
    val source: Source,
    var author: String,
    var title: String,
    var description: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String
)

