package com.canbot.paging

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.canbot.paging.adapter.StudentAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
        private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
                ViewModelProviders.of(this, object : ViewModelProvider.Factory {
                        override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(application) as T
                }).get(MainViewModel::class.java)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)
                val adapter = StudentAdapter()
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter

                viewModel.allStudent.observe(this, Observer { adapter.submitList(it) })
        }
}
