package com.dcubetech.phpmysqlconnect;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;

//Create a request handler to handel all the request.
public class SharedPrefManager {
        private static SharedPrefManager instance;
        private RequestQueue requestQueue;
        private static Context ctx;

        private static final String SHARED_PREF_NAME = "mysharedpred";
        private static final String KEY_USER_ID = "userid";
        private static final String KEY_USER_NAME = "username";
        private static final String KEY_USER_EMAIL = "useremail";

        private SharedPrefManager(Context context) {
            ctx = context;
        }

        public static synchronized SharedPrefManager getInstance(Context context) {
            if (instance == null) {
                instance = new SharedPrefManager(context);
            }
            return instance;
        }

        public boolean userLogin(int id, String fullname, String email){
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(KEY_USER_ID, id);
            editor.putString(KEY_USER_EMAIL, email);
            editor.putString(KEY_USER_NAME, fullname);
            editor.apply();
            return true;
        }

        public boolean isLoggedIn(){
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(
                    SHARED_PREF_NAME, Context.MODE_PRIVATE);
            if(sharedPreferences.getString(KEY_USER_EMAIL, null) != null){
                return true;
            }
            return false;
        }
        public boolean logout(){
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            return true;
        }

        public String getUserName(){
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(KEY_USER_NAME, null);
        }

        public String getUserEmail(){
            SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(KEY_USER_EMAIL, null);
        }
}
