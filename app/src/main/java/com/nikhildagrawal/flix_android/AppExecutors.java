package com.nikhildagrawal.flix_android;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

    private static AppExecutors instance;

    public static AppExecutors getInstance(){

        if(instance == null){
            instance = new AppExecutors();
        }
        return instance;
    }


    private final ScheduledExecutorService mNetwotkIO = Executors.newScheduledThreadPool(3);


    public ScheduledExecutorService networkIO(){
        return mNetwotkIO;
    }

}
