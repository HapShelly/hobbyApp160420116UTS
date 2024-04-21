package com.example.hobbyapp.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hobbyapp.databinding.FragmentNewsListBinding
import com.example.hobbyapp.viewmodel.NewsViewModel

class NewsListFragment : Fragment() {
    private lateinit var viewModel:NewsViewModel
    private val newsListAdapter = NewsListAdapter(arrayListOf())
    private lateinit var binding:FragmentNewsListBinding
    lateinit var shared: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        var sharedFile = requireActivity().packageName
        shared = requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        var savedUsername = shared.getString(LoginFragment.EXTRA_USERNAME, "")
        savedUsername?.let {
            if (it.isEmpty()) {
                val action = NewsListFragmentDirections.actionLoginFromNewsList()
                Navigation.findNavController(view).navigate(action)

            } else {
                viewModel.refresh()
                binding.refreshLayout.isRefreshing = false
                binding.recView.layoutManager = LinearLayoutManager(context)
                binding.recView.adapter = newsListAdapter

                observeViewModel()
            }
        }

        binding.refreshLayout.setOnRefreshListener {
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE

            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.listNewsLD.observe(viewLifecycleOwner, Observer {
            newsListAdapter.updateNewsList(it)
        })

        viewModel.newsLoadingErrorLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.txtError?.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                binding.recView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE

            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })
    }
}