package com.koobe.editor.common.server.uploader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Run s3upload and converter jobs
 */
public class UploadContextListener implements ServletContextListener {

    private final static String executorServiceName = UploadContextListener.class.getName();

    private final static ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ServletContext context = servletContextEvent.getServletContext();

        context.setAttribute(getExecutorServiceName(), executorService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        executorService.shutdown();
    }

    public String getExecutorServiceName() {
        return executorServiceName;
    }

    public ExecutorService getExecutor() {
        return executorService;
    }

    public static void executeJob(Runnable job) {
        executorService.execute(job);
    }
}
