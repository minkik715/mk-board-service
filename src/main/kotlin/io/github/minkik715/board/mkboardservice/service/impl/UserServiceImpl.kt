package io.github.minkik715.board.mkboardservice.service.impl

import io.github.minkik715.board.mkboardservice.dto.UserDTO
import io.github.minkik715.board.mkboardservice.dto.UserLoginRequest
import io.github.minkik715.board.mkboardservice.exception.DuplicateUserIdException
import io.github.minkik715.board.mkboardservice.exception.UserNotFoundException
import io.github.minkik715.board.mkboardservice.repository.UserRepository
import io.github.minkik715.board.mkboardservice.service.UserService
import io.github.minkik715.board.mkboardservice.utils.SHA256Util
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {
    override fun register(userProfile: UserDTO): UserDTO {
        val duplicated = isDuplicatedId(userProfile.userId)
        if(duplicated){
           throw DuplicateUserIdException(userProfile.userId)
        }
        userProfile.password = SHA256Util.encryptSHA256(userProfile.password)
        return userRepository.save(userProfile.toEntity()).toDto()
    }

    override fun login(userLoginRequest: UserLoginRequest): UserDTO {
        return userRepository.findByUserIdAndPassword(
            userLoginRequest.userId, SHA256Util.encryptSHA256(userLoginRequest.password)
        )?.toDto() ?:throw UserNotFoundException(userLoginRequest.userId)
    }

    override fun isDuplicatedId(userId: String): Boolean {
        return userRepository.existsByUserId(userId)
    }

    override fun getUserInfo(id: Long): UserDTO {
        return userRepository.findByIdAndStatus(id)?.toDto() ?: throw UserNotFoundException(id)
    }

    override fun updatePassword(id: Long, beforePassword: String, afterPassword: String): Boolean {
        val entity = userRepository.findByIdAndStatus(id)?: throw UserNotFoundException(id)
        return entity.updatePassword(SHA256Util.encryptSHA256(afterPassword))
    }


    @Transactional
    override fun deleteUser(id: Long): Boolean {
        val entity = userRepository.findByIdAndStatus(id)?: throw UserNotFoundException(id)
        return entity.updateStatus(status = UserDTO.UserStatus.DELETED)
    }

}