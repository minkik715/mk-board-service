package io.github.minkik715.board.mkboardservice.entity

import io.github.minkik715.board.mkboardservice.dto.CategoryDTO
import io.github.minkik715.board.mkboardservice.dto.UserDTO
import io.github.minkik715.board.mkboardservice.dto.UserDTO.UserStatus
import jakarta.persistence.*
import java.util.*

@Entity
class CategoryEntity(

    @Column(nullable = false, unique = true)
    private var name: String = "",
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L



    fun toDto(): CategoryDTO {
        return CategoryDTO(
            id = id,
            name = name,
        )
    }

    fun updateName(name: String) {
        this.name = name
    }

}