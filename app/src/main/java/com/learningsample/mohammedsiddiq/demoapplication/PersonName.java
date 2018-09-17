package com.learningsample.mohammedsiddiq.demoapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class PersonName extends Activity {

    static final String ENTERED_NAME = "enteredName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_name);
        Log.i(this.getLocalClassName(), " Inside person Activity");
        EditText nameEditor = (EditText) findViewById(R.id.nameEditText);

        nameEditor.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    validateAndReturn(v.getText().toString().trim());
                }
                return true;
            }
        });

    }

    private void validateAndReturn(String enteredName) {
        Intent responseIntent = new Intent();
        responseIntent.putExtra(ENTERED_NAME, enteredName);
        Log.i(getLocalClassName(), "Entered Name is " + enteredName);
        if (legalName(enteredName))
            setResult(Activity.RESULT_OK, responseIntent); // if legal name send OK response
        else
            setResult(Activity.RESULT_CANCELED, responseIntent); // if not send Cancelled respnse
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
//            Log.d(getLocalClassName(),"Name entered " + name);
            if (name.length() < 2) { // if name has only one character
                Log.d(getLocalClassName(), "Returning false because of length : " + name.length());
                return false;
            }
//            Log.d(getLocalClassName(),Boolean.toString(name.matches("[a-zA-Z]+")));
            if (!name.matches("[a-zA-Z]+")) {// if name contains other characters
//                Log.d(getLocalClassName(),"Returning false because of characters");

                return false;
            }

        }
//        Log.d(getLocalClassName(),"returning as Valid name");
        return true; // if control reaches here then Legal name
    }
}
