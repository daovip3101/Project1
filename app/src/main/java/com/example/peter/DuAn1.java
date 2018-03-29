package com.example.peter;

import android.app.Application;
import android.content.Context;

/**
 * Created by daovip on 3/29/2018.
 */

public class DuAn1 extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        DuAn1.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return DuAn1.context;
    }
}
