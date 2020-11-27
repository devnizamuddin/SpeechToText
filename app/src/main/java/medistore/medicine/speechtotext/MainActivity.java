package medistore.medicine.speechtotext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageButton speech_ib;
    private TextView text_tv;
    //request code for getting voice.
    private static final int REQUEST_CODE_VOICE_INPUT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speech_ib = findViewById(R.id.speech_ib);
        text_tv = findViewById(R.id.text_tv);

        speech_ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gettingVoice();
            }
        });


    }

    private void gettingVoice() {

        //starting getting voice intent.
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        //show to user
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi I am speaking...");

        try {
            startActivityForResult(intent, REQUEST_CODE_VOICE_INPUT);
        } catch (Exception e) {

            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_VOICE_INPUT && resultCode == RESULT_OK && data != null) {


            //getting string from voice.
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            text_tv.setText(results.get(0));
        }
        else {
            Toast.makeText(this, "Something error..", Toast.LENGTH_SHORT).show();
        }
        
    }
}