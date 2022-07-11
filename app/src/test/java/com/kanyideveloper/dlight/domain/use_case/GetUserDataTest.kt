package com.kanyideveloper.dlight.domain.use_case

import com.kanyideveloper.dlight.data.repository.FakeUserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import com.google.common.truth.Truth.assertThat;
import kotlinx.coroutines.flow.first

class GetUserDataTest {
    private lateinit var getUserData: GetUserData
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp(){
        fakeUserRepository = FakeUserRepository()
        getUserData = GetUserData(fakeUserRepository)
    }

    @Test
    fun `Get user data for JoelKanyi and it is correct`() = runBlocking{
        val user = getUserData("JoelKanyi").first()
        assertThat(user.data?.login).isEqualTo("JoelKanyi")
    }
}