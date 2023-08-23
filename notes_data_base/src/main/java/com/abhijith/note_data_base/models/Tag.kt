package com.abhijith.note_data_base.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "Tags")
data class Tag(
    @PrimaryKey(autoGenerate = true)
    var tag_id:Long = 0,
    var name:String,
)
