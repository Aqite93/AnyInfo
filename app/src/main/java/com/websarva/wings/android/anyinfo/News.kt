package com.websarva.wings.android.anyinfo

class News {
    var image: Int? = null
    var title: String? = null
    var url: String? = null
    var description: String? = null
    var published_at: String? = null

    constructor(image: Int, title: String, url: String, description: String, published_at: String){
        this.image = image
        this.title = title
        this.url = url
        this.description = description
        this.published_at = published_at
    }
}