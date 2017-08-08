package com.arabs.notes41.activities

import android.app.ActionBar
import android.app.AlertDialog
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.arabs.notes41.R

import kotlinx.android.synthetic.main.activity_task.*
import kotlinx.android.synthetic.main.content_task.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.content.DialogInterface
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.row_task.*
import android.widget.TextView
import com.arabs.notes41.support.DbHelper
import kotlinx.android.synthetic.main.activity_main.*


class TaskActivity : AppCompatActivity() {


    var dbHelper: DbHelper? = null
    var mAdapter: ArrayAdapter<String>? = null
    var lstTask: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        setSupportActionBar(toolbar_task)

        dbHelper = DbHelper(this)

        lstTask = findViewById<View>(R.id.lstTask) as? ListView

        loadTaskList()
    }

    private fun loadTaskList() {
        val taskList = dbHelper?.taskList
        if (mAdapter == null) {
            mAdapter = ArrayAdapter(this, R.layout.row_task, R.id.task_title, taskList)
            lstTask?.adapter = mAdapter
        } else {
            mAdapter!!.clear()
            mAdapter!!.addAll(taskList)
            mAdapter!!.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_task, menu)

        //Change menu icon color
        val icon = menu.getItem(0).icon
        icon.mutate()
        icon.setColorFilter(resources.getColor(android.R.color.white), PorterDuff.Mode.SRC_IN)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_task -> {
                val taskEditText = EditText(this)
                val dialog = AlertDialog.Builder(this)
                        .setTitle("Add New Task")
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add") { dialog, which ->
                            val task = taskEditText.text.toString()
                            dbHelper?.insertNewTask(task)
                            loadTaskList()
                        }
                        .setNegativeButton("Cancel", null)
                        .create()
                dialog.show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun deleteTask(view: View) {
        val parent = view.parent as? View
        val taskTextView = parent?.findViewById<View>(R.id.task_title) as? TextView
        Log.e("String", taskTextView?.text as? String)
        val task = taskTextView?.text.toString()
        dbHelper?.deleteTask(task)
        loadTaskList()
    }

}
