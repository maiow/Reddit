package com.mivanovskaya.humblr.tools

import androidx.lifecycle.ViewModel
import com.mivanovskaya.humblr.domain.state.LoadState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/** теперь не получится использовать, т.к. у всех фрагментов силд классы стейты разные? */

//abstract class BaseViewModel : ViewModel() {
//
//    protected val _loadState = MutableStateFlow(LoadState.START)
//    val loadState = _loadState.asStateFlow()
//
//    protected val handler = CoroutineExceptionHandler { _, _ ->
//        _loadState.value = LoadState.ERROR
//    }
//}