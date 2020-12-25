package com.volodymyrmalynovskyi.simple_kotlin_application.ui.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.volodymyrmalynovskyi.simple_kotlin_application.R

class NoteRecordViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
    val noteRecordTitleView = itemView.findViewById(R.id.text_view_title) as TextView
    val noteRecordCreatedView = itemView.findViewById(R.id.text_view_created) as TextView
    val noteRecordDescriptionView = itemView.findViewById(R.id.text_view_description) as TextView
}