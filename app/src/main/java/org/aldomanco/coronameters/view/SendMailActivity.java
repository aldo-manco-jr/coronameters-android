package org.aldomanco.coronameters.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.aldomanco.coronameters.R;

public class SendMailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView titleTextView;

    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;

    private Button sendMailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);

        mEditTextSubject = findViewById(R.id.edit_text_subject);
        mEditTextMessage = findViewById(R.id.edit_text_message);
        titleTextView = findViewById(R.id.title_send_mail_to);
        sendMailButton = findViewById(R.id.button_send_mail);

        sendMailButton.setOnClickListener(this);

        String title = "Invia Segnalazione\nalla Protezione Civile";
        titleTextView.setText(title);
        setTitle("Invia Segnalazione");
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
}
