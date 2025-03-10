package io.github.minkik715.board.mkboardservice.aop

import SessionUtil
import io.github.minkik715.board.mkboardservice.exception.UserSessionNotFoundException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class LoginCheckAspect {

    val log = LoggerFactory.getLogger(LoginCheckAspect::class.java)

    @Around("@annotation(io.github.minkik715.board.mkboardservice.aop.LoginCheck) && @annotation(loginCheck)")
    fun adminLoginCheck(joinPoint: ProceedingJoinPoint, loginCheck: LoginCheck) {
        val session = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request.session
        val userType = loginCheck.type

        var id : Long? = null;
        when(userType) {
            LoginCheck.UserType.ADMIN -> {
                id = SessionUtil.getLoginAdminId(session)
            }
            LoginCheck.UserType.USER -> {
                id = SessionUtil.getLoginMemberId(session)
            }
        }
        if(id == null) {
            log.error("Failed to find user in session ${session.id}")
            throw UserSessionNotFoundException()
        }
        val idIdx = 0;
        joinPoint.args.set(idIdx, id)
        joinPoint.proceed(joinPoint.args)
    }

}