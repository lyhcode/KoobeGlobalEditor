package com.koobe.editor.common.client;

/**
 * Created by lyhcode on 2013/12/31.
 */
public class CommonStringUtils {
    public static String prettySize(long size) {
        if (size < 1024) {
            return String.format("%d bytes", size);
        }
        else if (size < 1024 * 1024) {
            return String.format("%d KB", size / 1024);
        }
        else if (size < 1024 * 1024 * 1024) {
            return String.format("%d MB", size / 1024 / 1024);
        }
        else if (size < 1024 * 1024 * 1024 * 1024) {
            return String.format("%d GB", size / 1024 / 1024 / 1024);
        }

        return String.valueOf(size);
    }
}
