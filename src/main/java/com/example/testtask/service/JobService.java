package com.example.testtask.service;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;

public class JobService {

    private final ScheduledExecutorService scheduler;

    public JobService(ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
    }

    public Future<?> submitNow(Runnable runnable) {
        Future<?> future = scheduler.submit(runnable);
        System.out.println("New task info: " + future);
        return future;
    }

    public ScheduledFuture<?> submitFixedTime(Runnable runnable) {
        ScheduledFuture<?> schedule = scheduler.schedule(runnable, 20, SECONDS);
        System.out.println("New task info: " + schedule);
        return schedule;
    }


}
