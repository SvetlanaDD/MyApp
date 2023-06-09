package ru.netology.nmedia.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val authorAvatar: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    val likes: Int = 0,
    @Embedded
    val attachment: Attachment?
) {
    fun toDto() = Post(id, author, authorAvatar, content, published, likedByMe, likes
        , attachment?.toDto()
    )

    companion object {
        fun fromDto(dto: Post) =
            PostEntity(dto.id, dto.author, dto.authorAvatar, dto.content, dto.published, dto.likedByMe, dto.likes
                , Attachment?.fromDto(dto.attachment)
            )

    }
}

//fun List<PostEntity>.toDto(): List<Post> = map(PostEntity::toDto)
fun List<Post>.toEntity(): List<PostEntity> = map(PostEntity::fromDto)

data class Attachment (
    val url: String,
    val description: String?,
    val type: String//AttachmentType
) {
    fun toDto() = Attachment(url, description, type)

    companion object {
        fun fromDto(dto: Attachment?) = dto?.let {
            Attachment(it.url, it.description, it.type)
        }
    }
}

enum class AttachmentType {
    IMAGE
}