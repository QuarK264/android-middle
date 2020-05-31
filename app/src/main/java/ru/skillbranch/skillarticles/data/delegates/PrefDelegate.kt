package ru.skillbranch.skillarticles.data.delegates

import ru.skillbranch.skillarticles.data.local.PrefManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PrefDelegate<T>(private val defaultValue: T) : ReadWriteProperty<PrefManager, T?> {

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: PrefManager, property: KProperty<*>): T? = when (defaultValue) {
        is Boolean -> thisRef.preferences.getBoolean(STORED_BOOLEAN, false) as T
        is String -> thisRef.preferences.getString(STORED_STRING, "") as T
        is Float -> thisRef.preferences.getFloat(STORED_FLOAT, 0f) as T
        is Int -> thisRef.preferences.getInt(STORED_INT, 0) as T
        is Long -> thisRef.preferences.getLong(STORED_LONG, 0L) as T
        else -> null
    }

    override fun setValue(thisRef: PrefManager, property: KProperty<*>, value: T?) {
        when (value) {
            is Boolean -> thisRef.preferences.edit().putBoolean(STORED_BOOLEAN, value).apply()
            is String -> thisRef.preferences.edit().putString(STORED_STRING, value ).apply()
            is Float -> thisRef.preferences.edit().putFloat(STORED_FLOAT, value).apply()
            is Int -> thisRef.preferences.edit().putInt(STORED_INT, value).apply()
            is Long -> thisRef.preferences.edit().putLong(STORED_LONG, value).apply()
        }
    }

    companion object {
        private const val STORED_BOOLEAN = "STORED_BOOLEAN"
        private const val STORED_STRING = "STORED_STRING"
        private const val STORED_FLOAT = "STORED_FLOAT"
        private const val STORED_INT = "STORED_INT"
        private const val STORED_LONG = "STORED_LONG"
    }
}