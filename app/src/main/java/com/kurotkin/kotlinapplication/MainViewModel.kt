package com.kurotkin.kotlinapplication

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.kurotkin.kotlinapplication.model.GitRepoRepository
import com.kurotkin.kotlinapplication.model.NetManager
import com.kurotkin.kotlinapplication.model.OnRepositoryReadyCallback
import com.kurotkin.kotlinapplication.model.Repository

class MainViewModel : AndroidViewModel {            // AndroidViewModel запрашивает экземпляр приложения в своем конструкторе.

    constructor(application: Application) : super(application)

    var gitRepoRepository: GitRepoRepository = GitRepoRepository(NetManager( getApplication()))
//    val text = ObservableField<String>()
//    val isLoading = ObservableField<Boolean>()

    val text = ObservableField("old data")
    val isLoading = ObservableField(false)

    var repositories = MutableLiveData<ArrayList<Repository>>()

//    val cb = object : GitRepoRepository.OnDataReadyCallback {
//        override fun onDateReady(data: String) {
//            isLoading.set(false)
//            text.set(data)
//        }
//    }

//    fun refresh(){
//        isLoading.set(true)
//        gitRepoRepository.refreshData(cb)
//    }
//
//    fun refresh(b : Boolean?){
//        isLoading.set(true)
//        gitRepoRepository.refreshData(object : GitRepoRepository.OnDataReadyCallback {
//            override fun onDateReady(data: String) {
//                isLoading.set(false)
//                text.set(data)
//            }
//        })
//    }

    fun loadRepositories(){
        isLoading.set(true)
        gitRepoRepository.getRepositories(object : OnRepositoryReadyCallback {
            override fun onDataReady(data: ArrayList<Repository>) {
                isLoading.set(false)
                repositories.value = data
            }
        })
    }

}