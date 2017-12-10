package bornbaby.com.sqlitedatabase.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import bornbaby.com.sqlitedatabase.MainActivity;
import bornbaby.com.sqlitedatabase.R;
import bornbaby.com.sqlitedatabase.helper.SqliteHelper;
import bornbaby.com.sqlitedatabase.model.Shop;

import static android.app.Activity.RESULT_OK;


public class HomeFragment extends Fragment {
    public static final String TAG = HomeFragment.class.getSimpleName();
    private View view;
    private MainActivity parent;

    private Button btn_pick;
    private ImageView iv_selectedImage;
   /* private String Uri
    imageUri ="content://media/external/images/media/1465";*/

    private static final int RESULT_LOAD_IMG = 111;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = (MainActivity) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null)
            return view;

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        inItUi();
        return view;
    }

    private void inItUi() {
        btn_pick = view.findViewById(R.id.btn_pick);
        iv_selectedImage = view.findViewById(R.id.iv_selectedImage);

        btn_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);

            }
        });


        SqliteHelper db = new SqliteHelper(parent);

        // Inserting Shop/Rows
        Log.d("Insert: ", "Inserting ..");
        db.addShop(new Shop("Dockers", " 475 Brannan St #330, San Francisco, CA 94107, United States"));
        db.addShop(new Shop("Dunkin Donuts", "White Plains, NY 10601"));
        db.addShop(new Shop("Pizza Porlar", "North West Avenue, Boston , USA"));
        db.addShop(new Shop("Town Bakers", "Beverly Hills, CA 90210, USA"));

        // Reading all shops
        Log.d("Reading: ", "Reading all shops..");
        List<Shop> shops = db.getAllShops();

        for (Shop shop : shops) {
            String log = "Id: " + shop.getId() + " ,Name: " + shop.getName() + " ,Address: " + shop.getAddress();
            // Writing shops  to log
            Log.d("Shop: : ", log);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                assert imageUri != null;
                final InputStream imageStream = parent.getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                Log.d(TAG, "Selected Image >>>>" + imageUri);
                Log.d(TAG, "Selected imageStream  >>>>" + imageStream);
                iv_selectedImage.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(parent, "", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(parent, "You haven't picked Image", Toast.LENGTH_SHORT).show();
        }
    }
}
