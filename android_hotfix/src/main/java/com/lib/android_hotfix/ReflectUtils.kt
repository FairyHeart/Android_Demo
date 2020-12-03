package com.lib.android_hotfix

object ReflectUtils {

    /**
     *
     */
    fun setFiled(systemPathLists: Any, clazz: Class<out Any>, dexElements: Any) {
        val localField = clazz.getDeclaredField("dexElements")
        localField.isAccessible = true
        localField.set(systemPathLists, dexElements)
    }


    private fun getFiles(classLoader: Any, forName: Class<*>?, s: String): Any {
        TODO("Not yet implemented")
    }

    /**
     * 通过反射获取BaseDexClassLoader对象中创建的PathList对象
     * @param baseDexClassLoader BaseDexClassLoader对象
     * @return PathList对象
     */
    fun getPathList(baseDexClassLoader: Any): Any {
        return getFiles(
            baseDexClassLoader,
            Class.forName("dalvik.system.BaseDexClassLoader"),
            "pathList"
        )
    }

    /**
     * 通过反射获取BaseDexClassLoader对象中的PathList对象，再获取dexElements对象
     * @param pathList pathList对象
     * @return dexElements对象
     */
    fun getDexElements(pathList: Any): Any {
        return getFiles(pathList, pathList.javaClass, "dexElements")
    }
}
