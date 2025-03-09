package io.github.minkik715.board.mkboardservice.exception

class DuplicateUserIdException(id: String) : RuntimeException("Duplicated user id: $id")
