package com.example.hobbyapp.view.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hobbyapp.databinding.FragmentNewsDetailBinding
import com.example.hobbyapp.model.AppDB
import com.example.hobbyapp.model.News
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
        viewModel = NewsViewModel(AppDB.invoke(requireContext()))
        val data = arguments?.getParcelable<News>("data")

        binding.txtTitle.text = data?.title
        binding.txtAuthor.text = data?.author
        //binding.txtContent.text = data?.content
        binding.imgUrl = data?.imgUrl

        data?.content?.let {
            val paragraphsPerPage = 2
            content = splitNewsIntoPages(it, paragraphsPerPage)
            maxPage = content.size

            currentPage = 1
            binding.txtPage.text = "$currentPage / $maxPage"
            binding.buttonPrev.isEnabled = false

            if (maxPage == 1) {
                binding.buttonNext.isEnabled = false
            }

            binding.txtContent.text = content[currentPage - 1]

            binding.buttonNext.setOnClickListener {
                if (currentPage < maxPage) {
                    currentPage += 1
                    updateContent()
                }
            }

            binding.buttonPrev.setOnClickListener {
                if (currentPage > 1) {
                    currentPage -= 1
                    updateContent()
                }
            }
        }
    }

    private fun updateContent() {
        binding.txtContent.text = content[currentPage - 1]
        binding.txtPage.text = "$currentPage / $maxPage"
        binding.buttonPrev.isEnabled = currentPage > 1
        binding.buttonNext.isEnabled = currentPage < maxPage
    }

    private fun splitNewsIntoPages(news: String, paragraphsPerPage: Int): List<String> {
        val paragraphs = news.trim().split("\n\n")
        val pages = mutableListOf<String>()

        for (i in paragraphs.indices step paragraphsPerPage) {
            val page = paragraphs.subList(i, minOf(i + paragraphsPerPage, paragraphs.size))
            pages.add(page.joinToString("\n\n"))
        }

        return pages
    }
}