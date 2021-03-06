package com.guerra.enrico.models.todos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.guerra.enrico.models.sync.Operation
import com.guerra.enrico.models.sync.SyncAction
import com.guerra.enrico.models.sync.Syncable
import java.util.*

/**
 * Created by enrico
 * on 02/06/2018.
 */
@Entity(tableName = "Category")
data class Category constructor(
  @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "localId") val localId: Long = 0,
  @ColumnInfo(name = "id") val id: String = "",
  @ColumnInfo(name = "name") val name: String = ""
) : Syncable {

  override fun toSyncAction(operation: Operation): SyncAction = SyncAction(
    id = 0,
    entityName = ENTITY_NAME,
    entityLocalId = localId,
    entityId = id,
    operation = operation,
    createdAt = Date()
  )

  companion object {
    const val ENTITY_NAME = "Category"
  }
}