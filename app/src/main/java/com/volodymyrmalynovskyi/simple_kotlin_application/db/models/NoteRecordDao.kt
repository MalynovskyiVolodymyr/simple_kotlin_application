package com.volodymyrmalynovskyi.simple_kotlin_application.db.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteRecordDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertNoteRecord(record: NoteRecord)

    @Query("SELECT * FROM MyRecords ORDER BY id DESC")
    fun getAllNoteRecords(): LiveData<List<NoteRecord>>
}