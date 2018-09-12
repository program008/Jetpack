package com.canbot.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
        private var lifeListener:LifeListener?=null
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)
                lifeListener = LifeListener()
                lifecycle.addObserver(lifeListener!!)

        }
}

open class LifeListener :LifecycleObserver{
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate(owner: LifecycleOwner){
                Log.e("MainActivity","ON_CREATE")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart(owner: LifecycleOwner){
                Log.e("MainActivity","ON_START")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume(owner: LifecycleOwner){
                Log.e("MainActivity","ON_RESUME")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy(owner: LifecycleOwner){
                Log.e("MainActivity","ON_DESTROY")
        }
}
