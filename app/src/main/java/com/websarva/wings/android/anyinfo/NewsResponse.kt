package com.websarva.wings.android.anyinfo

import com.google.gson.annotations.SerializedName
import java.net.URL

class NewsResponse {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("totalResults")
    var totalResults: Int? = 0

    @SerializedName("articles")
    var articles: Array<Articles>? = null
}

class Articles {
    @SerializedName("source")
    var source: Source? = null
    @SerializedName("author")
    var author: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("url")
    var url: URL? = null
    @SerializedName("urlToImage")
    var urlToImage: URL? = null
    @SerializedName("publishedAt")
    var publishedAt: String? = null
    @SerializedName("content")
    var content: String? = null
}

class Source {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("name")
    var name: String? = null
}