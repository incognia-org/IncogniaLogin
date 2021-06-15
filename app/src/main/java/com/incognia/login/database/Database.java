package com.incognia.login.database;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.incognia.login.HomeActivity;

public class Database {


    public static String getLoggedUser(Context appContext){
        SharedPreferences sharedPreferences = appContext.getSharedPreferences("DATABASE", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("user","");
        return user;
    }

    public static boolean userExists(Context appContext, String email){
        SharedPreferences sharedPreferences = appContext.getSharedPreferences("DATABASE", Context.MODE_PRIVATE);
        String pass = sharedPreferences.getString(email,"");
        return !pass.isEmpty();
    }

    public static void addUser(Context appContext, String email, String password){
        SharedPreferences sharedPreferences = appContext.getSharedPreferences("DATABASE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(email, password);
        editor.commit();
    }

    public static boolean login(Context appContext, String email, String password){
        SharedPreferences sharedPreferences = appContext.getSharedPreferences("DATABASE", Context.MODE_PRIVATE);
        String dbPassword = sharedPreferences.getString(email,"");
        if(password.equals(dbPassword)){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("user", email);
            editor.commit();
            return true;
        }
        return false;
    }

    public static void logout(Context appContext){
        SharedPreferences sharedPreferences = appContext.getSharedPreferences("DATABASE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", "");
        editor.commit();
    }


}
