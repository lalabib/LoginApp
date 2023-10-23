package com.lalabib.loginapp.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lalabib.loginapp.data.remote.network.ApiService
import com.lalabib.loginapp.domain.model.ListUser
import com.lalabib.loginapp.utils.DataMapper
import java.io.IOException
import retrofit2.HttpException

class ListUserPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, ListUser>() {

    override fun getRefreshKey(state: PagingState<Int, ListUser>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListUser> {
        return try {
            val page = params.key ?: 1

            val responseData = apiService.getListUser(page = page)
            val data = DataMapper.responseToDomain(responseData.data)
            Log.e("TAG", "loadCheck: page = $page, size = ${responseData.data.size}")
            val nextPage = if (data.isEmpty()) {
                null
            } else {
                page + 1
            }
            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextPage
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
}