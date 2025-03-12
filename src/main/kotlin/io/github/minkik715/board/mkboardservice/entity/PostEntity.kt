package io.github.minkik715.board.mkboardservice.entity

import io.github.minkik715.board.mkboardservice.dto.PostDTO
import io.github.minkik715.board.mkboardservice.dto.UserDTO
import io.github.minkik715.board.mkboardservice.dto.UserDTO.UserStatus
import jakarta.persistence.*
import java.util.*

@Entity
class PostEntity(
    @Column(nullable = false, unique = true)
    private var name: String = "",
    private val isAdmin: Boolean = false,
    private var contents: String = "",
    private var views: Int = 0,
    private val categoryId: Long = 0L,
    private val userId: Long = 0L,
    private val fileId: Long = 0L,
    private var createTime: Date = Date(),
    private var updateTime: Date = Date()
){
    constructor(post: PostDTO) : this(
        name = post.name,
        isAdmin = post.isAdmin,
        contents = post.contents,
        views = post.views,
        categoryId = post.categoryId,
        userId = post.userId,
        fileId = post.fileId,
        createTime = post.createTime,
        updateTime = post.updateTime
    )

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L



    fun toDto(): PostDTO {
        return PostDTO(
            id = id,
            name = name,
            contents = contents,
            views =  views,
            userId = userId,
            categoryId = categoryId,
            fileId = fileId,
            isAdmin = isAdmin,
            createTime = createTime,
            updateTime = updateTime
        )
    }

    fun updatePost(dto: PostDTO) {
        this.name = dto.name
        this.name = dto.contents
        this.updateTime = dto.updateTime
    }

    fun updateViews(views: Int) {
        this.views += views
    }
}