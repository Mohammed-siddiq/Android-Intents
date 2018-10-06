package com.learningsample.mohammedsiddiq.demoapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    static final int ENTER_NAME_REQUEST = 1; // The request code
    static final int ADD_CONTACT_REQUEST = 2;
    private int nameActivityResultCode;
    private String userName;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) { // Avoiding app crash when second activity not returning data
            switch (requestCode) {
                case ENTER_NAME_REQUEST:
                    nameActivityResultCode = resultCode;
                    userName = data.getStringExtra(PersonName.ENTERED_NAME);
            }

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(this.getLocalClassName(), "-- Mohammed Siddiq was here!!");

        // Getting id's of the button
        Button nameButton = (Button) findViewById(R.id.nameButton);
        Button contactButton = (Button) findViewById(R.id.contactButton);

        if (savedInstanceState == null) {

        }

        nameButton.setOnClickListener(nameButtonListener);

        contactButton.setOnClickListener(contactButtonListener);

    }

    private View.OnClickListener nameButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, PersonName.class);
            startActivityForResult(intent, ENTER_NAME_REQUEST);
        }
    };

    private View.OnClickListener contactButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (nameActivityResultCode == Activity.RESULT_OK) {
                startContactsActivity(userName);
            } else if (userName == null)
                Toast.makeText(MainActivity.this, "You haven't entered any name, enter and try again.", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "You have entered an incorrect name : " + userName, Toast.LENGTH_LONG).show();
        }
    };

    private void startContactsActivity(String userName) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, userName);
        startActivityForResult(intent, ADD_CONTACT_REQUEST);

    }

}
