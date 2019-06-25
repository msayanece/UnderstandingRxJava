package com.sayan.understandingrxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sayan.understandingrxjava.viewmodels.MainActivityViewModel
import com.sayan.easylistwidget.EasyListView
import com.sayan.easylistwidget.listtiles.ListTile
import com.sayan.understandingrxjava.models.StudentName
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.file.Files.size





class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        val names: ArrayList<StudentName> = ArrayList()
        val listTile = ListTile<StudentName>(StudentName::class.java)
            .addTitle("getName")
        val easyListView = EasyListView.Builder<StudentName>(this)
            .addRecyclerView(recyclerView)
            .addListItems(names)
            .addItemModel(StudentName::class.java)
            .addRow(listTile)
            .build()

        mainActivityViewModel.createListOfStudents()
            .observe(this, Observer {
                names.add(StudentName(it))
                easyListView.basicRecyclerAdapter?.notifyDataSetChanged()
                Log.d("understandingrxjava MA", "data: $it")
            })
    }
}
