package com.example.lantsev.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lantsev.repository.PostRepository
import com.example.lantsev.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like()=repository.like()
    fun share()=repository.share()
}