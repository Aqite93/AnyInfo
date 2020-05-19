package com.websarva.wings.android.anyinfo

import android.content.Context
import android.content.pm.LauncherActivityInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.news_list.view.*

class NewsAdapter(var context: Context, var newsList: ArrayList<News>) : BaseAdapter() {
    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = layoutInflater.inflate(R.layout.news_list, parent, false)
        view.news_image.setImageResource(newsList[position].image!!)
        view.news_title.text = newsList[position].title
        view.news_url.text = newsList[position].url
        view.news_description.text = newsList[position].description
        view.news_description.text = newsList[position].published_at

        return view
    }

    override fun getItem(position: Int): Any {
        return newsList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return newsList.size
    }

}
