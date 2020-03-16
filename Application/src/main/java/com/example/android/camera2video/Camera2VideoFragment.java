/*
 * Copyright 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.camera2video;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.camera2video.base.BaseFragment;
import com.example.android.camera2video.camera.CameraHelper;
import com.example.android.camera2video.camera.DefaultCameraHelper;
import com.example.android.camera2video.camera.ICameraStateListener;
import com.example.android.camera2video.widget.AutoFitTextureView;

import static com.example.android.camera2video.Constant.VIDEO_PERMISSIONS;

public class Camera2VideoFragment extends BaseFragment
        implements View.OnClickListener {

    private static final String TAG = "Camera2VideoFragment";

    /**
     * An {@link AutoFitTextureView} for camera preview.
     */
    private AutoFitTextureView mTextureView;

    /**
     * Button to record video
     */
    private Button mButtonVideo;


    /**
     * Whether the app is recording video now
     */
    private boolean mIsRecordingVideo;


    public static Camera2VideoFragment newInstance() {
        return new Camera2VideoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera2_video, container, false);
    }

    private CameraHelper mCameraHelper;

    private ICameraStateListener mCameraStateListener = new ICameraStateListener() {
        @Override
        public void onSurfaceTextureAvailable() {

        }

        @Override
        public void onCameraOpenError(Exception e) {
            e.printStackTrace();
            if (null != getActivity()) {
                getActivity().finish();
            }
        }

        @Override
        public void onCameraOpened() {

        }

        @Override
        public void onCameraDisconnected() {

        }

        @Override
        public void onStartRecordSuccess() {
            mButtonVideo.setText(R.string.stop);
            mIsRecordingVideo = true;
        }

        @Override
        public void onStartRecordFailed() {
            mIsRecordingVideo = false;
            Activity activity = getActivity();
            if (null != activity) {
                Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onStopRecord() {
            Activity activity = getActivity();
            if (null != activity) {
                Toast.makeText(activity, "保存成功", Toast.LENGTH_SHORT).show();
            }
            mIsRecordingVideo = false;
            mButtonVideo.setText(R.string.record);
        }
    };

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        mTextureView = (AutoFitTextureView) view.findViewById(R.id.texture);
        mButtonVideo = (Button) view.findViewById(R.id.video);
        mButtonVideo.setOnClickListener(this);
        view.findViewById(R.id.info).setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!hasPermissionsGranted(VIDEO_PERMISSIONS)) {
            requestVideoPermissions();
            return;
        }
        mCameraHelper = new DefaultCameraHelper();
        mCameraHelper.initCamera(getActivity(), mTextureView, mCameraStateListener);
    }

    @Override
    public void onPause() {
        if (mCameraHelper != null) {
            mCameraHelper.release();
            mCameraHelper = null;
        }
        super.onPause();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.video: {
                if (mIsRecordingVideo) {
                    mCameraHelper.stopRecord();
                } else {
                    mCameraHelper.startRecord();
                }
                break;
            }
            case R.id.info: {
                Activity activity = getActivity();
                if (null != activity) {
                    new AlertDialog.Builder(activity)
                            .setMessage(R.string.intro_message)
                            .setPositiveButton(android.R.string.ok, null)
                            .show();
                }
                break;
            }
        }
    }


}
