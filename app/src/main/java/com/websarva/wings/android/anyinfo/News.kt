package com.websarva.wings.android.anyinfo

class News {
    var image: String? = null
    var title: String? = null
    var urlToImage: String? = null
    var description: String? = null
    var published_at: String? = null

    constructor(image: String, title: String, url: String, description: String, published_at: String){
        this.image = image
        this.title = title
        this.urlToImage = url
        this.description = description
        this.published_at = published_at
    }
}