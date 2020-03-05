package com.example.android.camera2video.camera;

import android.app.Activity;

import com.example.android.camera2video.AutoFitTextureView;

/**
 * @author leosun
 * Created by Leosun on 2020/3/5 09:58
 */
public interface CameraHelper {

    /**
     * 初始化 helper
     *
     * @param textureView
     * @param listener
     */
    public void initCamera(Activity activity, AutoFitTextureView textureView, ICameraStateListener listener);

    /**
     * 开启相机，投射到 textureView
     */
    public void openCamera(int width, int height);

    /**
     * 开启 录屏
     */
    public void startRecord();

    /**
     * 关闭录屏
     */
    public void stopRecord();

    /**
     * 释放资源，防止内存泄漏
     */
    public void release();
}
