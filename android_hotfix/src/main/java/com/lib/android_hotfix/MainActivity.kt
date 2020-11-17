package com.lib.android_hotfix

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.lib.android_hotfix.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.activity = this
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    fun show() {
        val i = 10 / 0
        Toast.makeText(this, "show $i", Toast.LENGTH_SHORT).show()
    }

    fun hotFix() {

    }

    fun fix() {
        //获取修复包
        val sourceFile = File(Environment.getExternalStorageDirectory(), ".dex")

        //目标文件，私有目录
        val targetFile = File(
            getDir(
                Constants.DEX_DIR,
                Context.MODE_PRIVATE
            ).absolutePath + File.separator + Constants.DEX_NAME
        )
        if (targetFile.exists()) {
            targetFile.delete()
        }

        //将下载的修复包复制到私有目录，然后再做减压工作
        FileUtils.copyFile(sourceFile, targetFile)

        //调用修复工具，开始修复
        FixDexUtils.loadFixDex(this)
    }
}