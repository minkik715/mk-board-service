package io.github.minkik715.board.mkboardservice.repository

import io.github.minkik715.board.mkboardservice.entity.CategoryEntity
import io.github.minkik715.board.mkboardservice.entity.PostEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PostRepository: JpaRepository<PostEntity, Long> {
    @Query("select p from PostEntity p where p.id = :postId")
    fun findByPostId(postId: Long) : PostEntity?

    fun findAllyByUserId(userId: Long): List<PostEntity>
}