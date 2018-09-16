package com.learningsample.mohammedsiddiq.demoapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class PersonName extends AppCompatActivity {

    static final String ENTERED_NAME = "enteredName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_name);
        Log.i(this.getLocalClassName()," Inside person Activity");
        EditText nameEditor = (EditText) findViewById(R.id.nameEditText);

        String enteredName = nameEditor.getText().toString().trim(); // get the entered text and trim
        Intent responseIntent = new Intent();
        responseIntent.putExtra(ENTERED_NAME,enteredName);
        if (legalName(enteredName)) {
            setResult(Activity.RESULT_OK,responseIntent); // if legal name send OK response
        }
        setResult(Activity.RESULT_CANCELED,responseIntent); // if not send Cancelled respnse


        finish(); //closing activity

    }

    /*
     A legal name consists at
    least of a first name and a last name, each containing a sequence of alphabetical characters and separated
    by one or more space characters. Leading and trailing white space characters should be ignored
     */
    private boolean legalName(String enteredName) {
        String nameArray[] = enteredName.split(" +"); //split based on one or more spaces to find first and last name

        if (nameArray.length < 2) return false; // contains only first name

        for (String name :
                nameArray) {
            if (name.length() < 2) { // if name has only one character
                return false;
            }
            if (!name.matches("[a-zA-z]")) { // if name contains other characters
                return false;
            }

        }
        return true; // if control reaches here then Legal name
    }
}
