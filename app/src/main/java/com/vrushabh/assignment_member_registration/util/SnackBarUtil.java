package com.vrushabh.assignment_member_registration.util;

import android.view.View;
import com.google.android.material.snackbar.Snackbar;

public class SnackBarUtil {

    public static void showSnackBar(View root, String msg) {
        Snackbar.make(root, msg, Snackbar.LENGTH_SHORT).show();
    }
}
