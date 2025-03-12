package io.github.minkik715.board.mkboardservice.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class PostDTO(
    val id: Long,
    val name: String,
    val isAdmin: Boolean,
    val contents: String,
    val views: Int,
    val categoryId: Long,
    var userId: Long,
    val fileId: Long,
    val createTime: Date = Date(),
    val updateTime: Date = Date()
)
