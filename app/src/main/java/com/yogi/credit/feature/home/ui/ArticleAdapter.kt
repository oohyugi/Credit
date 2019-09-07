package com.yogi.credit.feature.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yogi.credit.R
import com.yogi.credit.data.model.ItemsMdl

/**
 * Created by oohyugi on 2019-09-07.
 * github: https://github.com/oohyugi
 */
class ArticleAdapter :
    ListAdapter<ItemsMdl, ArticleAdapter.ViewHolder>(DiffUtilsHomeAdapter()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)

        holder.bind(data)


    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_title_article)
        private val ivArticle = itemView.findViewById<ImageView>(R.id.iv_article)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val context = parent.context
                val inflater = LayoutInflater.from(context)
                val view = inflater.inflate(R.layout.item_article, parent, false)


                return ViewHolder(view)
            }

        }

        fun bind(data: ItemsMdl?) {
            Glide.with(itemView.context).load(data?.articleImage).into(ivArticle)
            tvTitle.text = data?.articleTitle

        }

    }

    class DiffUtilsHomeAdapter : DiffUtil.ItemCallback<ItemsMdl>() {
        override fun areItemsTheSame(oldItem: ItemsMdl, newItem: ItemsMdl): Boolean {
            return oldItem.articleTitle == newItem.articleTitle
        }

        override fun areContentsTheSame(oldItem: ItemsMdl, newItem: ItemsMdl): Boolean {
            return oldItem == newItem

        }

    }


}