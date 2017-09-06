package com.example.debs.androidarmishandball.restclient;

import android.util.Base64;

import com.example.debs.androidarmishandball.restclient.dto.*;

import java.util.ArrayList;
import java.util.List;


public class AppSession {
    public static User loggedUser;

    public static String getEncondedUserCredentials(){
        String plainCreds = AppSession.loggedUser.credentials();
        byte[] plainCredsBytes = plainCreds.getBytes();
        String base64Creds = Base64.encodeToString(plainCredsBytes, Base64.DEFAULT);

        return base64Creds;
    }
}
