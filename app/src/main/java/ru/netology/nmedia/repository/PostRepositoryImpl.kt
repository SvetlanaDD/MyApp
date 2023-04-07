package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import ru.netology.nmedia.api.PostsApi
import ru.netology.nmedia.dao.PostDao
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.entity.PostEntity
import ru.netology.nmedia.entity.toEntity
import java.io.IOException

class PostRepositoryImpl(private val postDao: PostDao): PostRepository {
    override val data: LiveData<List<Post>> = postDao.getAll().map{it.map(PostEntity::toDto)}

    override suspend fun getAll() {
        try {
            val response = PostsApi.retrofitService.getAll()
            if (!response.isSuccessful) {
                throw Exception ("Api error")
            }

            val body = response.body() ?: throw Exception("Body is null")
            postDao.insert(body.toEntity())
        } catch (e: IOException) {
            throw Exception ("Network error")
        } catch (e: Exception) {
            throw Exception ("Unknown error")
        }

    }

    override suspend fun likedById(id: Long, likeByMe: Boolean) {

        try {
            if (!likeByMe) {val response = PostsApi.retrofitService.likeById(id)
                if (!response.isSuccessful) {
                    throw Exception ("Api error")
                }}
            else {val response = PostsApi.retrofitService.dislikeById(id)
                if (!response.isSuccessful) {
                    throw Exception ("Api error")
                }}

            postDao.likeById(id)
        } catch (e: IOException) {
            throw Exception ("Network error")
        } catch (e: Exception) {
            throw Exception ("Unknown error")
        }
    }

    override suspend fun save(post: Post) {
        try {
            val response = PostsApi.retrofitService.save(post)
            if (!response.isSuccessful) {
                throw Exception ("Api error")
            }

            val body = response.body() ?: throw Exception("Body is null")
            postDao.insert(PostEntity.fromDto(body))
        } catch (e: IOException) {
            throw Exception ("Network error")
        } catch (e: Exception) {
            throw Exception ("Unknown error")
        }
    }

    override suspend fun removeById(id: Long) {
        try {
            val response = PostsApi.retrofitService.removeById(id)
            if (!response.isSuccessful) {
                throw Exception ("Api error")
            }

            postDao.removeById(id)
        } catch (e: IOException) {
            throw Exception ("Network error")
        } catch (e: Exception) {
            throw Exception ("Unknown error")
        }
    }

}
