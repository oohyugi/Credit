package com.yogi.credit.feature.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.*

import com.yogi.credit.R
import com.yogi.credit.data.model.HomeMdl
import com.yogi.credit.utils.MarginItemDecoration
import com.yogi.credit.utils.convertDpToPixel

/**
 * Created by oohyugi on 2019-09-07.
 * github: https://github.com/oohyugi
 */
class HomeAdapter() :
    ListAdapter<HomeMdl, RecyclerView.ViewHolder>(DiffUtilsHomeAdapter()) {


    private val ITEM_TYPE_PRODUCT = 0
    private val ITEM_TYPE_ARTICLE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ITEM_TYPE_PRODUCT -> return HomeViewHolder.from(parent)
            else -> return HomeViewHolderSection.from(parent)
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = getItem(position)

        when (holder) {
            is HomeViewHolderSection -> holder.bind(data)
            is HomeViewHolder -> holder.bind(data)
        }


    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).section) {
            "products" -> ITEM_TYPE_PRODUCT
            else -> ITEM_TYPE_ARTICLE
        }
    }


    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val rvContent = itemView.findViewById<RecyclerView>(R.id.rv_content)
        private lateinit var mAdapterProduct: ProductAdapter

        companion object {
            fun from(parent: ViewGroup): HomeViewHolder {
                val context = parent.context
                val inflater = LayoutInflater.from(context)
                val view = inflater.inflate(R.layout.item_home, parent, false)


                return HomeViewHolder(view)
            }

        }

        fun bind(data: HomeMdl?) {
            mAdapterProduct = ProductAdapter()
            data?.items.let {
                mAdapterProduct.submitList(it)
            }

            val mLayoutManager = GridLayoutManager(itemView.context, 3)
            rvContent.apply {
                layoutManager = mLayoutManager
                adapter = mAdapterProduct
                addItemDecoration(MarginItemDecoration(14,MarginItemDecoration.TYPE_GRID_LAYOUT,3))
            }

        }

    }


    class HomeViewHolderSection(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvSection = itemView.findViewById<TextView>(R.id.tv_section)
        private val rvContent = itemView.findViewById<RecyclerView>(R.id.rv_content)
        lateinit var mArticleAdapter: ArticleAdapter

        companion object {
            fun from(parent: ViewGroup): HomeViewHolderSection {
                val context = parent.context
                val inflater = LayoutInflater.from(context)
                val view = inflater.inflate(R.layout.item_home_section, parent, false)


                return HomeViewHolderSection(view)
            }

        }

        fun bind(data: HomeMdl?) {

            if (data?.sectionTitle != null) {
                tvSection.text = data.sectionTitle
            }

            mArticleAdapter = ArticleAdapter()
            val mLayoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
            data?.items.let {
                mArticleAdapter.submitList(it)
            }
            rvContent?.apply {
                layoutManager = mLayoutManager
                adapter = mArticleAdapter
                addItemDecoration(
                    MarginItemDecoration(
                        16,
                        MarginItemDecoration.TYPE_LINEARLAYOUT_VERTICAL
                    )
                )
            }


        }


    }

    class DiffUtilsHomeAdapter : DiffUtil.ItemCallback<HomeMdl>() {
        override fun areItemsTheSame(oldItem: HomeMdl, newItem: HomeMdl): Boolean {
            return oldItem.section == newItem.section
        }

        override fun areContentsTheSame(oldItem: HomeMdl, newItem: HomeMdl): Boolean {
            return oldItem == newItem

        }

    }


}