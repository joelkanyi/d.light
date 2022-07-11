package com.kanyideveloper.dlight.domain.use_case

import com.google.common.truth.Truth
import com.kanyideveloper.dlight.data.repository.FakeUserRepository
import com.kanyideveloper.dlight.util.TestData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUserReposTest {

    private lateinit var getUserRepos: GetUserRepos
    private lateinit var fakeUserRepository: FakeUserRepository

    @Before
    fun setUp() {
        fakeUserRepository = FakeUserRepository()
        getUserRepos = GetUserRepos(fakeUserRepository)
    }

    @Test
    fun `Get user repos for JoelKanyi and they are correct`() = runBlocking{
        val repos = getUserRepos("JoelKanyi").first()
        Truth.assertThat(repos.data).isEqualTo(TestData.testRepos)
    }
}