package com.folio3.parse;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mshahab on 2015-10-24.
 */

public class PushFragment extends ListFragment {

    public static final String FILE = "push_log.txt";
    String[] log_text = new String[]{};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        List<String> logs = readFileFromInternalStorage(inflater.getContext(), FILE);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, logs.toArray(new String[logs.size()]));
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public List<String> readFileFromInternalStorage(Context context, String fileName) {

        List<String> logs = new ArrayList<String>();
        if (new File(context.getFilesDir().getPath().toString() + "/" + fileName).exists()) {

            String eol = System.getProperty("line.separator");
            BufferedReader input = null;
            try {
                input = new BufferedReader(new InputStreamReader(context.openFileInput(fileName)));
                String line;
                //StringBuffer buffer = new StringBuffer();
                while ((line = input.readLine()) != null) {
                    //buffer.append(line + eol);
                    logs.add(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return logs;
    }
}