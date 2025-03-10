package io.github.minkik715.board.mkboardservice.controller

import SessionUtil
import io.github.minkik715.board.mkboardservice.aop.LoginCheck
import io.github.minkik715.board.mkboardservice.aop.LoginCheckAspect
import io.github.minkik715.board.mkboardservice.dto.UserDTO
import io.github.minkik715.board.mkboardservice.dto.UserLoginRequest
import io.github.minkik715.board.mkboardservice.dto.UserUpdatePasswordRequest
import io.github.minkik715.board.mkboardservice.exception.UserNotFoundException
import io.github.minkik715.board.mkboardservice.service.UserService
import jakarta.servlet.http.HttpSession
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {
    val log = LoggerFactory.getLogger(UserController::class.java)

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(@RequestBody userDTO: UserDTO): ResponseEntity<UserDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(userDTO))
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    fun signIn(@RequestBody userLoginRequest: UserLoginRequest, session: HttpSession): ResponseEntity<UserDTO> {
        val userProfile = userService.login(userLoginRequest)
        if(userProfile.isAdmin){
            SessionUtil.setLoginAdminId(session, userProfile.id)
        }else{
            SessionUtil.setLoginMemberId(session, userProfile.id)
        }
        return ResponseEntity.ok(userProfile)
    }

    @GetMapping
    fun getMemberInfo(session: HttpSession): ResponseEntity<UserDTO> {
        val id = SessionUtil.getLoginMemberId(session) ?: SessionUtil.getLoginAdminId(session) ?: throw UserNotFoundException(session.id)
        return ResponseEntity.ok(userService.getUserInfo(id))
    }

    @PutMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    fun logout(session: HttpSession){
        SessionUtil.clear(session)
    }

    @PutMapping("/password")
    @LoginCheck(type = LoginCheck.UserType.USER)
    fun updatePassword(id: Long?, @RequestBody passwordUpdateRequest: UserUpdatePasswordRequest): ResponseEntity<Boolean> {
        return ResponseEntity.ok(
            userService.updatePassword(
                id!!,
                passwordUpdateRequest.beforePassword,
                passwordUpdateRequest.afterPassword
            )
        )
    }

    @DeleteMapping
    fun deleteUser(session: HttpSession): ResponseEntity<Boolean> {
        val id = SessionUtil.getLoginMemberId(session) ?: SessionUtil.getLoginAdminId(session) ?: throw UserNotFoundException(session.id)
        SessionUtil.clear(session)
        return ResponseEntity.ok(
            userService.deleteUser(id)
        )
    }
}