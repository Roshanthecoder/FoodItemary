package codeflies.com.saalimmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import codeflies.com.saalimmvvm.model.PostResultItem
import codeflies.com.saalimmvvm.model.responseClass.home.HomeResponse
import codeflies.com.saalimmvvm.model.responseClass.maintenance.MaintenanceResponse
import codeflies.com.saalimmvvm.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private var _postdata = MutableLiveData<List<PostResultItem>>()
    val postdata: LiveData<List<PostResultItem>>
        get() = _postdata

    private var _loading=MutableLiveData<Boolean>()
    val loading:LiveData<Boolean>
        get() = _loading

    private var _postdataHomeresponse = MutableLiveData<HomeResponse>()
    val postdataHomeresponse: LiveData<HomeResponse>
        get() = _postdataHomeresponse


    private var _postdataMaintainresponse = MutableLiveData<MaintenanceResponse>()
    val postdataMaintainresponse: LiveData<MaintenanceResponse>
        get() = _postdataMaintainresponse




    fun getpost() {
        viewModelScope.launch {
            _loading.value=true
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    repository.getpost()
                }
            }.onSuccess {
                _postdata.postValue(it)
                _loading.value=false
               Log.e("postValue", it.toString())
            }.onFailure {
                it.localizedMessage?.let { it1 -> Log.e("saalim", it1)
                    _loading.value=false}
            }
        }


    }




    fun getpostHome() {
        viewModelScope.launch {
            _loading.value=true
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    repository.getHomePosts()
                }
            }.onSuccess {
                _postdataHomeresponse.postValue(it)
                _loading.value=false
                 Log.e("postdataHomeresponse", it.toString())
            }.onFailure {
                it.localizedMessage?.let { it1 -> Log.e("saalim", it1)
                    _loading.value=false}
            }
        }


    }


    fun getpostMainta() {
        viewModelScope.launch {
            _loading.value=true
            kotlin.runCatching {
                withContext(Dispatchers.IO) {
                    repository.getMaintenancePosts()
                }
            }.onSuccess {
                _postdataMaintainresponse.postValue(it)
                _loading.value=false
                Log.e("_postdataMaintainresponse", it.toString())
            }.onFailure {
                it.localizedMessage?.let { it1 -> Log.e("saalim", it1)
                    _loading.value=false}
            }
        }


    }


}