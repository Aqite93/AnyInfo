package com.websarva.wings.android.anyinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

class MainActivity : AppCompatActivity() {
    companion object {
        var BaseUrl = "http://newsapi.org/v2/top-headlines/"
        var country = "us"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lvNews = findViewById<ListView>(R.id.lvNews)
        val newsList: MutableList<MutableMap<String, URL>> = mutableListOf()
        val news = getNewsHeadline()

        // TODO set news api response on list view
    }

    fun getNewsHeadline() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(NewsService::class.java)
        val call = service.getNewsHeadlines(country)
        var news: NewsResponse
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.code() == 200) {
                    val newsResponse = response.body()!!
                    Log.i("newsResponse: ", newsResponse.totalResults.toString())
                    news = newsResponse
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.i("failure", "failed...")
                Log.i("failure getting news: ", t.message)
            }
        })

        return news
    }
}
