package com.volodymyrmalynovskyi.simple_kotlin_application.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.volodymyrmalynovskyi.simple_kotlin_application.R
import com.volodymyrmalynovskyi.simple_kotlin_application.db.models.NoteRecord
import com.volodymyrmalynovskyi.simple_kotlin_application.ui.viewholders.NoteRecordViewHolder

class NoteRecordAdapter(val records: List<NoteRecord>): RecyclerView.Adapter<NoteRecordViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteRecordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_record_view, parent, false)

        return NoteRecordViewHolder(view)
    }

    override fun getItemCount(): Int {
        return records.size
    }

    override fun onBindViewHolder(holder: NoteRecordViewHolder, position: Int) {
        holder.noteRecordTitleView.text = records[position].title ?: ""
        holder.noteRecordCreatedView.text = records[position].dateCreated ?: ""
        holder.noteRecordDescriptionView.text = records[position].description ?: ""
    }

}