package com.example.ghac.domain.pagingsource


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ghac.domain.model.GithubUser
import com.example.ghac.domain.repository.GithubUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserSearchPagingSource @Inject constructor(
    private val userRepository: GithubUserRepository,
    private val query: String,
) : PagingSource<Int, GithubUser>() {
    companion object {
        const val FIRST_PAGE_INDEX = 1
        const val PAGING_SIZE = 20
    }

    override fun getRefreshKey(state: PagingState<Int, GithubUser>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): PagingSource.LoadResult<Int, GithubUser> =
        withContext(Dispatchers.IO) {
            val position = params.key ?: FIRST_PAGE_INDEX
            return@withContext try {
                val result = userRepository.getGithubUsersByByKeyword(query, position, PAGING_SIZE)
                LoadResult.Page(
                    data = result,
                    prevKey = null,
                    //次のページデータがない場合はnullを返してページング終了
                    nextKey = (position + 1).takeIf { result.isNotEmpty() }
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
}
