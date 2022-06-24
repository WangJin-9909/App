package com.example.utils;

import android.util.Log;

public class AppLogger {
    private static boolean isLogEnable = false;

    public static void setIsLogEnable(boolean isLogEnable) {
        AppLogger.isLogEnable = isLogEnable;
    }

    private final static String TAG = "GMLogger";

    /**
     * Debug
     *
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        if (isLogEnable) {
            Log.d(tag, rebuildMsg(msg));
        }
    }

    public static void d(String msg) {
        if (isLogEnable) {
            Log.d(TAG, rebuildMsg(msg));
        }
    }

    /**
     * Information
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        if (isLogEnable) {
            Log.i(tag, rebuildMsg(msg));
        }
    }

    public static void i(String msg) {
        if (isLogEnable) {
            Log.i(TAG, rebuildMsg(msg));
        }
    }

    /**
     * Verbose
     *
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg) {
        if (isLogEnable) {
            Log.v(tag, rebuildMsg(msg));
        }
    }

    public static void v(String msg) {
        if (isLogEnable) {
            Log.v(TAG, rebuildMsg(msg));
        }
    }

    /**
     * Warning
     *
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        if (isLogEnable) {
            Log.w(tag, rebuildMsg(msg));
        }
    }

    public static void w(String msg) {
        if (isLogEnable) {
            Log.w(TAG, rebuildMsg(msg));
        }
    }

    /**
     * Error
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (isLogEnable) {
            Log.e(tag, rebuildMsg(msg));
        }
    }

    public static void e(String msg) {
        if (isLogEnable) {
            Log.e(TAG, rebuildMsg(msg));
        }
    }

    /**
     * Error
     *
     * @param tag
     * @param msg
     * @param e
     */
    public static void e(String tag, String msg, Exception e) {
        if (isLogEnable) {
            Log.e(tag, rebuildMsg(msg), e);
        }
    }

    public static void e(String msg, Exception e) {
        if (isLogEnable) {
            Log.e(TAG, rebuildMsg(msg), e);
        }
    }

    /**
     * Rebuild Log Msg
     *
     * @param msg
     * @return
     */
    private static String rebuildMsg(String msg) {

        StringBuffer sb = new StringBuffer();
        try {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
            sb.append("####    " + msg + " | ");
            sb.append(stackTraceElement.getFileName().substring(0, stackTraceElement.getFileName().length() - 5) + ".");
            sb.append(stackTraceElement.getMethodName() + "(");
            sb.append(+stackTraceElement.getLineNumber() + ")    ####\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
