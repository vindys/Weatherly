package com.vindys.weatherly.worker;

import android.content.Context;

import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;

public interface ChildWorkerFactory {
    public ListenableWorker create(Context context, WorkerParameters params);
}
