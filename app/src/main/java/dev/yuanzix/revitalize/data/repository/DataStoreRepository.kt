package dev.yuanzix.revitalize.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
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

class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context
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

    val readUsernameAndPassword: Flow<String> = dataStore.data.catch {
        if (it is IOException) {
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {
        it[PreferenceKeys.username] ?: ""
        it[PreferenceKeys.password] ?: ""
    }

}