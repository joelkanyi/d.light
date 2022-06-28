package com.kanyideveloper.dlight.domain

import com.kanyideveloper.dlight.data.FakeUserRepository
import com.kanyideveloper.dlight.domain.use_case.GetUserFollowings
import org.junit.Before

class GetUserFollowingTest {

    private lateinit var getUserFollowing: GetUserFollowings
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp() {
        fakeUserRepository = FakeUserRepository()
        getUserFollowing = GetUserFollowings(fakeUserRepository)
    }
}