package com.lib.android_hotfix

import android.content.Context
import dalvik.system.DexClassLoader
import java.io.File

/**
 *
 *
 * @author: Fairy.
 * @date  : 2020/11/17.
 */
object FixDexUtils {

    private val loadedDex = mutableSetOf<File>()

    //加载热修复到dex文件
    fun loadFixDex(context: Context) {
        //修复之前清空原来的数据
        loadedDex.clear()

        val fileDir = context.getDir(Constants.DEX_DIR, Context.MODE_PRIVATE) ?: return
        fileDir.listFiles().let { files ->
            files?.forEach {
                if (it?.name?.endsWith(Constants.Dex_suffix) == true && "classes.dex" != it.name) {
                    loadedDex.add(it)
                }
            }
        }

        //创建类加载器
        createDexClassLoader(context,fileDir)
    }

    /**
     * 创建加载补丁的DexClassLoader类加载器
     */
    private fun createDexClassLoader(context: Context, fileDir: File) {
        //创建输出的目录
        val optimizedDir =fileDir.absolutePath+File.separator+"opt_dex"
        val fopt = File(optimizedDir)
        if (!fopt.exists()) {
            fopt.mkdirs()
        }
        loadedDex.forEach {
            //创建自有的类加载器
            val classLoader = DexClassLoader(it.absolutePath,optimizedDir,null,context.classLoader)
            //每循环一次，修复一次
            hotfix(classLoader,context)
        }

    }

    private fun hotfix(classLoader: DexClassLoader, context: Context) {
        //获取系统的PathClassLoader类加载器
        val pathLoader = context.classLoader

        //获取自有的DexElements数组
        val myElements = ReflectUtils.getDexElements(ReflectUtils.getPathList(classLoader))

        //获取系统的DexElements数组
        val systemElements = ReflectUtils.getDexElements(ReflectUtils.getPathList(pathLoader))

        //合并数组，并生成一个新的DexElements数组（包含排序工作）
        val dexElements = ArrayUtils.combineArray(myElements,systemElements)

        //获取系统的pathList属性（通过反射技术）
        val systemPathLists = ReflectUtils.getPathList(pathLoader)

        //通过反射技术，将新的dexElements数组 赋值给系统的pathList属性
        ReflectUtils.setFiled(systemPathLists,systemPathLists::class.java,dexElements)

    }
}