package com.mivanovskaya.humblr.data.repository

import android.annotation.SuppressLint
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mivanovskaya.humblr.domain.ListItem
import com.mivanovskaya.humblr.domain.repository.SubredditsRemoteRepository
import com.mivanovskaya.humblr.domain.tools.ListTypes
import javax.inject.Inject

class PagingSource @Inject constructor(
    private val repository: SubredditsRemoteRepository,
    private val source: String?,
    private val listType: ListTypes
) : PagingSource<String, ListItem>() {

    override fun getRefreshKey(state: PagingState<String, ListItem>): String = FIRST_PAGE

    @SuppressLint("SuspiciousIndentation")
    override suspend fun load(params: LoadParams<String>): LoadResult<String, ListItem> {
        val page = params.key ?: FIRST_PAGE
        return kotlin.runCatching {
            repository.getList(listType, source, page)
        }.fold(
            onSuccess = {LoadResult.Page(data = it, prevKey = null,
                nextKey = if (it.isEmpty()) null else it.last().name)},
            onFailure = {LoadResult.Error(it)}
        )
    }

    private companion object {
        private const val FIRST_PAGE = ""
    }
}

