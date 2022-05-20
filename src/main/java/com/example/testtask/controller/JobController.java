package com.example.testtask.controller;

import com.example.testtask.service.JobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

    @GetMapping(value = "/instant")
    public void test() {
        Runnable runnableTask = createThread();
        jobService = new JobService(executorService);
        Future<?> future = jobService.submitNow(runnableTask);
        System.out.println("Thread bitdi mi? " + future.isDone());
    }

    @GetMapping(value = "/periodic/job")
    public void testPeriodic() {
        Runnable runnableTask = createThread();
        jobService = new JobService(executorService);
        Future<?> future = jobService.submitFixedTime(runnableTask);
        System.out.println("Thread bitdi mi? " + future.isDone());
    }

    private Runnable createThread() {
        return () -> {
            try {
                System.out.println("Thread is running...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
