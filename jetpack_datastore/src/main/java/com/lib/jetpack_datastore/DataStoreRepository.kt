package com.lib.jetpack_datastore

import android.content.Context
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 *
 *
 * @author: GuaZi.
 * @date  : 2020/12/25.
 */
class DataStoreRepository(private val context: Context) {

    companion object {

        private var name = "data_store"

        fun setSharedName(sharedName: String) {
            this.name = sharedName
        }
    }

    private val dataStore by lazy {
        context.createDataStore(
            name = name,
            //SharedPreferences数据迁移
            migrations = listOf(SharedPreferencesMigration(context, "shared_preferences_name"))
        )
    }


    /**
     * 保存数据
     *
     * @param key
     * @param value
     */
    suspend fun <T> saveData(key: Preferences.Key<T>, value: T): DataStoreRepository {
        dataStore.edit {
            it[key] = value
        }
        return this
    }

    /**
     * 读取数据
     */
    fun <T> readData(
        key: Preferences.Key<T>,
        default: T? = null,
        onError: (Throwable) -> Unit = {}
    ): Flow<T?> {
        return dataStore.data
            .catch {
                onError.invoke(it)
            }.map {
                it[key] ?: default
            }
    }
}