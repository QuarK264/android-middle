package ru.skillbranch.kotlinexample

import java.math.BigInteger
import java.security.MessageDigest
import java.security.SecureRandom

class User private constructor(
    private val firstName: String,
    private val lastName: String?,
    email: String? = null,
    rawPhone: String? = null,
    meta: Map<String, Any>? = null
) {
    var userInfo: String

    private val fullName
        get() = listOfNotNull(firstName, lastName)
            .joinToString(" ")
            .capitalize()

    private val initials
        get() = listOfNotNull(firstName, lastName)
            .map { it.first().toUpperCase() }
            .joinToString(" ")

    private var phone: String? = null
        set(value) {
            field = value?.replace("""[^+\d]""".toRegex(), "")
        }

    private var _login: String? = null

    var login: String
        get() = _login!!
        set(value) {
            _login = value.toLowerCase()
        }

    private var _salt: String? = null

    var salt: String?
        get() = _salt
        set(value) {
            _salt = value
        }

    private lateinit var _passwordHash: String

    var passwordHash: String
        get() = _passwordHash
        set(value) {
            _passwordHash = value
        }

    var accessCode: String? = null

    constructor(
        firstName: String,
        lastName: String?,
        email: String,
        password: String
    ) : this(firstName, lastName, email, meta = mapOf("auth" to "password")) {
        println("Secondary email constructor")
        _passwordHash = encrypt(password)
    }

    constructor(
        firstName: String,
        lastName: String?,
        rawPhone: String? = null
    ) : this(firstName, lastName, rawPhone = rawPhone, meta = mapOf("auth" to "sms")) {
        println("Secondary phone constructor")
        val code = generateAccessCode()
        _passwordHash = encrypt(code)
        println("Phone passwordHash is $_passwordHash")
        accessCode = code
        sendAccessCodeToUser(rawPhone, code)
    }

    init {
        println("First init block, primary constructor was called")

        check(firstName.isNotBlank()) { "FirstName must not be blank" }
        check(!email.isNullOrBlank() || !rawPhone.isNullOrBlank()) { "Email or phone must not be blank" }

        phone = rawPhone
        login = email ?: phone!!

        userInfo = """
            firstName: $firstName
            lastName: $lastName
            login: $login
            fullName: $fullName
            initials: $initials
            email: $email
            phone: $phone
            meta: $meta
        """.trimIndent()
    }

    fun checkPassword(password: String) = encrypt(password) == _passwordHash.also {
        println("Checking password hash is $it")
    }

    fun changePassword(oldPassword: String, newPassword: String) {
        if (checkPassword(oldPassword)) {
            _passwordHash = encrypt(newPassword)
            if (!accessCode.isNullOrEmpty()) {
                accessCode = newPassword
            }
            println("Password $oldPassword has been changed on new password $newPassword")
        } else {
            throw IllegalArgumentException("The entered password does not match the current password")
        }
    }

    private fun encrypt(password: String): String {
        if (_salt.isNullOrEmpty()) {
            _salt = ByteArray(16).also { SecureRandom().nextBytes(it) }.toString()
        }
        println("Salt while encrypt: $_salt")
        return _salt.plus(password).md5()
    }

    private fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(toByteArray())
        val hexString = BigInteger(1, digest).toString(16)
        return hexString.padStart(32, '0')
    }

    fun generateAccessCode(): String {
        val possible = "ABCDEFGHUJKLMNOPQRSTUVWXYZabcdefghujklmnopqrstuvwxyz0123456789"

        return StringBuilder().apply {
            repeat(6) {
                (possible.indices).random().also {
                    append(possible[it])
                }
            }
        }.toString()
    }

    fun sendAccessCodeToUser(phone: String?, code: String?) {
        println("...... sending access code: $code to $phone")
    }

    companion object Factory {
        fun makeUser(
            fullName: String,
            email: String? = null,
            password: String? = null,
            phone: String? = null
        ): User {
            val (firstName, lastName) = fullName.fullNameToPair()

            return when {
                !phone.isNullOrBlank() -> User(firstName, lastName, phone)
                !email.isNullOrBlank() && !password.isNullOrBlank() -> User(firstName, lastName, email, password)
                else -> throw IllegalArgumentException("Email or phone must be not null or blank")
            }
        }

        private fun String.fullNameToPair(): Pair<String, String?> =
            this
                .split(" ")
                .filter { it.isNotBlank() }
                .run {
                    when (size) {
                        1 -> first() to null
                        2 -> first() to last()
                        else -> throw IllegalArgumentException(
                            "FullName must contain only first and last name, current split result: ${this@fullNameToPair}"
                        )
                    }
                }
    }
}