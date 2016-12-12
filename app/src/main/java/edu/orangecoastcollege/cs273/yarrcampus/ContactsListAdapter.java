package edu.orangecoastcollege.cs273.yarrcampus;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdo94 on 12/8/2016.
 */

public class ContactsListAdapter extends ArrayAdapter<Contacts> {
        private Context mContext;
        private List<Contacts> mContactsList = new ArrayList<>();
        private  int mResourceId;
        private LinearLayout contactsListLinearLayout;
        private TextView contactsNameListTextView;
        private TextView contactsNumberListTextView;

    public ContactsListAdapter(Context context, int rId, List<Contacts> contacts){
        super(context, rId, contacts);
        mContext = context;
        mResourceId = rId;
        mContactsList = contacts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        Contacts selectedContact = mContactsList.get(position);

        contactsListLinearLayout = (LinearLayout) view.findViewById(R.id.contactsLinearLayout);
        contactsNameListTextView = (TextView) view.findViewById(R.id.contactsNameListTextView);
        contactsNumberListTextView = (TextView) view.findViewById(R.id.contactsNumberListTextView);

        contactsNameListTextView.setText(selectedContact.getName());
        contactsNumberListTextView.setText(selectedContact.getPhoneNumber());

        contactsListLinearLayout.setTag(selectedContact);



        return view;
    }
}
