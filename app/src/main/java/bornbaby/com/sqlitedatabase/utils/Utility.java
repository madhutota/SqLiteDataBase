package bornbaby.com.sqlitedatabase.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import bornbaby.com.sqlitedatabase.R;

/**
 * Created by madhu on 09-Dec-17.
 */

public class Utility {

    public static void navigateFragment(Fragment fragment, String tag, Bundle bundle, FragmentActivity fragmentActivity) {
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right);
        // fragmentTransaction.setCustomAnimations(R.anim.window_enter, R.anim.window_close);
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        fragmentTransaction.replace(R.id.content_main, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commitAllowingStateLoss();
        fragmentManager.executePendingTransactions();
    }

    public static String saveBitmap(Context context, Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/.SqLiteDataBase");
        if (myDir.exists()) {
            boolean statusFolder = myDir.delete();
            Log.v("FOLDER:--", "FOLDER STATUS:" + statusFolder);
        } else {
            boolean status = myDir.mkdirs();
            Log.v("FOLDER:--", "FOLDER STATUS:" + status);
        }

        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(new Date());
        String fname = "Photo_" + ts + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) {
            boolean status = file.delete();
            Log.v("DELETE:--", "DELETE STATUS:" + status);
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }



}
