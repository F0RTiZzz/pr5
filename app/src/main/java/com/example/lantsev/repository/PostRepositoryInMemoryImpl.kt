package com.example.lantsev.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lantsev.dto.Post

class PostRepositoryInMemoryImpl : PostRepository{
    private var post = Post(
            id = 1,
            author = "ГБПОУ ВО \"БТПИТ\"",
            content = "ЛИДЕР ДВИЖЕНИЯ ПЕРВЫХ\n" +
                    "29 марта студент 2 курса Бараненко Максим Борисоглебского техникума промышленных и информационных технологий совместно с советником директора по воспитанию Алехиной Светланой приняли участие в территориальном этапе регионального конкурса «Лидер Движения Первых Воронежской области», который проходил на базе МОУ Новохоперской СОШ N2.\n" +
                    "Ребята проходили испытания по следующим темам: «Эрудит», «Оратор», «Работа в команде».\n" +
                    "По итогам работы победителем в номинации «Лидер Движения Первых в профессиональных образовательных организациях в возрасте от 16 до 18 лет» признан Бараненко Максим.\n" +
                    "Максим будет представлять наш техникум на региональном этапе конкурса.",
            published = "30 мар в 13:13",
            likedByMe = false,
            likes = 999999,
            share = 999
        )
    private val data = MutableLiveData(post)
    override fun get(): LiveData<Post> = data
    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe)
        if (post.likedByMe) post.likes++ else post.likes--
        data.value = post
    }
    override fun share() {
        post.share++
        data.value = post
    }
}


