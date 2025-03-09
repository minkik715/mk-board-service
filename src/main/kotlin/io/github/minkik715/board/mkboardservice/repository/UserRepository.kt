package io.github.minkik715.board.mkboardservice.repository

import io.github.minkik715.board.mkboardservice.dto.UserDTO
import io.github.minkik715.board.mkboardservice.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity, Long> {
    fun existsByUserId(userId: String): Boolean
    fun findByIdAndStatus(id: Long, status: UserDTO.UserStatus = UserDTO.UserStatus.DEFAULT): UserEntity?
    fun findByUserIdAndPassword(userId: String, password: String): UserEntity?
}
