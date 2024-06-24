package com.example.hobbyapp.view.news

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.FragmentNewsListBinding
import com.example.hobbyapp.model.AppDB
import com.example.hobbyapp.viewmodel.NewsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsListFragment : Fragment(), NewsListEvent {
    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsListBinding
    lateinit var shared: SharedPreferences
    val adapter = NewsListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = NewsViewModel(AppDB.invoke(requireContext()))
        var sharedFile = requireActivity().packageName
        var shared: SharedPreferences =
            requireActivity().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        if (shared.getString("username", null) == null) {
            findNavController().navigate(R.id.actionLoginFromNewsList)
        }

        binding.listAdapter = adapter
        binding.viewModel = viewModel
        binding.eventHandler = this
        binding.lifecycleOwner = this

        CoroutineScope(Dispatchers.IO).launch{
            viewModel.getNews()
        }
        viewModel.listNewsLD.observe(viewLifecycleOwner){
            adapter.updateNewsList(it.toList())
        }

    }

    override fun onAddBtnClick() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.addNews()
        }
    }

    override fun onItemClick() {
        TODO("Not yet implemented")
    }
}