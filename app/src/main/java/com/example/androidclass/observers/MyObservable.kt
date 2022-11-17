package com.example.androidclass.observers

interface MyObservable {
    fun registerObserver(observer: MyObserver?)
    fun removeObserver(observer: MyObserver?)
    fun notifyObservers()
}
