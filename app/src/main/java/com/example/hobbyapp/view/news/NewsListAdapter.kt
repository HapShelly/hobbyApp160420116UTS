package com.example.hobbyapp.view.news

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.NewsListItemBinding
import com.example.hobbyapp.model.News
import com.example.hobbyapp.util.loadImage

class  NewsListAdapter:RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {
    private val newsList = ArrayList<News>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]

        val imageViewNews = holder.itemView.findViewById<ImageView>(R.id.imageViewNews)
        val progressLoadImage = holder.itemView.findViewById<ProgressBar>(R.id.progressLoadImage)
        imageViewNews.loadImage(news.imgUrl, progressLoadImage)

        with(holder.binding){
            txtTitle.text = news.title
            txtAuthor.text = "by @${news.author}"
            textPreview.text = news.preview

            buttonRead.setOnClickListener {
                val action = NewsListFragmentDirections.actionNewsDetail(news)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun updateNewsList(newestNewsList: List<News>){
        newsList.clear()
        newsList.addAll(newestNewsList)
        notifyDataSetChanged()
    }

    class NewsViewHolder(var binding: NewsListItemBinding): RecyclerView.ViewHolder(binding.root)

}