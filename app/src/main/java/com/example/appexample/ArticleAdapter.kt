package com.example.appexample

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ArticleAdapter(
    private val context: Context,
    recyclerView: RecyclerView
) : BaseAdapter<BaseModel>(recyclerView) {
        override fun getViewHolder(viewType: Int, itemView: View): BaseViewHolder<BaseModel> {
            // Log.e("pppp", "getViewHolder") //5
            return when (viewType) {
                R.layout.card_ads -> AdsHolder(itemView)
                R.layout.layout_progress_bar -> LoadingHolder(itemView)
                else -> ArticleHolder(itemView)
            }
        }

        override fun setItemViewType(position: Int, items: MutableList<BaseModel>): Int {
            return when (position) {
                0 -> R.layout.card_article_red
                else -> items[position].getLayoutType()
            }
        }

        override fun getProgressbar(): MutableList<BaseModel> {
            return LoadingModel.addProgress()
        }

        inner class ArticleHolder(itemView: View) : BaseViewHolder<BaseModel>(itemView) {
            override fun bindItem(item: BaseModel) {
                if (item !is ArticlesModel) return
                itemView.findViewById<TextView>(R.id.tv_title)?.text = item.title
                itemView.findViewById<TextView>(R.id.tv_description)?.text = item.description

                itemView.setOnClickListener {
                    Toast.makeText(context, adapterPosition.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }

    inner class AdsHolder(itemView: View) : BaseViewHolder<BaseModel>(itemView) {
        override fun bindItem(item: BaseModel) {
            if (item !is AdsModel) return

            itemView.setOnClickListener {
                Toast.makeText(context, item.url, Toast.LENGTH_SHORT).show()
            }

            Picasso.with(context)
                .load(item.url)
                .into(itemView.findViewById<ImageView>(R.id.image_view))

        }
    }

    inner class LoadingHolder(itemView: View) : BaseViewHolder<BaseModel>(itemView) {
        override fun bindItem(item: BaseModel) {
            if (item !is LoadingModel) return
            itemView.findViewById<ProgressBar>(R.id.progress_bar)?.visibility = View.VISIBLE
        }
    }


}