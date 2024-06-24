package com.example.hobbyapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hobbyapp.global.Global
import com.example.hobbyapp.model.AppDB
import com.example.hobbyapp.model.News
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class NewsViewModel(private val roomDb: AppDB) : ViewModel() {
    val listNewsLD = MutableLiveData<ArrayList<News>>()
    val newsDetail = MutableLiveData<News>()

    suspend fun getNews() {
        coroutineScope {
            val result = roomDb.newsDao().getAll()
            viewModelScope.launch {
                if (result.isNotEmpty()) {
                    listNewsLD.value = ArrayList(result)
                }
            }
        }
    }

    suspend fun addNews() {
        coroutineScope {
            Global.listOfNews.forEach {
                roomDb.newsDao().insertNews(it)
            }
            getNews()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}