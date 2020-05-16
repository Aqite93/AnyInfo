package com.websarva.wings.android.anyinfo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var newsResponse: NewsResponse? = null

    companion object {
        var BaseUrl = "http://newsapi.org/v2/top-headlines/"
        var country = "us"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lvNews = findViewById<ListView>(R.id.lvNews)

        // When click list item, moving to news site
        lvNews.setOnItemClickListener { parent, view, position, id ->
            val element = parent.getItemAtPosition(position) as MutableMap<String, String>
            Log.i("url: ", element["url"])
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(element["url"] as String))
            startActivity(browserIntent)
        }
        getNewsHeadline()
    }

    /**
     * Get world news title and url by using news api.
     * After getting news, set them on list view.
     */
    private fun getNewsHeadline() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(NewsService::class.java)
        val call = service.getNewsHeadlines(country)

        // call news api
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.code() == 200) {
                    newsResponse = response.body() as NewsResponse
                    Log.i("newsResponse: ", "get news response")

                    // create mutable data
                    val lvNews = findViewById<ListView>(R.id.lvNews)
                    val newsList: MutableList<MutableMap<String, String?>> = mutableListOf()

                    newsResponse!!.articles?.forEach {
                        val news = mutableMapOf("title" to it.title, "url" to it.url)
                        newsList.add(news)
                    }

                    // set list view adapter
                    val from = arrayOf("title", "url")
                    val to = intArrayOf(android.R.id.text1, android.R.id.text2)
                    val adapter = SimpleAdapter(
                        applicationContext,
                        newsList,
                        android.R.layout.simple_list_item_2,
                        from,
                        to
                    )
                    lvNews.adapter = adapter
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.i("failure getting news: ", t.message)
            }
        })
    }
}