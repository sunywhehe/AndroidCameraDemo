package com.example.android.camera2video.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v13.app.FragmentCompat;

import com.example.android.camera2video.R;

import static com.example.android.camera2video.Constant.REQUEST_VIDEO_PERMISSIONS;
import static com.example.android.camera2video.Constant.VIDEO_PERMISSIONS;

/**
 * @author leosun
 * Created by Leosun on 2020/3/5 10:33
 */
public class ConfirmationDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Fragment parent = getParentFragment();
        return new AlertDialog.Builder(getActivity())
                .setMessage(R.string.permission_request)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FragmentCompat.requestPermissions(parent, VIDEO_PERMISSIONS, REQUEST_VIDEO_PERMISSIONS);
                    }
                })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                parent.getActivity().finish();
                            }
                        })
                .create();
    }

}
