package io.github.minkik715.board.mkboardservice.repository

import io.github.minkik715.board.mkboardservice.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<CategoryEntity, Long> {
}