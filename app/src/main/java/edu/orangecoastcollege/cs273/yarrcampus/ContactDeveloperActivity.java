package edu.orangecoastcollege.cs273.yarrcampus;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class ContactDeveloperActivity extends AppCompatActivity {

    private EditText contactDeveloperEditText;
    private TextView wordCountTextView;
    private Button sendEmailButton;
    @Override
    /**
     * Creates activity_contact_developer and sets each variable to its appropriate widgets and text fields
     * as well as setting any Listeners to the variables.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_developer);

        contactDeveloperEditText = (EditText) findViewById(R.id.contactDeveloperEditText);
        wordCountTextView = (TextView) findViewById(R.id.wordCountTextView);
        sendEmailButton = (Button) findViewById(R.id.sendEmailButton);
        wordCountTextView.setText("0" + getResources().getString(R.string.letters_cap));


        contactDeveloperEditText.addTextChangedListener(searchTextChangedListener);
    }

    /**
     *Listens to any text change in our single Edit Text and displays the number of characters in the field.
     */
    TextWatcher searchTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            int numberOfChar = charSequence.toString().length();
            wordCountTextView.setText(String.valueOf(numberOfChar) + getResources().getString(R.string.letters_cap));
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    /**
     * Creates an intent to send an email to our developer.
     * This function sets who the email is being sent to and populates the message
     * the the user's message
     *
     * @param view
     */
    public void sendEmail(View view){
            String[] TO = {"someone@gmail.com"};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);


            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");

            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "YarrWorld Contact Developer");
            emailIntent.putExtra(Intent.EXTRA_TEXT, contactDeveloperEditText.getText());

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                contactDeveloperEditText.setText("");
                Log.i("Finished sending email", "");
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(ContactDeveloperActivity.this,
                        "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
    }
}
