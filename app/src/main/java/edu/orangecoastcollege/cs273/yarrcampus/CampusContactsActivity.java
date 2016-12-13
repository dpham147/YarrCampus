package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;

public class CampusContactsActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Contacts> allContactsList;
    private ContactsListAdapter contactsListAdapter;
    private ListView contactsListView;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ShakeDetector shakeDetector;
    private RelativeLayout activity_campus_contacts;
    private Context context;

    /**
     * Loads all the Contacts in to the adapter and displays them into the ListView.
     * When the user shakes the phone, rotate the layout out of view for 10 seconds
     *
     * @param savedInstanceState Last instance of when the activity was loaded
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_contacts);

        activity_campus_contacts = (RelativeLayout) findViewById(R.id.activity_campus_contacts);
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
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        context = this;
        shakeDetector = new ShakeDetector(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake() {
                Animation animation = AnimationUtils.loadAnimation(context, R.anim.reverse_anim);
                activity_campus_contacts.startAnimation(animation);
            }
        });
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

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(shakeDetector, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(shakeDetector);
    }
}
