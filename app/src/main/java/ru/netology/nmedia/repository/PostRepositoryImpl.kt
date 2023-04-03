package ru.netology.nmedia.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.netology.nmedia.api.PostsApi
import ru.netology.nmedia.dto.Post

class PostRepositoryImpl: PostRepository {

    override fun getAllAsync(callback: PostRepository.AsyncCallback<List<Post>>) {

        PostsApi.retrofitService.getAll().enqueue(object : Callback <List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful) {
                    if (response.code() in 300 .. 399) {
                        callback.onError(java.lang.RuntimeException("${response.code()} перенаправление"))
                    }
                    if (response.code() in 400 .. 499) {
                        callback.onError(java.lang.RuntimeException("${response.code()} ошибка клиента"))
                    }
                    if (response.code() in 500 .. 599) {
                        callback.onError(java.lang.RuntimeException("${response.code()} ошибка сервера"))
                    }

                    callback.onError(java.lang.RuntimeException(response.message()))
                    return
                }

                callback.onSuccess(response.body() ?: throw java.lang.RuntimeException("body is null"))
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                callback.onError(java.lang.RuntimeException(t))
            }
        })
    }

    override fun likedByIdAsync(id: Long, likedByMe: Boolean, callback: PostRepository.AsyncCallback<Post>) {
        if (!likedByMe){
            PostsApi.retrofitService.likeById(id).enqueue(object : Callback <Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (!response.isSuccessful) {
                        if (response.code() in 300 .. 399) callback.onError(java.lang.RuntimeException( "${response.code()} перенаправление" ))
                        if (response.code() in 400 .. 499) callback.onError(java.lang.RuntimeException( "${response.code()} ошибка клиента" ))

                        callback.onError(java.lang.RuntimeException(response.message()))
                        return
                    }

                    callback.onSuccess(response.body() ?: throw java.lang.RuntimeException("body is null"))
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    callback.onError(java.lang.RuntimeException(t))
                }
            })
        }else{
            PostsApi.retrofitService.dislikeById(id).enqueue(object : Callback <Post> {
                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (!response.isSuccessful) {
                        if (response.code() in 300 .. 399) callback.onError(java.lang.RuntimeException( "${response.code()} перенаправление" ))
                        if (response.code() in 400 .. 499) callback.onError(java.lang.RuntimeException( "${response.code()} ошибка клиента" ))

                        callback.onError(java.lang.RuntimeException(response.message()))
                        return
                    }

                    callback.onSuccess(response.body() ?: throw java.lang.RuntimeException("body is null"))
                }

                override fun onFailure(call: Call<Post>, t: Throwable) {
                    callback.onError(java.lang.RuntimeException(t))
                }
            })
        }

    }

    override fun saveAsync(post: Post, callback: PostRepository.AsyncCallback<Post>) {

        PostsApi.retrofitService.save(post).enqueue(object : Callback <Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (!response.isSuccessful) {
                    if (response.code() in 300 .. 399) callback.onError(java.lang.RuntimeException( "${response.code()} перенаправление" ))
                    if (response.code() in 400 .. 499) callback.onError(java.lang.RuntimeException( "${response.code()} ошибка клиента" ))

                    callback.onError(java.lang.RuntimeException(response.message()))
                    return
                }

                callback.onSuccess(response.body() ?: throw java.lang.RuntimeException("body is null"))
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                callback.onError(java.lang.RuntimeException(t))
            }
        })
    }

    override fun removeByIdAsync(id: Long, callback: PostRepository.AsyncCallback<Unit>) {

        PostsApi.retrofitService.removeById(id).enqueue(object : Callback <Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (!response.isSuccessful) {
                    if (response.code() in 300 .. 399) callback.onError(java.lang.RuntimeException( "${response.code()} перенаправление" ))
                    if (response.code() in 400 .. 499) callback.onError(java.lang.RuntimeException( "${response.code()} ошибка клиента" ))

                    callback.onError(java.lang.RuntimeException(response.message()))
                    return
                }

                callback.onSuccess(response.body() ?: throw java.lang.RuntimeException("body is null"))
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                callback.onError(java.lang.RuntimeException(t))
            }
        })
    }
}
