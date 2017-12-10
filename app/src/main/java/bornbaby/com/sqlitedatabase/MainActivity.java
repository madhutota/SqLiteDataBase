package bornbaby.com.sqlitedatabase;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import bornbaby.com.sqlitedatabase.fragments.DashboardFragment;
import bornbaby.com.sqlitedatabase.fragments.HomeFragment;
import bornbaby.com.sqlitedatabase.fragments.NotificationsFragment;
import bornbaby.com.sqlitedatabase.utils.Utility;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    Bundle bundle = new Bundle();
                    Utility.navigateFragment(new HomeFragment(), HomeFragment.TAG, bundle, MainActivity.this);
                    // mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    Bundle navigationBundel = new Bundle();
                    Utility.navigateFragment(new DashboardFragment(), DashboardFragment.TAG, navigationBundel, MainActivity.this);
                    // mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    Bundle notificationBundel = new Bundle();
                    Utility.navigateFragment(new NotificationsFragment(), NotificationsFragment.TAG, notificationBundel, MainActivity.this);
                    // mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // hideSystemUI();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Bundle bundle = new Bundle();
        Utility.navigateFragment(new HomeFragment(), HomeFragment.TAG, bundle, MainActivity.this);



    }
    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        // remove the following flag for version < API 19
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );
    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream =getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                iv_selectedImage.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(PostImage.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(PostImage.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }
*/
}
