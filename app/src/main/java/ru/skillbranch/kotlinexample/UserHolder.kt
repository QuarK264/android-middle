package ru.skillbranch.kotlinexample

import androidx.annotation.VisibleForTesting

object UserHolder {
    private val map = mutableMapOf<String, User>()

    fun registerUser(fullName: String, email: String, password: String): User {
        val containsKey = map.containsKey(email.toLowerCase())
        if (!containsKey) {
            return User.makeUser(fullName, email, password).also { user -> map[user.login] = user }
        } else {
            throw IllegalArgumentException("A user with this email already exists")
        }
    }

    fun loginUser(login: String, password: String): String? {
        val key = if (login.contains('@')) login else login.replace("""[^+\d]""".toRegex(), "")
        return map[key]?.let {
            if (it.checkPassword(password)) {
                it.userInfo
            } else {
                null
            }
        }
    }

    fun registerUserByPhone(fullName: String, phone: String): User {
        val formatedPhone = phone.replace("""[^+\d]""".toRegex(), "")
        if (!map.containsKey(formatedPhone)) {
            val matches = formatedPhone.matches("""^\+[0-9]{11}""".toRegex())
            if (matches) {
                return User.makeUser(fullName, phone = phone)
                    .also { user -> map[user.login] = user }
            } else {
                throw IllegalArgumentException("Enter a valid phone number starting with a + and containing 11 digits")
            }
        } else {
            throw IllegalArgumentException("A user with this phone already exists")
        }
    }

    fun requestAccessCode(login: String) {
        val phone = login.replace("""[^+\d]""".toRegex(), "")
        map[phone]?.apply {
            val newAccessCode = generateAccessCode()
            changePassword(accessCode!!, newAccessCode)
        }
    }

    fun importUsers(list: List<String>): List<User> {
        val users = mutableListOf<User>()
        list.map { rawUser ->
            val userProps = rawUser
                .split(';')
                .take(4)
                .map { it.trim() }
            val user = if (userProps[3] == "") {
                User.makeUser(userProps[0], userProps[1], "temp")
            } else {
                User.makeUser(userProps[0], phone = userProps[3])
            }
            user.apply {
                val authData = userProps[2].split(":")
                salt = authData[0]
                passwordHash = authData[1]
                userInfo = userInfo.replace("""\{.+\}""".toRegex(), "{src=csv}")
            }
            users.add(user)
        }

        return users
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun clearHolder() {
        map.clear()
    }
}
