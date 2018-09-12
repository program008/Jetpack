package com.canbot.jetpack

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import androidx.work.*
import com.canbot.jetpack.workmanage.MainWorker
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)
                //创建任务执行条件的约束
                val constraints = Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        //.setRequiresBatteryNotLow(true)                 // 不在电量不足时执行
                        //.setRequiresCharging(true)                      // 在充电时执行
                        //.setRequiresStorageNotLow(true)                 // 不在存储容量不足时执行
                        //.setRequiresDeviceIdle(true)                    // 在待机状态下执行，需要 API 23
                        .build()

                val dateFormat = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
                val data = Data.Builder()
                        .putString("time", dateFormat.format(Date()))
                        .build()
                val request = OneTimeWorkRequest.Builder(MainWorker::class.java)
                        .setInputData(data)
                        .setConstraints(constraints)
                        .build()
                //定时任务
                val periodicWorkRequest = PeriodicWorkRequest
                        .Builder(MainWorker::class.java, 15, TimeUnit.MINUTES)
                        .setConstraints(constraints)
                        .setInputData(data)
                        .build()

                WorkManager.getInstance().enqueue(request)

                WorkManager.getInstance()
                        .getStatusById(request.id)
                        .observe(this, Observer<WorkStatus> { workStatus ->
                                if (workStatus != null && workStatus.state.isFinished) {
                                        tv.setText(workStatus.outputData.getString("name", ""))
                                        Log.d("MainActivity", workStatus.outputData.getString("name", ""))
                                }
                        })
        }

        override fun onDestroy() {
                super.onDestroy()
                //如果需要取消一个在队列中的任务，也是通过 id 实现的
                //WorkManager.getInstance().cancelWorkById(request.id)
        }
}

