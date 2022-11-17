package com.example.androidclass.storage

import com.example.androidclass.observers.MyObservable
import com.example.androidclass.observers.MyObserver

object ObservableStorage : MyObservable {
    private val observers = mutableListOf<MyObserver>()

    var isAirplaneModeOn = false

    override fun registerObserver(observer: MyObserver?) {
        observer?.let { observers.add(it) }
    }

    override fun removeObserver(observer: MyObserver?) {
        observer?.let { observers.remove(it) }
    }

    override fun notifyObservers() {
        observers.forEach {
            it.update(isAirplaneModeOn)
        }
    }
}
