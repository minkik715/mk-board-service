package io.github.minkik715.board.mkboardservice.aop


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class LoginCheck(val type: UserType) {

    enum class UserType{
        USER, ADMIN
    }
}
