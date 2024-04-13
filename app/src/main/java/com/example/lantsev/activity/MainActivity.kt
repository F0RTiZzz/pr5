package com.example.lantsev.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.lantsev.R
import com.example.lantsev.databinding.ActivityMainBinding
import com.example.lantsev.viewmodel.PostViewModel
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                textt.text = post.content
                textlike.text = post.likes.toString()
                textshare.text = post.share.toString()
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                textlike.text = post.likes.toString()
                when {
                    post.likes in 1000..999999 -> textlike.text = "${post.likes / 1000}K"
                    post.likes < 1000 -> textlike.text = post.likes.toString()
                    else -> textlike.text = String.format("%.1fM", post.likes.toDouble() / 1000000)
                }
                textshare.text = post.share.toString()
                when {
                    post.share < 1000 -> textshare.text = post.share.toString()
                    post.share in 1000..999999 -> textshare.text = "${post.share / 1000}K"
                    else -> textshare.text = String.format(
                        "%.1fM", post.share.toDouble() / 1000000
                    )
                }

            }
            binding.like.setOnClickListener {
                viewModel.like()
            }
            binding.share.setOnClickListener {
                viewModel.share()
            }
        }

    }
}
@MainThread
public inline fun <reified VM : ViewModel> ComponentActivity.viewModels(
    noinline extrasProducer: (() -> CreationExtras)? = null,
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }

    return ViewModelLazy(
        VM::class,
        { viewModelStore },
        factoryPromise,
        { extrasProducer?.invoke() ?: this.defaultViewModelCreationExtras }
    )
}