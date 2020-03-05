package com.example.android.camera2video;

import android.Manifest;

/**
 * @author leosun
 * Created by Leosun on 2020/3/5 10:34
 */
public class Constant {
    public static final String[] VIDEO_PERMISSIONS = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
    };

    public static final int REQUEST_VIDEO_PERMISSIONS = 1;
}
