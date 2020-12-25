package com.volodymyrmalynovskyi.simple_kotlin_application.db.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "MyRecords")
class NoteRecord (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "dateCreated")
    var dateCreated: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String
): Parcelable