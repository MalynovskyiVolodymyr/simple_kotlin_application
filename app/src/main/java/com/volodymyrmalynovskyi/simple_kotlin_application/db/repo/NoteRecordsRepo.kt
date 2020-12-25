package com.volodymyrmalynovskyi.simple_kotlin_application.db.repo

import androidx.lifecycle.LiveData
import com.volodymyrmalynovskyi.simple_kotlin_application.db.models.NoteRecord
import com.volodymyrmalynovskyi.simple_kotlin_application.db.models.NoteRecordDao
import javax.inject.Inject

class NoteRecordsRepo @Inject constructor(private val noteRecordDao: NoteRecordDao){
    fun allNoteRecords(): LiveData<List<NoteRecord>> = noteRecordDao.getAllNoteRecords()

    suspend fun insertNoteRecord(record: NoteRecord){
        noteRecordDao.insertNoteRecord(record)
    }
}