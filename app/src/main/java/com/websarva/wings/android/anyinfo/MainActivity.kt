package com.websarva.wings.android.anyinfo

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebView
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var newsResponse: NewsResponse

    companion object {
        var BaseUrl = "http://newsapi.org/v2/top-headlines/"
        var country = "us"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lvNews = findViewById<ListView>(R.id.lvNews)

//        // If want to use webview, remove comment.
//        // When click list item, moving to news site
//        lvNews.setOnItemClickListener { parent, view, position, id ->
//            val element = parent.getItemAtPosition(position) as MutableMap<String, String>
//            Log.i("url: ", element["url"])
//            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(element["url"] as String))
//            startActivity(browserIntent)
//        }

        // When click list item, moving to news site
        lvNews.setOnItemClickListener { parent, view, position, id ->
            setContentView(R.layout.web);
            webView = findViewById(R.id.web_view);

            // enable javascript
            webView.settings.javaScriptEnabled = true

            val element = parent.getItemAtPosition(position) as MutableMap<String, String>
            webView.loadUrl(element["url"] as String)
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
                    val newsList = arrayListOf<News>()

                    newsResponse.articles?.forEach {
                        var news = News(
                            R.drawable.ichigo,
                            it.title!!,
                            it.url!!,
                            it.description!!,
                            it.publishedAt!!
                        )
                        newsList.add(news)
                    }

                    val adapter = NewsAdapter(applicationContext, newsList)
                    lvNews.adapter = adapter
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.i("failure getting news: ", t.message)
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            // back previous page
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun toLocalDate(
        date: String?,
        dateFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'",
        timeZone: TimeZone = TimeZone.getTimeZone("JST")
    ): Date {
        val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
        parser.timeZone = timeZone
        return parser.parse(date)
    }
}