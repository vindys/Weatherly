package com.vindys.weatherly.worker;

import android.content.Context;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.work.Constraints;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.testing.TestDriver;
import androidx.work.testing.TestListenableWorkerBuilder;
import androidx.work.testing.WorkManagerTestInitHelper;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class LoadWeatherWorkerTest {

    private Context mContext;
    @Before
    public void setUp() throws Exception {
        mContext = ApplicationProvider.getApplicationContext();
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Countdown latch
     */
    private CountDownLatch lock = new CountDownLatch(5);

    @Test
    public void testWorkSucceedByTime() throws Exception {
        // Define input data

        // Define constraints
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .build();

        // Create request
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(LoadWeatherWorker.class)
                .setConstraints(constraints)
                .build();

        WorkManager workManager = WorkManager.getInstance(mContext);
        TestListenableWorkerBuilder worker = TestListenableWorkerBuilder.from(mContext, request);
        // Enqueue
        workManager.enqueue(request).getResult().get();
        lock.countDown();
        lock.await(50000, TimeUnit.MILLISECONDS);
        // Tells the testing framework that all constraints are met.
        //testDriver.setAllConstraintsMet(request.getId());
        // Get WorkInfo and outputData
        WorkInfo workInfo = workManager.getWorkInfoById(request.getId()).get();

        // Assert
        assertThat(workInfo.getState(), CoreMatchers.is(WorkInfo.State.SUCCEEDED));

    }

    @Test
    public void startWork() {
    }
}