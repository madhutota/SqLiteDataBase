package bornbaby.com.sqlitedatabase.helper;

import android.app.Application;
import android.content.Context;

/**
 * Created by madhu on 09-Dec-17.
 */

public class MyAppplication extends Application {


    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        MyAppplication.context = getApplicationContext();
    }

    public static Context getContext() {
        return MyAppplication.context;


    }
}
