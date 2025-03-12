package io.github.minkik715.board.mkboardservice.service

import io.github.minkik715.board.mkboardservice.dto.CategoryDTO

interface CategoryService {
    fun register(userId: Long, categoryDTO: CategoryDTO)
    fun update(categoryDTO: CategoryDTO)
    fun delete(id: Long)
}