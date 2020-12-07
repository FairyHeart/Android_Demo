package com.lib.jetpack_workmanager

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/7.
 */
class UploadLogWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    /**
     * 执行耗时操作
     *
     * @return  若执行成功，则返回Result.success()
     *          若执行失败，则返回Result.failure()
     *          若需要重新执行，则返回Result.retry()
     */
    override fun doWork(): Result {
        //接收传递过来的值
        val inputData = inputData.getString("key")

        //任务执行完毕后，返回数据
        val resultData = Data.Builder()
            .putBoolean("result", true)
            .build()

        return Result.success(resultData)
    }
}