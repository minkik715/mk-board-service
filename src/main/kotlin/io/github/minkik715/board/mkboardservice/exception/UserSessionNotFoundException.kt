package io.github.minkik715.board.mkboardservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.client.HttpStatusCodeException

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class UserSessionNotFoundException: HttpStatusCodeException(HttpStatus.UNAUTHORIZED,"User not found in session")