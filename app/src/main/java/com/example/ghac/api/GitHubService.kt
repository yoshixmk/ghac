package com.example.ghac.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Github API communication setup via Retrofit.
 *
 * @see <a href="https://docs.github.com/en/rest/search?apiVersion=2022-11-28#search-users">search-users</a>
 */
interface GithubService {
    /**
     * to search users with a query
     */
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): UserSearchResponse

    /**
     * to fetch the user name by id
     *
     * @see <a href="https://docs.github.com/ja/rest/repos/repos?apiVersion=2022-11-28#list-repositories-for-a-user">list-repositories-for-a-user</a>
     */
    @GET("users/{username}/repos")
    suspend fun getUser(
        @Path("username") username: Long,
        @Query("sort") sort: String,
        @Query("direction") direction: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): List<Repo>
}
