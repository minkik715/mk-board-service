
import jakarta.servlet.http.HttpSession

object SessionUtil {
    private const val LOGIN_MEMBER_ID = "LOGIN_MEMBER_ID"
    private const val LOGIN_ADMIN_ID = "LOGIN_ADMIN_ID"
    fun getLoginMemberId(session: HttpSession): Long? {
        return session.getAttribute(LOGIN_MEMBER_ID) as Long?
    }

    fun setLoginMemberId(session: HttpSession, id: Long) {
        session.setAttribute(LOGIN_MEMBER_ID, id)
    }

    fun getLoginAdminId(session: HttpSession): Long? {
        return session.getAttribute(LOGIN_ADMIN_ID) as Long?
    }

    fun setLoginAdminId(session: HttpSession, id: Long) {
        session.setAttribute(LOGIN_ADMIN_ID, id)
    }

    fun clear(session: HttpSession) {
        session.invalidate()
    }
}