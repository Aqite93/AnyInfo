package com.websarva.wings.android.anyinfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class NewsAdapter(var context: Context, var newsList: ArrayList<News>) : BaseAdapter() {
    private class ViewHolder(row: View?) {
        var newsImage: ImageView = row?.findViewById(R.id.news_image) as ImageView
        var newsTitle: TextView = row?.findViewById(R.id.news_title) as TextView
        var newsUrl: TextView = row?.findViewById(R.id.news_url) as TextView
        var newsDescription: TextView = row?.findViewById(R.id.news_description) as TextView
        var newsPublishedAt: TextView = row?.findViewById(R.id.news_published_at) as TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View
        var viewHolder: ViewHolder
        if (convertView == null) {
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.news_list, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var news: News = getItem(position) as News
//        viewHolder.newsImage.setImageResource(news.image)
        return view as View
    }

    override fun getItem(position: Int): News {
        return newsList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return newsList.count()
    }

}
