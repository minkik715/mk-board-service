package io.github.minkik715.board.mkboardservice.dto

data class CategoryDTO(
    val id: Long,
    val name: String,
    val sortStatus: SortStatus? = null,
    val searchCount: Int? = null,
    val pageOffSet: Int? = null,
){
    enum class SortStatus {
        CATEGORIES, NEWEST, OLDEST
    }

}
