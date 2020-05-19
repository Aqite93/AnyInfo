package com.websarva.wings.android.anyinfo

class News {
    var title: String? = null
    var url: String? = null
    var urlToImage: String? = null
    var description: String? = null
    var publishedAt: String? = null

    constructor(title: String, url: String, urlToImage: String, description: String, published_at: String){
        this.title = title
        this.url = url
        this.urlToImage = urlToImage
        this.description = description
        this.publishedAt = published_at
    }
}