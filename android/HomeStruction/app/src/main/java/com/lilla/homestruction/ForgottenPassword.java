package com.lilla.homestruction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by lilla on 21/09/16.
 */

public class ForgottenPassword extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotten_password);
        final TextView mTextView = (TextView) findViewById(R.id.txtDisplay);

        //TODO forgotten password screen (question + answer)
//
// Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url ="http://acs1.ddns.net:2080/api/temp/?page=2";
//
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        int ind = response.indexOf("value");
//                        ind = ind + 7;
//                        String temp = response.substring(ind, ind + 4);
//                        System.out.println(temp);
//                        mTextView.setText(response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
//            }
//        });
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);

    }
}
