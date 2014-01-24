package com.koobe.editor.common.server.database;

import com.koobe.common.core.config.KoobeConfig;
import com.koobe.common.data.security.DatabaseSecurityManager;
import com.koobe.common.data.security.impl.RDSDatabaseSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.www.content.text.PlainTextInputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Manage Amazon RDS Security
 */
public class DatabaseContextListener implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(DatabaseContextListener.class);

    private static final KoobeConfig config = KoobeConfig.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {


        try {
            String address = getServerIPAddress() + "/32";

            log.info("RDS allow {} {}", "ec2", address);

            DatabaseSecurityManager manager;
            manager = new RDSDatabaseSecurityManager(config.getAwsAccessKeyID(), config.getAwsSecretKey());
            manager.allow("koobe-globaleditor", address);
        }
        catch (IOException ex) {
            log.error(ex.getMessage());
        }
        catch (Exception ex) {
            log.error(ex.getMessage());
        }

        log.info("Database Context Initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        try {
            String address = getServerIPAddress() + "/32";

            log.info("RDS deny {} {}", "ec2", address);

            DatabaseSecurityManager manager;
            manager = new RDSDatabaseSecurityManager(config.getAwsAccessKeyID(), config.getAwsSecretKey());
            manager.deny("ec2", address);
        }
        catch (IOException ex) {
            log.error(ex.getMessage());
        }
        catch (Exception ex) {
            log.error(ex.getMessage());
        }


        log.info("Database Context Destroyed");
    }

    private String getServerIPAddress() throws IOException {
        URL url = new URL("http://ifconfig.me/ip");
        Object stream = url.getContent();

        BufferedReader reader = new BufferedReader(new InputStreamReader((PlainTextInputStream)stream));
        String response = reader.readLine();
        reader.close();

        return response;
    }
}
