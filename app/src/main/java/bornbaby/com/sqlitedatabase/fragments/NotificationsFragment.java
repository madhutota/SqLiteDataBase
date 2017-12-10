package bornbaby.com.sqlitedatabase.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bornbaby.com.sqlitedatabase.MainActivity;
import bornbaby.com.sqlitedatabase.R;


public class NotificationsFragment extends Fragment {
    public static final String TAG = NotificationsFragment.class.getSimpleName();
    private View view;
    private MainActivity parent;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view != null)
            return view;

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notifications, container, false);
        return view;
    }

}
