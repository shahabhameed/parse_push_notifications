/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.folio3.parse;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseAnalytics;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "F3Scrum";
    public static final String KEY_PUSH_DATA = "com.parse.Data";
    public static final String FILE = "push_log.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        String filePath = getApplicationContext().getFilesDir().getPath().toString() + "/" + FILE;
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                System.out.println("Creating new file");
                file.createNewFile();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        /** When push message is clicked onCreate function of this activity is called */
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getString(KEY_PUSH_DATA) != null) {

            JSONObject pushData = null;
            try {
                pushData = new JSONObject(extras.getString(KEY_PUSH_DATA));
            } catch (JSONException e) {
                Log.e(TAG, "Unexpected JSONException when receiving push data: ", e);
            }

            String alert = null;
            if (pushData != null) {
                alert = pushData.optString("alert", null);
            }

            // Write log to the logfile
            if (alert != null) {
                writeLogToFile(getApplicationContext(), alert);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment pushFragment = new PushFragment();
                ft.replace(R.id.push_fragment, pushFragment);
                ft.commit();
            }
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void writeLogToFile(Context context, String alert) {
        String eol = System.getProperty("line.separator");
        BufferedWriter writer = null;
        try {
            FileOutputStream openFileOutput = context.openFileOutput(FILE, Context.MODE_APPEND);
            openFileOutput.write((alert + "\r\n").getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


}
