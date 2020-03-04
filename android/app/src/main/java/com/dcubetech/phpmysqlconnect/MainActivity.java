package com.dcubetech.phpmysqlconnect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // get all the tags from the layout
    EditText fullname,email,password;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get all the tags
        fullname = (EditText) findViewById(R.id.fullname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signup = (Button) findViewById(R.id.signup);
//        Set on click listener on sign up button
        signup.setOnClickListener(this);
    }

/*
 * on click will implements View.OnClickListener and calls the function onClick
 * inside onclick call another function for register
 * currently there is only one button so no need to set conditions to check which button is clicked
 */

    @Override
    public void onClick(View v) {
        // check button clicked is register button or not...
        if(v == signup){
            register();// call the register function
        }
    }

    private void register() {
//        Toast.makeText(getApplicationContext(), "Register", Toast.LENGTH_SHORT).show();
        /*
        *   get text in entered by user in edit text
        * */
        final String fn = fullname.getText().toString().trim();
        final String em = email.getText().toString().trim();
        final String pwd = password.getText().toString().trim();

        /*
        *   Now create a string request to save data through your php script
        */
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                new Response.Listener<String>() { //create a response if the php scripts works
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() { //create error response if data is not inserted..
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){ // pass the values from the edit text to your php script.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("fullname", fn);
                params.put("email", em);
                params.put("password", pwd);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest); // call to request handler class for making the request queue
    }

}
