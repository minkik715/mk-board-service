package io.github.minkik715.board.mkboardservice.controller

import io.github.minkik715.board.mkboardservice.aop.LoginCheck
import io.github.minkik715.board.mkboardservice.dto.CategoryDTO
import io.github.minkik715.board.mkboardservice.dto.CategoryUpdateRequest
import io.github.minkik715.board.mkboardservice.service.CategoryService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/categories")
class CategoryController(
    val categoryService: CategoryService
) {

    val log = LoggerFactory.getLogger(CategoryController::class.java)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    fun registerCategory(userId: Long?, @RequestBody categoryDTO: CategoryDTO) {
        categoryService.register(userId!!, categoryDTO)
    }

    @PatchMapping("{categoryId}")
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    fun updateCategories(
        userId: Long?,
        @RequestBody request: CategoryUpdateRequest
    ) {
        val categoryDTO =
            CategoryDTO(request.id, request.name)
        categoryService.update(categoryDTO)
    }

    @DeleteMapping("{categoryId}")
    @LoginCheck(type = LoginCheck.UserType.ADMIN)
    fun updateCategories(
        userId: Long?,
        @PathVariable(name = "categoryId") categoryId: Int
    ) {
        categoryService.delete(categoryId.toLong())
    }


}