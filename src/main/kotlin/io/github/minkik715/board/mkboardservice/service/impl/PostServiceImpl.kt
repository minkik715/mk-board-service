package io.github.minkik715.board.mkboardservice.service.impl

import io.github.minkik715.board.mkboardservice.dto.PostDTO
import io.github.minkik715.board.mkboardservice.dto.UserDTO
import io.github.minkik715.board.mkboardservice.entity.PostEntity
import io.github.minkik715.board.mkboardservice.repository.PostRepository
import io.github.minkik715.board.mkboardservice.service.PostService
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val userService: UserServiceImpl,
): PostService {

    val log = LoggerFactory.getLogger(PostServiceImpl::class.java)

    @Transactional
    override fun register(userId: Long, postDTO: PostDTO) {
        val memberInfo: UserDTO = userService.getUserInfo(userId)
        postDTO.userId = userId
        postRepository.save(PostEntity(postDTO))
    }

    override fun getMyProducts(userId: Long): List<PostDTO> {
        return postRepository.findAllyByUserId(userId).map { it.toDto() }
    }

    @Transactional
    override fun updateProducts(postDTO: PostDTO) {
        runCatching {
            postRepository.findByPostId(postDTO.id)?.updatePost(postDTO)
                ?: throw IllegalArgumentException("Post not found")
        }.onFailure {
            log.error("updateProducts ERROR! {}", postDTO)
            throw RuntimeException("updateProducts ERROR! 물품 변경 메서드를 확인해주세요\nParams : $postDTO")
        }
    }

    @Transactional
    override fun deleteProduct(userId: Long, productId: Long) {
        runCatching {
            postRepository.deleteById(productId)

        }.onFailure {
            log.error("deleteProudct ERROR! {}", productId)
            throw RuntimeException("updateProducts ERROR! 물품 삭제 메서드를 확인해주세요\nParams : $productId")
        }
    }
}