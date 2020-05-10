package com.websarva.wings.android.anyinfo.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.websarva.wings.android.anyinfo.NewsResponse
import com.websarva.wings.android.anyinfo.NewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DashboardViewModel : ViewModel() {
    companion object {
        var BaseUrl = "http://newsapi.org/v2/top-headlines"
        var country = "us"
    }

    private val _text = MutableLiveData<String>().apply {
        getNewsHeadline()
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    internal fun getNewsHeadline() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(NewsService::class.java)
        val call = service.getNewsHeadlines(country)
        call.enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>?, response: Response<NewsResponse>?) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<NewsResponse>?, t: Throwable?) {
                TODO("Not yet implemented")
            }
        })
    }
}