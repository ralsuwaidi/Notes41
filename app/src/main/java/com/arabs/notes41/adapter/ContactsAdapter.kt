package com.arabs.notes41.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import com.arabs.notes41.R
import com.arabs.notes41.data.Contact

/**
 * Created by Rashid on 07/08/2017.
 */
// Pass in the contact array into the constructor
// Store the context for easy access
// Easy access to the context object in the recyclerview

class ContactsAdapter ( private val context: Context, private val mContacts: List<Contact>) :
        RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Your holder should contain a member variable
        // for any view that will be set as you render a row

        var titleTextView: TextView = itemView.findViewById<View>(R.id.title) as TextView
        var dateTextView: TextView = itemView.findViewById<View>(R.id.date) as TextView

        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
    }

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.row_rv_contacts, parent, false)

        // Return a new holder instance
        val viewHolder = ViewHolder(contactView)
        return viewHolder
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ContactsAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val contact = mContacts[position]

        // Set item views based on your views and data model
        val textView = viewHolder.titleTextView
        textView.text = contact.name
        val button = viewHolder.dateTextView
        button.text = "Message"
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mContacts.size
    }
}