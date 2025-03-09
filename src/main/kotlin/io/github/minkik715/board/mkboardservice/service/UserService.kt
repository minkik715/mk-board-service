package io.github.minkik715.board.mkboardservice.service

import io.github.minkik715.board.mkboardservice.dto.UserDTO
import io.github.minkik715.board.mkboardservice.dto.UserLoginRequest

interface UserService {
    fun register(userProfile: UserDTO): UserDTO;

    fun login(userLoginRequest: UserLoginRequest): UserDTO;

    fun isDuplicatedId(id: String): Boolean;

    fun getUserInfo(id: Long): UserDTO;

    fun updatePassword( id: Long, beforePassword: String, afterPassword: String): Boolean

    fun deleteUser(id: Long): Boolean;
}