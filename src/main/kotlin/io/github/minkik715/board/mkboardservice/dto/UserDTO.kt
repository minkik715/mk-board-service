package io.github.minkik715.board.mkboardservice.dto

import io.github.minkik715.board.mkboardservice.entity.UserEntity
import jakarta.validation.constraints.NotBlank
import java.util.Date

data class UserDTO(
    val id: Long,
    @NotBlank(message = "Name is required")
    val userId: String,
    val isAdmin: Boolean,
    @NotBlank(message = "Name is required")
    var password: String,
    @NotBlank(message = "Name is required")
    val nickname: String,
    var createTime: Date = Date(),
    val isWithDraw: Boolean = false,
    val status: UserStatus = UserStatus.DEFAULT,
    val updateTime: Date = Date()
){
    enum class UserStatus {
        DEFAULT, ADMIN, DELETED
    }

    fun toEntity(): UserEntity {
        return UserEntity(
            userId = this.userId,
            isAdmin = this.isAdmin,
            password = this.password,
            nickname = this.nickname,
            isWithDraw = this.isWithDraw,
            status = this.status,
            createTime = this.createTime,
            updateTime = this.updateTime
        )
    }
}
