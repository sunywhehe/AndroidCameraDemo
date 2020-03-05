package com.example.android.camera2video.camera;

/**
 * @author leosun
 * Created by Leosun on 2020/3/5 10:07
 */
public interface ICameraStateListener {
    /**
     * Texture 准备完毕，可用
     */
    public void onSurfaceTextureAvailable();

    /**
     * 打开 camera 错误
     *
     * @param e
     */
    public void onCameraOpenError(Exception e);

    public void onCameraOpened();

    public void onCameraDisconnected();

    public void onStartRecordSuccess();

    public void onStartRecordFailed();

    public void onStopRecord();

}
