package com.arabs.notes41.data

import java.util.ArrayList

/**
 * Created by Rashid on 07/08/2017.
 */

class Contact(val name: String, val isOnline: Boolean) {
    companion object {

        private var lastContactId = 0

        fun createContactsList(numContacts: Int): ArrayList<Contact> {
            val contacts = ArrayList<Contact>()

            for (i in 1..numContacts) {
                contacts.add(Contact("Person " + ++lastContactId, i <= numContacts / 2))
            }

            return contacts
        }
    }
}