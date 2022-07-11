package com.kanyideveloper.dlight.domain.use_case

import com.google.common.truth.Truth
import com.kanyideveloper.dlight.data.repository.FakeUserRepository
import com.kanyideveloper.dlight.util.TestData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUserFollowersTest {

    private lateinit var getUserFollowers: GetUserFollowers
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp() {
        fakeUserRepository = FakeUserRepository()
        getUserFollowers = GetUserFollowers(fakeUserRepository)
    }

    @Test
    fun `Get user followers for JoelKanyi and they are correct`() = runBlocking{
        val followers = getUserFollowers("JoelKanyi").first()
        Truth.assertThat(followers.data).isEqualTo(TestData.testFollowers)
    }
}