package io.github.minkik715.board.mkboardservice.dto

import jakarta.validation.constraints.NotBlank

data class UserLoginRequest(
    @NotBlank(message = "User ID cannot be blank")
    val userId: String,
    @NotBlank(message = "User ID cannot be blank")
    val password: String
)
