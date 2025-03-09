package io.github.minkik715.board.mkboardservice.utils

import io.github.minkik715.board.mkboardservice.exception.PasswordEncryptFailException
import org.slf4j.LoggerFactory
import java.security.MessageDigest

object SHA256Util {

    private const val ENCRYPTION_TYPE: String = "SHA-256"
    private const val ENCRYPTION_KEY = "mk-board-encryption-key"
    private val log = LoggerFactory.getLogger(SHA256Util::class.java)
    fun encryptSHA256(value: String): String {
        return runCatching {
            val sh = MessageDigest.getInstance(ENCRYPTION_TYPE)
            sh.update(value.toByteArray())
            val digest = sh.digest()
            val sb = StringBuffer()
            for (b in digest) {
                sb.append(Integer.toString((b.toInt() and 0xff) + 0x100, 16).substring(1))
            }
            sb.toString()
        }.onFailure {
            log.error("encryptSHA256 error $it")
            throw PasswordEncryptFailException()
        }.getOrThrow()
    }
}