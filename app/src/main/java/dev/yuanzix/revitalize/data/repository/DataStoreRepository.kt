package dev.yuanzix.revitalize.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.yuanzix.revitalize.util.Constants.DATA_STORE_NAME
import dev.yuanzix.revitalize.util.Constants.DATA_STORE_PASSWORD_KEY
import dev.yuanzix.revitalize.util.Constants.DATA_STORE_USERNAME_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

data class UserCredentials(val username: String, val password: String)

class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private object PreferenceKeys {
        val username = stringPreferencesKey(name = DATA_STORE_USERNAME_KEY)
        val password = stringPreferencesKey(name = DATA_STORE_PASSWORD_KEY)
    }

    private val dataStore = context.dataStore

    suspend fun persistUsernameAndPassword(username: String, password: String) {
        dataStore.edit {
            it[PreferenceKeys.username] = username
            it[PreferenceKeys.password] = password
        }
    }

    val readUsernameAndPassword: Flow<UserCredentials> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        val username = preferences[PreferenceKeys.username] ?: ""
        val password = preferences[PreferenceKeys.password] ?: ""
        UserCredentials(username, password)
    }

    suspend fun persistDate(date: Long) {
        dataStore.edit {
            it[longPreferencesKey("date")] = date
        }
    }

    val readDate: Flow<Long> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        preferences[longPreferencesKey("date")] ?: 0
    }
}
