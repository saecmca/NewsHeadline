package com.example.news

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    lateinit var arrNews: ArrayList<Articles>
    lateinit var mcontext: Context


    constructor(arrNews: ArrayList<Articles>, mcontext: Context) : this() {
        this.mcontext = mcontext
        this.arrNews = arrNews
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.news_adapter, parent, false)


        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val eachKeepReadItem = arrNews.get(position)
            if(!TextUtils.isEmpty(eachKeepReadItem.urlToImage))
            Picasso.get().load(eachKeepReadItem.urlToImage).placeholder(R.drawable.placeholder)
                .into(holder.imSinglenews)
            holder.tvTitle.setText(eachKeepReadItem.title)
            if(!TextUtils.isEmpty(eachKeepReadItem.source.name))
            holder.tvSource.setText(eachKeepReadItem.source.name)
            holder.tvDate.text = (eachKeepReadItem.publishedAt)
            holder.relayout.setOnClickListener {
                var intente = Intent(mcontext, NewsDetails::class.java)
                intente.putExtra("image", eachKeepReadItem.urlToImage)
                intente.putExtra("title", eachKeepReadItem.title)
                intente.putExtra("desc", eachKeepReadItem.description)
                intente.putExtra("source", eachKeepReadItem.source.name)
                intente.putExtra("datetime", eachKeepReadItem.publishedAt)
                mcontext.startActivity(intente)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun getItemCount(): Int {
        return arrNews.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle = itemView.findViewById<View>(R.id.tvTitle) as TextView
        var tvSource = itemView.findViewById<View>(R.id.tvsource) as TextView
        var tvDate = itemView.findViewById<View>(R.id.tvDate) as TextView
        var imSinglenews = itemView.findViewById<View>(R.id.ivImage) as ImageView
        var relayout = itemView.findViewById<View>(R.id.relay) as RelativeLayout
    }
}
