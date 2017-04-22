package com.example.kritik.dralt.Application;

import android.app.Application;

import com.example.kritik.dralt.Utility.Commons;

/**
 * Created by Kritik on 09-Apr-17.
 */

public class Init extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        new Commons();
    }
}
