package org.aldomanco.coronameters.view.ui.report;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.aldomanco.coronameters.R;
import org.aldomanco.coronameters.view.OnBackPressed;

public class ReportFragment extends Fragment implements OnBackPressed, View.OnClickListener {

    private TextView titleTextView;

    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;

    private Button sendMailButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_send_mail, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditTextSubject = view.findViewById(R.id.edit_text_subject);
        mEditTextMessage = view.findViewById(R.id.edit_text_message);
        titleTextView = view.findViewById(R.id.title_send_mail_to);
        sendMailButton = view.findViewById(R.id.button_send_mail);

        sendMailButton.setOnClickListener(this);

        String title = "Invia Segnalazione\nalla Protezione Civile";
        titleTextView.setText(title);
    }

    public static ReportFragment newInstance() {
        ReportFragment fragment = new ReportFragment();
        return fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_send_mail:
                sendMail();
                break;
        }
    }

    public void sendMail() {
        String subject = mEditTextSubject.getText().toString();
        String message = mEditTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"protezionecivile@pec.governo.it"});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Scegli un Email Client"));
    }

    @Override
    public void onBackPressed() {

    }
}