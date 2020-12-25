package com.volodymyrmalynovskyi.simple_kotlin_application.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.volodymyrmalynovskyi.simple_kotlin_application.db.AppContext
import com.volodymyrmalynovskyi.simple_kotlin_application.db.MyDataBase
import com.volodymyrmalynovskyi.simple_kotlin_application.db.models.NoteRecord
import com.volodymyrmalynovskyi.simple_kotlin_application.db.repo.NoteRecordsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}

class NoteRecordViewModel: ViewModel(){
    @Inject
    lateinit var noteRecordRepo: NoteRecordsRepo
    lateinit var noteRecordList: LiveData<List<NoteRecord>>

    init {
        val noteRecordDao = MyDataBase.getDataBase(AppContext.getContext()).noteRecordDao()
        noteRecordRepo = NoteRecordsRepo(noteRecordDao)
        noteRecordList = noteRecordRepo.allNoteRecords()
    }

    fun addNoteRecord(record: NoteRecord){
        viewModelScope.launch (Dispatchers.IO){
            noteRecordRepo.insertNoteRecord(record)
        }
    }
}