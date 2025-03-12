package io.github.minkik715.board.mkboardservice.service

import io.github.minkik715.board.mkboardservice.dto.PostDTO

interface PostService {

    fun register(id: Long, postDTO: PostDTO)

    fun getMyProducts(userId: Long): List<PostDTO>

    fun updateProducts(postDTO: PostDTO)

    fun deleteProduct(userId: Long, productId: Long)
}