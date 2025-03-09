package io.github.minkik715.board.mkboardservice.exception

class UserNotFoundException(id: Any) : RuntimeException("User not found: $id") {

}
