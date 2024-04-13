package com.example.lantsev.repository
import androidx.lifecycle.LiveData
import com.example.lantsev.dto.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun share()
}