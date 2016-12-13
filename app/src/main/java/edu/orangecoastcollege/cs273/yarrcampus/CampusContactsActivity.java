package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class CampusContactsActivity extends AppCompatActivity {

    private static final int PHONE_CALL_REQUEST_CODE = 99;
    private DBHelper db;
    private List<Contacts> allContactsList;
    private ContactsListAdapter contactsListAdapter;
    private ListView contactsListView;

    /**
     * Loads all the Contacts in to the adapter and displays them into the ListView
     *
     * @param savedInstanceState Last instance of when the activity was loaded
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_contacts);
        db = new DBHelper(this);
        db.deleteAllContacts();
        db.addContact(new Contacts("Michael Paulding", "7144326842", "24/7/365"));
        db.addContact(new Contacts("Answer Center", "7144325772", "8:00 AM - 5:00 PM"));
        db.addContact(new Contacts("OCC President", "7144325712", "8:00 AM - 5:00 PM"));
        db.addContact(new Contacts("Transfer Center", "7144325894", "8:00 AM - 5:00 PM"));
        db.addContact(new Contacts("Library", "7144325885", "8:00 AM - 9:00 PM"));
        db.addContact(new Contacts("Enrollment Center", "7144325072", "8:00 AM - 5:00 PM"));
        db.addContact(new Contacts("Campus Safety", "7144325005", "8:00 AM - 10:00 PM"));
        allContactsList = db.getAllContacts();
        contactsListAdapter = new ContactsListAdapter(this, R.layout.contacts_list_item, allContactsList);
        contactsListView = (ListView) findViewById(R.id.contactsListView);
        contactsListView.setAdapter(contactsListAdapter);
    }

    /**
     * Dials the phone number of the contact selected
     *
     * @param view The current view
     */
    public void callContact(View view){
        if(view instanceof LinearLayout){
            Contacts contact = (Contacts) view.getTag();
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + contact.getPhoneNumber()));

            startActivity(callIntent);
        }
    }
}
