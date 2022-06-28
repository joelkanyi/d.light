package com.kanyideveloper.dlight.domain

import com.kanyideveloper.dlight.data.FakeUserRepository
import com.kanyideveloper.dlight.domain.use_case.GetUserFollowers
import org.junit.Before

class GetUserFollowersTest {

    private lateinit var getUserFollowers: GetUserFollowers
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp() {
        fakeUserRepository = FakeUserRepository()
        getUserFollowers = GetUserFollowers(fakeUserRepository)
    }
}