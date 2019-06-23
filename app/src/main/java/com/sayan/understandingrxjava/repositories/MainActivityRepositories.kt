package com.sayan.understandingrxjava.repositories

import com.sayan.understandingrxjava.models.AStudent
import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

class MainActivityRepositories {
    fun getSomeStudents(): Observable<String> {
        return Observable
            .fromIterable(generateListOfStudents())
            .subscribeOn(Schedulers.io())
            .map{it.name}
    }

    fun generateListOfStudents(): ArrayList<AStudent> {
        return ArrayList<AStudent>().apply {
            add(AStudent("sayan", "12", "X", "A"))
            add(AStudent("ayan", "1", "X", "A"))
            add(AStudent("Abhi", "3", "X", "A"))
        }
    }
}