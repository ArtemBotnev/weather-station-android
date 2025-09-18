package com.artembotnev.weatherstation.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.artembotnev.weatherstation.domain.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

internal class UserPreferenceRepositoryImpl @Inject constructor(
    val dataStore: DataStore<Preferences>,
) : UserPreferenceRepository {

    override suspend fun setHost(host: String) {
        setStringToDataStore(key = HOST_DATA_STORE_KEY, value = host)
    }

    override suspend fun setPort(port: String) {
        setStringToDataStore(key = PORT_DATA_STORE_KEY, value = port)
    }

    override suspend fun getHost(): String = readDataStore().first()[HOST_DATA_STORE_KEY].orEmpty()

    override suspend fun getPort(): String = readDataStore().first()[PORT_DATA_STORE_KEY].orEmpty()

    override suspend fun hostFlow(): Flow<String> = readDataStore().map {
        it[HOST_DATA_STORE_KEY]
    }.filterNotNull()

    override suspend fun portFlow(): Flow<String> = readDataStore().map {
        it[PORT_DATA_STORE_KEY]
    }.filterNotNull()

    private fun readDataStore(): Flow<Preferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }

    private suspend fun setStringToDataStore(key: Preferences.Key<String>, value: String) = try {
        dataStore.edit { it[key] = value }
    } catch (e: Exception) {
        Timber.tag(TAG).e(e.message.orEmpty())
    }

    private companion object {
        const val TAG = "UserPreferenceRepositoryImpl"
        val HOST_DATA_STORE_KEY = stringPreferencesKey("HOST_DATA_STORE_KEY")
        val PORT_DATA_STORE_KEY = stringPreferencesKey("PORT_DATA_STORE_KEY")
    }
}