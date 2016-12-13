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
        private TextView contactsHoursListTextView;

    /**
     * Creates a new <code>GameListAdapter</code> given a mContext, resource id and list of games.
     *
     * @param context The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param contacts The list of buildings to display
     */
    public ContactsListAdapter(Context context, int rId, List<Contacts> contacts){
        super(context, rId, contacts);
        mContext = context;
        mResourceId = rId;
        mContactsList = contacts;
    }

    /**
     * Gets the view associated with the layout.
     * @param position The position of the Contact selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        Contacts selectedContact = mContactsList.get(position);

        contactsListLinearLayout = (LinearLayout) view.findViewById(R.id.contactsLinearLayout);
        contactsNameListTextView = (TextView) view.findViewById(R.id.contactsNameListTextView);
        contactsNumberListTextView = (TextView) view.findViewById(R.id.contactsNumberListTextView);
        contactsHoursListTextView = (TextView) view.findViewById(R.id.contactsHoursListTextView);

        contactsNameListTextView.setText(selectedContact.getName());
        String phone = selectedContact.getPhoneNumber();
        contactsNumberListTextView.setText("(" + phone.substring(0, 3) +
                                            ") " + phone.substring(3, 6) +
                                            " - " + phone.substring(6));
        contactsHoursListTextView.setText(selectedContact.getHours());

        contactsListLinearLayout.setTag(selectedContact);



        return view;
    }
}
