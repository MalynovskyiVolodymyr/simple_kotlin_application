package com.volodymyrmalynovskyi.simple_kotlin_application.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.volodymyrmalynovskyi.simple_kotlin_application.R
import com.volodymyrmalynovskyi.simple_kotlin_application.db.models.NoteRecord
import com.volodymyrmalynovskyi.simple_kotlin_application.ui.adapters.NoteRecordAdapter
import java.util.*

class DashboardFragment : Fragment() {

    lateinit var savedContext: Context
    lateinit var recordsRecyclerView: RecyclerView
    private lateinit var noteRecordViewModel: NoteRecordViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        savedContext = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.let{ it ->
            recordsRecyclerView = it.findViewById(R.id.list_recyclerview)
            recordsRecyclerView.layoutManager = LinearLayoutManager(activity)
            noteRecordViewModel.noteRecordList.observe(viewLifecycleOwner, Observer<List<NoteRecord>>(){ records:List<NoteRecord> ->
                recordsRecyclerView.adapter = NoteRecordAdapter(records)
            })
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        noteRecordViewModel =
                ViewModelProviders.of(this).get(NoteRecordViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        root.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            showCreateListDialog()
        }

        return root
    }

    //

    fun addRecord(record: NoteRecord){
        noteRecordViewModel.addNoteRecord(record)
    }

    private fun showCreateListDialog(){
        val dialogTitle = getString(R.string.new_record)
        val positiveButtonTitle = getString(R.string.create_record)

        val builder = MaterialAlertDialogBuilder(savedContext, R.style.AlertDialogTheme_Center)

        val factory = LayoutInflater.from(savedContext)
        val textEntryView: View = factory.inflate(R.layout.alert_input_fields, null)

        val input1 =
            textEntryView.findViewById<View>(R.id.alert_text_title) as EditText
        val input2 =
            textEntryView.findViewById<View>(R.id.alert_text_description) as EditText
        input1.setText("", TextView.BufferType.EDITABLE)
        input2.setText("", TextView.BufferType.EDITABLE)

        builder.setTitle(dialogTitle)

        builder.setView(textEntryView)

        builder.setPositiveButton(positiveButtonTitle){ dialog, _ ->
            val rec = NoteRecord(title = input1.text.toString(),
                dateCreated = Date().toString(),
                description = input2.text.toString()
            )
            //val rec = NoteRecord(title = "test", dateCreated = Date().toString(), description = "hello!!!2")
            addRecord(rec)

            dialog.dismiss()
        }

        builder.show()
    }
}