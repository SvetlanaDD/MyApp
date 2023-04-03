package ru.netology.nmedia.repository

import ru.netology.nmedia.dto.Post

interface PostRepository {
    fun getAllAsync(callback: AsyncCallback <List<Post>>)
    fun likedByIdAsync(id: Long,  likedByMe: Boolean, callback: AsyncCallback <Post>)
    fun saveAsync(post: Post, callback: AsyncCallback <Post>)
    fun removeByIdAsync(id: Long, callback: AsyncCallback <Unit>)

    interface AsyncCallback <T>{
        fun onSuccess(posts: T) {}
        fun onError(e: Exception) {}
    }
}
