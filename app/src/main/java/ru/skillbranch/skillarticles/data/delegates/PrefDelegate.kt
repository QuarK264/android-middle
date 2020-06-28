package ru.skillbranch.skillarticles.data.delegates

import ru.skillbranch.skillarticles.data.local.PrefManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PrefDelegate<T>(private val defaultValue: T) {

    private var storedValue: T? = null

    operator fun provideDelegate(
        thisRef: PrefManager,
        property: KProperty<*>
    ): ReadWriteProperty<PrefManager, T?> {
        val key = property.name

        return object : ReadWriteProperty<PrefManager, T?> {
            @Suppress("UNCHECKED_CAST")
            override fun getValue(thisRef: PrefManager, property: KProperty<*>): T? {
                if (storedValue == null) {
                    storedValue = when (defaultValue) {
                        is Boolean -> thisRef.preferences.getBoolean(key, defaultValue) as T
                        is String -> thisRef.preferences.getString(key, defaultValue) as T
                        is Float -> thisRef.preferences.getFloat(key, defaultValue) as T
                        is Int -> thisRef.preferences.getInt(key, defaultValue) as T
                        is Long -> thisRef.preferences.getLong(key, defaultValue) as T
                        else -> error("This type can not be stored into Preferences")
                    }
                }
                return storedValue
            }

            override fun setValue(thisRef: PrefManager, property: KProperty<*>, value: T?) {
                with(thisRef.preferences.edit()) {
                    when (value) {
                        is Boolean -> putBoolean(key, value)
                        is String -> putString(key, value)
                        is Float -> putFloat(key, value)
                        is Int -> putInt(key, value)
                        is Long -> putLong(key, value)
                        else -> error("Only primitive types can be stored into Preferences")
                    }
                    apply()
                }
                storedValue = value
            }
        }
    }
}