package com.example.hobbyapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hobbyapp.databinding.FragmentNewsDetailBinding
import com.example.hobbyapp.util.loadImage
import com.example.hobbyapp.viewmodel.NewsViewModel

class NewsDetailFragment : Fragment() {
    private lateinit var binding:FragmentNewsDetailBinding
    private lateinit var viewModel: NewsViewModel
    private var currentPage = 1
    private var maxPage = 1
    private var content: List<String> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        val newsId = NewsDetailFragmentArgs.fromBundle(requireArguments()).newsid
        viewModel.getNews(newsId)

        viewModel.newsLD.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Log.d("get news", it.toString())

                binding.imageViewNews.loadImage(it.imgUrl, binding.progressLoadImage)
                binding.txtTitle.text = it.title
                binding.txtAuthor.text = "by @${it.author}"

                val paragrapPerPage = 2
                content = splitNewsIntoPages(it.content!!, paragrapPerPage)
                maxPage = content.size

                currentPage = 1
                binding.txtPage.text = "$currentPage / $maxPage"
                binding.buttonPrev.isEnabled = false;

                if (maxPage == 1) {
                    binding.buttonNext.isEnabled = false
                }

                binding.txtContent.text = content[currentPage - 1]
            }
        })

        binding.buttonNext.setOnClickListener {
            currentPage += 1

            if (currentPage == maxPage){
                binding.buttonNext.isEnabled = false
            }

            if (currentPage > 1){
                binding.buttonPrev.isEnabled = true
            }

            binding.txtContent.text = content[currentPage - 1]
            binding.txtPage.text = "$currentPage / $maxPage"
        }

        binding.buttonPrev.setOnClickListener {
            currentPage -= 1

            if (currentPage == 1){
                binding.buttonPrev.isEnabled = false
            }

            if (currentPage < maxPage){
                binding.buttonNext.isEnabled = true
            }

            binding.txtContent.text = content[currentPage - 1]
            binding.txtPage.text = "$currentPage / $maxPage"
        }
    }

    private fun splitNewsIntoPages(news: String, paragraphsPerPage: Int): List<String> {
        val paragraphs = news.trim().split("\n\n")
        val numPages = paragraphs.size / paragraphsPerPage + if (paragraphs.size % paragraphsPerPage != 0) 1 else 0

        val pages = arrayListOf<String>()
        for (i in 0 until numPages) {
            val startIdx = i * paragraphsPerPage
            val endIdx = minOf((i + 1) * paragraphsPerPage, paragraphs.size)
            val pageParagraphs = paragraphs.subList(startIdx, endIdx)
            pages.add(pageParagraphs.joinToString("\n\n"))
        }

        return pages
    }
}