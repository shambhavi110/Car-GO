package com.example.rajalakshmi.motionsensor;

/**
 * Created by RajaLakshmi on 22-Jun-15.
 */

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class prefsActivity extends PreferenceActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }
}


