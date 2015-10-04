package com.example.tina.tayto;

import android.app.Activity;
import android.app.Application;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by tina on 8/5/15.
 */
public class GlobalState extends Application {
    String username;

    public void setVariables(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}