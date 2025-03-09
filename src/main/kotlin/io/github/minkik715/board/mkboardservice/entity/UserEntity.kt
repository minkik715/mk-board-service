package io.github.minkik715.board.mkboardservice.entity

import io.github.minkik715.board.mkboardservice.dto.UserDTO
import io.github.minkik715.board.mkboardservice.dto.UserDTO.UserStatus
import jakarta.persistence.*
import java.util.*

@Entity
class UserEntity(

    @Column(nullable = false, unique = true)
    private val userId: String = "",
    private val isAdmin: Boolean = false,
    private var password: String = "",
    private val nickname: String = "",
    private val isWithDraw: Boolean = false,
    @Enumerated(EnumType.STRING)
    private var status: UserStatus = UserStatus.DEFAULT,
    private var createTime: Date = Date(),
    private val updateTime: Date = Date()
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L



    fun toDto(): UserDTO {
        return UserDTO(
            id = id,
            userId = userId,
            isAdmin = isAdmin,
            password = password,
            nickname = nickname,
            isWithDraw = isWithDraw,
            status = status,
            createTime = createTime,
            updateTime = updateTime
        )
    }

    fun updateStatus(status: UserStatus): Boolean {
        this.status = status
        return true
    }

    fun updatePassword(password: String):Boolean{
        this.password = password
        return true
    }
}