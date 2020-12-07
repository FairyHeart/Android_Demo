package com.lib.jetpack_workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //设置任务触发条件
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)//网络已连接
            .setRequiresCharging(true)//处于充电
            .setRequiresBatteryNotLow(true)//电池电量充足
            .build()

        //传递参数
        val data = Data.Builder()
            .putString("key", "value")
            .build()

        //一次性任务
//        val workRequest = OneTimeWorkRequest.Builder(UploadLogWorker::class.java)
        val workRequest = OneTimeWorkRequestBuilder<UploadLogWorker>()
            .setConstraints(constraints)//设置触发条件
            .setInitialDelay(10, TimeUnit.SECONDS)//符合触发条件后，延迟10执行
            .setBackoffCriteria(//设置指数退避策略
                BackoffPolicy.LINEAR,
                OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .addTag("uploadTag")//为任务设置tag标签
            .setInputData(data)
            .build()

        //周期性任务,周期性任务的间隔时间不能少于15分钟
        val workRequest2 =
//            PeriodicWorkRequest.Builder(UploadLogWorker::class.java, 15, TimeUnit.MINUTES)
            PeriodicWorkRequestBuilder<UploadLogWorker>(15, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()

        //将任务提交给系统
        WorkManager.getInstance(this).enqueue(workRequest)

        //观察任务状态
        val workInfoLists = WorkManager.getInstance(this).getWorkInfosByTag("uploadTag")

        //取消任务
        WorkManager.getInstance(this).cancelAllWork()

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest.id)
            .observe(this, Observer {
                if (it != null && it.state == WorkInfo.State.SUCCEEDED) {
                    val outputData = it.outputData.getString("result")
                }
            })

    }

    fun task() {
        val workRequestA = OneTimeWorkRequest.Builder(UploadLogWorker::class.java).build()
        val workRequestB = OneTimeWorkRequest.Builder(UploadLogWorker::class.java).build()
        val workRequestC = OneTimeWorkRequest.Builder(UploadLogWorker::class.java).build()
        val workRequestD = OneTimeWorkRequest.Builder(UploadLogWorker::class.java).build()
        val workRequestE = OneTimeWorkRequest.Builder(UploadLogWorker::class.java).build()

        //先执行完A，再执行B
        WorkManager.getInstance(this).beginWith(workRequestA)
            .then(workRequestB)
            .enqueue()
        //先执行完A、B（a、b一起执行），然后再执行C
        WorkManager.getInstance(this).beginWith(listOf(workRequestA, workRequestB))
            .then(workRequestC)
            .enqueue()

        //复杂任务链
        val workContinuation1 = WorkManager.getInstance(this).beginWith(workRequestA)
            .then(workRequestB)
        val workContinuation2 = WorkManager.getInstance(this).beginWith(workRequestC)
            .then(workRequestD)
        val taskList = ArrayList<WorkContinuation>()
        taskList.add(workContinuation1)
        taskList.add(workContinuation2)
        WorkContinuation.combine(taskList)
            .then(workRequestE)
            .enqueue()
    }
}