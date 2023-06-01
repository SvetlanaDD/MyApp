package ru.netology.nmedia.dto

import ru.netology.nmedia.entity.Attachment

data class Post(
    val id: Long,
    val author: String,
    val authorAvatar: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    var likes: Int = 0,
    val attachment: Attachment? = null
)
