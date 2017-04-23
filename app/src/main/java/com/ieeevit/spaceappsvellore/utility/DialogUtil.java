package com.ieeevit.spaceappsvellore.utility;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by Karishnu Poddar on 28/08/2016.
 */
public class DialogUtil {
    public static void createDialog(String message, Activity activity, final OnPositiveButtonClick onPositiveButtonClick){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(onPositiveButtonClick!=null) {
                    onPositiveButtonClick.onClick();
                }
            }
        });
        alertDialogBuilder.show();
    }

    public interface OnPositiveButtonClick {
        void onClick();
    }
}
