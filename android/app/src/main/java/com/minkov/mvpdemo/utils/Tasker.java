package com.minkov.mvpdemo.utils;

import android.os.AsyncTask;

import java.util.function.Consumer;

public class Tasker {
    public static void runInBackground(Runnable action) {
        AsyncTask<Boolean, Void, Void> task = new AsyncTask<Boolean, Void, Void>() {
            @Override
            protected Void doInBackground(Boolean... _) {
                action.run();
                return null;
            }
        };

        task.execute(false);
    }
}
