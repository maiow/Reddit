package com.mivanovskaya.humblr.data.repository

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mivanovskaya.humblr.domain.tools.ListTypes
import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.repository.SubredditsRemoteRepository
import javax.inject.Inject

class PagingSource @Inject constructor(
    private val repository: SubredditsRemoteRepository,
    private val source: String?,
    private val listType: ListTypes
) : PagingSource<String, ListItem>() {

    override fun getRefreshKey(state: PagingState<String, ListItem>): String = FIRST_PAGE

    @SuppressLint("SuspiciousIndentation")
    override suspend fun load(params: LoadParams<String>): LoadResult<String, ListItem> {
        Log.d(TAG, "load: ")
        val page = params.key ?: FIRST_PAGE
//        Log.i(TAG, "source: $source")
//        return kotlin.runCatching {
//
//        }.fold(
//            onSuccess = {
//                Log.e(TAG, "loading ok")
        return LoadResult.Page(
            data = repository.getList(listType, source, page),
            prevKey = null,
            nextKey = if (repository.getList(listType, source, page).isEmpty()) null
            else repository.getList(listType, source, page).last().name
        )
//
//            }, onFailure = {
//                Log.e(TAG, "loading error")
//                LoadResult.Error(it)
//            }
//        )
    }

    private companion object {
        private const val FIRST_PAGE = ""
    }
}

