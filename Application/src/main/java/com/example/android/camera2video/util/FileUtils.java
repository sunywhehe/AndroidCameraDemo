package com.example.android.camera2video.util;

import android.content.Context;

import java.io.File;

/**
 * @author leosun
 * Created by Leosun on 2020/3/5 16:31
 */
public class FileUtils {
    public static String getVideoFilePath(Context context) {
        final File dir = context.getExternalFilesDir(null);
        return (dir == null ? "" : (dir.getAbsolutePath() + "/"))
                + System.currentTimeMillis() + ".mp4";
    }

}
