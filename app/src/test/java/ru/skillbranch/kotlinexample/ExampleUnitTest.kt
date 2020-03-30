package ru.skillbranch.kotlinexample

import org.junit.After
import org.junit.Assert
import org.junit.Test
import ru.skillbranch.kotlinexample.extensions.dropLastUntil

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @After
    fun after(){
        UserHolder.clearHolder()
    }

    @Test
    fun register_user_success() {
        val holder = UserHolder
        val user = holder.registerUser("John Doe", "John_Doe@unknown.com","testPass")
        val expectedInfo = """
            firstName: John
            lastName: Doe
            login: john_doe@unknown.com
            fullName: John Doe
            initials: J D
            email: John_Doe@unknown.com
            phone: null
            meta: {auth=password}
        """.trimIndent()

        Assert.assertEquals(expectedInfo, user.userInfo)
    }

    @Test(expected = IllegalArgumentException::class)
    fun register_user_fail_blank() {
        val holder = UserHolder
        holder.registerUser("", "John_Doe@unknown.com","testPass")
    }

    @Test(expected = IllegalArgumentException::class)
    fun register_user_fail_illegal_name() {
        val holder = UserHolder
        holder.registerUser("John Jr Doe", "John_Doe@unknown.com","testPass")
    }

    @Test(expected = IllegalArgumentException::class)
    fun register_user_fail_illegal_exist() {
        val holder = UserHolder
        holder.registerUser("John Doe", "John_Doe@unknown.com","testPass")
        holder.registerUser("John Doe", "John_Doe@unknown.com","testPass")
    }

    @Test
    fun register_user_by_phone_success() {
        val holder = UserHolder
        val user = holder.registerUserByPhone("John Doe", "+7 (917) 971 11-11")
        val expectedInfo = """
            firstName: John
            lastName: Doe
            login: +79179711111
            fullName: John Doe
            initials: J D
            email: null
            phone: +79179711111
            meta: {auth=sms}
        """.trimIndent()

        Assert.assertEquals(expectedInfo, user.userInfo)
        Assert.assertNotNull(user.accessCode)
        Assert.assertEquals(6, user.accessCode?.length)
    }

    @Test(expected = IllegalArgumentException::class)
    fun register_user_by_phone_fail_blank() {
        val holder = UserHolder
        holder.registerUserByPhone("", "+7 (917) 971 11-11")
    }

    @Test(expected = IllegalArgumentException::class)
    fun register_user_by_phone_fail_illegal_name() {
        val holder = UserHolder
        holder.registerUserByPhone("John Doe", "+7 (XXX) XX XX-XX")
    }

    @Test(expected = IllegalArgumentException::class)
    fun register_user_failby_phone_illegal_exist() {
        val holder = UserHolder
        holder.registerUserByPhone("John Doe", "+7 (917) 971-11-11")
        holder.registerUserByPhone("John Doe", "+7 (917) 971-11-11")
    }

    @Test
    fun login_user_success() {
        val holder = UserHolder
        holder.registerUser("John Doe", "John_Doe@unknown.com","testPass")
        val expectedInfo = """
            firstName: John
            lastName: Doe
            login: john_doe@unknown.com
            fullName: John Doe
            initials: J D
            email: John_Doe@unknown.com
            phone: null
            meta: {auth=password}
        """.trimIndent()

        val successResult =  holder.loginUser("john_doe@unknown.com", "testPass")

        Assert.assertEquals(expectedInfo, successResult)
    }

    @Test
    fun login_user_by_phone_success() {
        val holder = UserHolder
        val user = holder.registerUserByPhone("John Doe", "+7 (917) 971-11-11")
        val expectedInfo = """
            firstName: John
            lastName: Doe
            login: +79179711111
            fullName: John Doe
            initials: J D
            email: null
            phone: +79179711111
            meta: {auth=sms}
        """.trimIndent()

        val successResult =  holder.loginUser("+7 (917) 971-11-11", user.accessCode!!)

        Assert.assertEquals(expectedInfo, successResult)
    }

    @Test
    fun login_user_fail() {
        val holder = UserHolder
        holder.registerUser("John Doe", "John_Doe@unknown.com","testPass")

        val failResult =  holder.loginUser("john_doe@unknown.com", "test")

        Assert.assertNull(failResult)
    }

    @Test
    fun login_user_not_found() {
        val holder = UserHolder
        holder.registerUser("John Doe", "John_Doe@unknown.com","testPass")

        val failResult =  holder.loginUser("john_cena@unknown.com", "test")

        Assert.assertNull(failResult)
    }

    @Test
    fun request_access_code() {
        val holder = UserHolder
        val user = holder.registerUserByPhone("John Doe", "+7 (917) 971-11-11")
        val oldAccess = user.accessCode
        holder.requestAccessCode("+7 (917) 971-11-11")

        val expectedInfo = """
            firstName: John
            lastName: Doe
            login: +79179711111
            fullName: John Doe
            initials: J D
            email: null
            phone: +79179711111
            meta: {auth=sms}
        """.trimIndent()

        val successResult =  holder.loginUser("+7 (917) 971-11-11", user.accessCode!!)

        Assert.assertNotEquals(oldAccess, user.accessCode!!)
        Assert.assertEquals(expectedInfo, successResult)
    }

    @Test
    fun test_csv() {
        val result = UserHolder.importUsers(
                listOf(
                    " John Doe ;JohnDoe@unknown.com;[B@7591083d:c6adb4becdc64e92857e1e2a0fd6af84;;",
                    "  Mark Smith;;[B@7621083d:c6adbge5cdc64e9285e1e2a0fd6af843;+79524782585;"
                )
            )
            .map { it.userInfo }

        val expectedInfo = listOf("""
            firstName: John
            lastName: Doe
            login: johndoe@unknown.com
            fullName: John Doe
            initials: J D
            email: JohnDoe@unknown.com
            phone: null
            meta: {src=csv}
        """.trimIndent(),
        """
            firstName: Mark
            lastName: Smith
            login: +79524782585
            fullName: Mark Smith
            initials: M S
            email: null
            phone: +79524782585
            meta: {src=csv}
        """.trimIndent()
        )

        Assert.assertEquals(expectedInfo, result)
    }

    @Test
    fun test_csv_login() {
        val holder = UserHolder

        val expectedUsers = holder.importUsers(listOf(
            " John    Doe ;JohnDoe@list.ru;[B@7591083d:c6adb4becdc64e92857e1e2a0fd6af84;;",
            " John;;[B@77a567e1:a07e337973f9ab704118c73ff827a695;+7 (900) 971-11-11;"
        ))

        val expectedInfo = """
            firstName: John
            lastName: Doe
            login: johndoe@list.ru
            fullName: John Doe
            initials: J D
            email: JohnDoe@list.ru
            phone: null
            meta: {src=csv}
        """.trimIndent()

        val successResult =  holder.loginUser("johndoe@list.ru", "testPass")
        val failResult =  holder.loginUser("JohnDoe@gmail.ru", "invalidPass")

        Assert.assertNotEquals(expectedInfo, failResult)
        Assert.assertEquals(expectedInfo, successResult)
    }

    @Test
    fun test_extension() {
        val result = "House Nymeros Martell of Sunspear"
            .split(" ")
            .dropLastUntil { it == "of" }

        Assert.assertEquals(listOf("House", "Nymeros", "Martell"), result)
    }
}
