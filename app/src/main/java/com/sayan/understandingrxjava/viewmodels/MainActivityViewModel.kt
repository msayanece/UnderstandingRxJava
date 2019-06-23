package com.sayan.understandingrxjava.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sayan.understandingrxjava.repositories.MainActivityRepositories
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    fun createListOfStudents(): MutableLiveData<List<String>> {
        val studentNameLiveData: MutableLiveData<List<String>> = MutableLiveData()
        compositeDisposable.add(MainActivityRepositories()
            .getSomeStudents()
            .buffer(3)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("understandingrxjava vM", "data: $it")
                studentNameLiveData.postValue(it)
            })
        return studentNameLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}