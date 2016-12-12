package edu.orangecoastcollege.cs273.yarrcampus;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class CampusContactsActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Contacts> allContactsList;
    private ContactsListAdapter contactsListAdapter;
    private ListView contactsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_contacts);
        db = new DBHelper(this);
        db.deleteAllContacts();
        db.addContact(new Contacts("Kevin Do", "tel:7147574625"));
        db.addContact(new Contacts("Roman Barron", "tel:9512882633"));
        allContactsList = db.getAllContacts();
        Log.d("Yarr Campus", "test");
        for (Contacts contact : allContactsList)
            Log.d("Yarr Campus", contact.getName() + " " + contact.getPhoneNumber());
        contactsListAdapter = new ContactsListAdapter(this, R.layout.contacts_list_item, allContactsList);
        contactsListView = (ListView) findViewById(R.id.contactsListView);
        contactsListView.setAdapter(contactsListAdapter);



    }

    public void callContact(View view){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED);

        if(view instanceof LinearLayout){
            Contacts contact = (Contacts) view.getTag();
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse(contact.getPhoneNumber()));

            startActivity(callIntent);
        }
    }
}
