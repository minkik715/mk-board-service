package io.github.minkik715.board.mkboardservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.client.HttpStatusCodeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException(id: Any) : HttpStatusCodeException(HttpStatus.NOT_FOUND, "User not found: $id") {

}
