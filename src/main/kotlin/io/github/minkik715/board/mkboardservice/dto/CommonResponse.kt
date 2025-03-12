package io.github.minkik715.board.mkboardservice.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus

data class CommonResponse<T>(
    @JsonProperty("status") var status: HttpStatus?,
    @JsonProperty("code") var code: String?,
    @JsonProperty("message") var message: String?,
    @JsonProperty("data") var data: T?
)
