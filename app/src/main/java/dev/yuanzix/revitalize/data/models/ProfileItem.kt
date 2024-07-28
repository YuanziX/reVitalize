package dev.yuanzix.revitalize.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.yuanzix.revitalize.data.constants.DatabaseConstants.PROFILE_TABLE

@Entity(tableName = PROFILE_TABLE)
data class ProfileItem(
    @PrimaryKey val title: String,
    val value: String,
)