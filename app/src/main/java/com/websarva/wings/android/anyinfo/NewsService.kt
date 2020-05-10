package com.websarva.wings.android.anyinfo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {
    @Headers("x-api-key: <news api-key>")
    @GET("http://newsapi.org/v2/top-headlines")
    fun getNewsHeadlines(@Query("country") country: String): Call<NewsResponse>
}