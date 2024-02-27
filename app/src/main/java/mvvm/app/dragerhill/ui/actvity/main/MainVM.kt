package mvvm.app.dragerhill.ui.actvity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import mvvm.app.dragerhill.utils.ApiState
import javax.inject.Inject


//NKHL_12

@HiltViewModel
class MainVM
@Inject
    constructor(
        private val repo:MainRepo
    )
    :ViewModel() {

    private val postStateFlow:MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val  _postStateFlow :StateFlow<ApiState> = postStateFlow

    fun  getPost() = viewModelScope.launch {
        postStateFlow.value  = ApiState.Loading
        repo.getPost().catch {
            e-> postStateFlow.value = ApiState.Failure(e)
        }.collect{
            postStateFlow.value = ApiState.Success(it)
        }
    }
}