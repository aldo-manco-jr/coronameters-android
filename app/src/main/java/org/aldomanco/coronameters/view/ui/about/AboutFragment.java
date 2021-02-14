package org.aldomanco.coronameters.view.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.aldomanco.coronameters.R;
import org.aldomanco.coronameters.view.OnBackPressed;

public class AboutFragment extends Fragment implements OnBackPressed {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        return root;
    }

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    @Override
    public void onBackPressed() {

    }
}