package io.github.minkik715.board.mkboardservice.service.impl

import io.github.minkik715.board.mkboardservice.dto.CategoryDTO
import io.github.minkik715.board.mkboardservice.entity.CategoryEntity
import io.github.minkik715.board.mkboardservice.repository.CategoryRepository
import io.github.minkik715.board.mkboardservice.service.CategoryService
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository
): CategoryService {

    val log = LoggerFactory.getLogger(CategoryServiceImpl::class.java)

    @Transactional
    override fun register(userId: Long, categoryDTO: CategoryDTO) {
        categoryRepository.save(CategoryEntity(categoryDTO.name))
    }

    @Transactional
    override fun update(categoryDTO: CategoryDTO) {
        categoryRepository.findById(categoryDTO.id).getOrNull()?.let {
            it.updateName(categoryDTO.name)
        }
    }

    @Transactional
    override fun delete(id: Long) {
        categoryRepository.deleteById(id)
    }
}