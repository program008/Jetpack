package com.canbot.jetpack.workmanage

import android.util.Log
import androidx.work.Data
import androidx.work.Worker

/**
 * Created by tao.liu on 2018/9/11.
 * description this is description
 */
class MainWorker : Worker() {

        override fun doWork(): WorkerResult {
                Log.e("MainWork",inputData.getString("time",""))
                outputData = Data.Builder()
                        .putString("name","执行完成")
                        .build()
                return WorkerResult.SUCCESS
        }
}