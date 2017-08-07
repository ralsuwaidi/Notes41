package com.arabs.notes41

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*

import com.arabs.notes41.adapter.ContactsAdapter
import com.arabs.notes41.data.Contact
import com.arabs.notes41.support.ItemClickSupport
import kotlinx.android.synthetic.main.content_main.*

import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var contacts = ArrayList<Contact>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        ItemClickSupport.addTo(rvContacts).setOnItemClickListener { recyclerView, position, v ->
            Snackbar.make(recyclerView, "you clicked number " + position, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        // Initialize contacts
        contacts.clear()
        contacts = Contact.createContactsList(28)
        // Create adapter passing in the sample user data
        val adapter = ContactsAdapter(this, contacts)
        // Attach the adapter to the recyclerview to populate items
        rvContacts.adapter = adapter
        // Set layout manager to position the items
        rvContacts.layoutManager = LinearLayoutManager(this)
        // That's all!
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
