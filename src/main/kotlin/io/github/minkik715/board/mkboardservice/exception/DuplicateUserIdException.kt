package io.github.minkik715.board.mkboardservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.client.HttpStatusCodeException

@ResponseStatus(HttpStatus.BAD_REQUEST)
class DuplicateUserIdException(id: String) :  HttpStatusCodeException(HttpStatus.BAD_REQUEST,"Duplicated user id: $id")
