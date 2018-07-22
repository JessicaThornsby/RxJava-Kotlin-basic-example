package com.jessicathornsby.rxjavaexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//Start the stream when the button is clicked//

        button.setOnClickListener { startRStream() }

    }

    private fun startRStream() {

//Create an Observable//

        val myObservable = getObservable()

//Create an Observer//

        val myObserver = getObserver()

//Subscribe myObserver to myObservable//

        myObservable
                .subscribe(myObserver)
    }

    private fun getObserver(): Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
            }

//Every time onNext is called, print the value to Android Studio’s Logcat//

            override fun onNext(s: String) {
                Log.d(TAG, "onNext: $s")
            }

//Called if an exception is thrown//

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

//When onComplete is called, print the following to Logcat//

            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }
        }
    }

//Give myObservable some data to emit//

    private fun getObservable(): Observable<String> {
        return Observable.just("1", "2", "3", "4", "5")
    }
}
