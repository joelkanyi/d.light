package com.kanyideveloper.dlight.domain.use_case

import com.google.common.truth.Truth
import com.kanyideveloper.dlight.data.repository.FakeUserRepository
import com.kanyideveloper.dlight.util.TestData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUserFollowingTest {

    private lateinit var getUserFollowing: GetUserFollowings
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp() {
        fakeUserRepository = FakeUserRepository()
        getUserFollowing = GetUserFollowings(fakeUserRepository)
    }

    @Test
    fun `Get user following for JoelKanyi and they are correct`() = runBlocking{
        val following = getUserFollowing("JoelKanyi").first()
        Truth.assertThat(following.data).isEqualTo(TestData.testFollowing)
    }
}