package io.github.minkik715.board.mkboardservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpStatusCodeException

class PasswordEncryptFailException : HttpStatusCodeException(HttpStatus.INTERNAL_SERVER_ERROR, "Password Encrypt Fail!")
