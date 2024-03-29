package com.example.ghac.domain.pagingsource


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.ghac.domain.model.GithubRepo
import com.example.ghac.domain.repository.GithubRepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GithubRepositoriesPagingSource @Inject constructor(
    private val githubRepoRepository: GithubRepoRepository,
    private val username: String,
) : PagingSource<Int, GithubRepo>() {
    companion object {
        const val FIRST_PAGE_INDEX = 1
        const val PAGING_SIZE = 20
    }

    override fun getRefreshKey(state: PagingState<Int, GithubRepo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepo> =
        withContext(Dispatchers.IO) {
            val position = params.key ?: FIRST_PAGE_INDEX
            return@withContext try {
                val result =
                    githubRepoRepository.getGithubRepositoriesByUsername(
                        username = username,
                        page = position,
                        itemsPerPage = PAGING_SIZE
                    )
                LoadResult.Page(data = result, prevKey = null,
                    //次のページデータがない場合はnullを返してページング終了
                    nextKey = (position + 1).takeIf { result.isNotEmpty() })
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }
}
