package com.kanyideveloper.dlight.domain

import com.kanyideveloper.dlight.data.FakeUserRepository
import com.kanyideveloper.dlight.domain.use_case.GetUserData
import org.junit.Before

class GetUserDataTest {

    private lateinit var getUserData: GetUserData
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp() {
        fakeUserRepository = FakeUserRepository()
        getUserData = GetUserData(fakeUserRepository)
    }
}