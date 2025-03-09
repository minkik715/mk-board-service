package io.github.minkik715.board.mkboardservice.dto

data class UserUpdatePasswordRequest(
    val beforePassword: String,
    val afterPassword: String
)