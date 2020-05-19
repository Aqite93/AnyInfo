package com.websarva.wings.android.anyinfo

import android.content.Context
import android.content.pm.LauncherActivityInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class NewsAdapter(var context: Context, var newsList: ArrayList<News>) : BaseAdapter() {
    private class ViewHolder(view: View?) {
        var newsImage: ImageView = view?.findViewById(R.id.news_image) as ImageView
        var newsTitle: TextView = view?.findViewById(R.id.news_title) as TextView
        var newsUrl: TextView = view?.findViewById(R.id.news_url) as TextView
        var newsDescription: TextView = view?.findViewById(R.id.news_description) as TextView
        var newsPublishedAt: TextView = view?.findViewById(R.id.news_published_at) as TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder: ViewHolder
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.news_list, null)
            viewHolder = ViewHolder(convertView)
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder.newsImage.setImageResource(newsList[position].image!!)
        viewHolder.newsTitle.text = newsList[position].title
        viewHolder.newsUrl.text = newsList[position].url
        viewHolder.newsDescription.text = newsList[position].description
        viewHolder.newsPublishedAt.text = newsList[position].published_at

        return convertView!!
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
