package com.lib.android_hotfix

import java.lang.reflect.Array

object ArrayUtils {

    /**
     * 合并数组
     * @param arrayLhs 前数组（插入数组）
     * @param arrayRhs 后数组（已有数组）
     * @return 处理后的新数组
     */
    fun combineArray(arrayLhs: Any, arrayRhs: Any): Any {
        //获取一个数组的Class对象，通过Array.newInstance（）可以反射生成数组对象
        val loadClass = arrayLhs.javaClass.componentType

        //前数组长度
        val i = Array.getLength(arrayLhs)

        //新数组总长度
        val j = i + Array.getLength(arrayRhs)

        //生成数组对象
        val result = Array.newInstance(loadClass, j)
        for (k in i until j) {
            if (k < i) {
                //从0开始遍历，如果前数组有值，添加到新数组到第一个位置
                Array.set(result, k, Array.get(arrayLhs, k))
            } else {
                //添加完前数组，再添加后数组，合并完成
                Array.set(result, k, Array.get(arrayRhs, k - i))
            }
        }
        return result
    }

}
