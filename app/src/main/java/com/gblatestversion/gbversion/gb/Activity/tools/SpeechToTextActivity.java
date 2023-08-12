package com.gblatestversion.gbversion.gb.Activity.tools;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivitySpeechToTextBinding;

import java.util.ArrayList;

public class SpeechToTextActivity extends AppCompatActivity {
    ActivitySpeechToTextBinding binding;
    private static final String TAG = "STMultiLanguageActivity";
    private final int REQ_CODE_SPEECH_INPUT = 100;

    TextView mTextFromSpeech;
    Spinner mLanguageSpinner;

    String[] mLanguages = {"English", "Hindi", "Bengali", "Urdu", "Kannada", "Malayalam", "Telugu", "Gujarati", "Bulgarian", "Danish", "German", "Greek", "Spanish",
            "Finnish", "Filipino", "French", "Hausa", "Indonesian", "Italian", "Japanese", "Latvian",
            "Malay", "Dutch", "Polish", "Portuguese", "Romanian", "Slovak", "Thai", "Turkish", "Vietnamese", "Swedish"};

    String mSelectedLanguage = "en";

    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySpeechToTextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WFSAppLoadAds.getInstance().showNativeBottomDynamic(this, binding.frameViewAds);

        // binding.promptSpeechOutput.setVisibility(View.GONE);
        binding.promptSpeechOutput.setVisibility(View.VISIBLE);
        binding.warningMsg.setVisibility(View.GONE);
        binding.tool.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.tool.title.setText("Speech To Text");

        mTextFromSpeech = findViewById(R.id.input_voice_text);
        mLanguageSpinner = findViewById(R.id.spinner);


        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, mLanguages);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mLanguageSpinner.setAdapter(spinnerAdapter);

        mLanguageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        mSelectedLanguage = "en";
                        break;
                    case 1:
                        mSelectedLanguage = "hi";
                        break;
                    case 2:
                        mSelectedLanguage = "bn";
                        break;
                    case 3:
                        mSelectedLanguage = "ur";
                        break;
                    case 4:
                        mSelectedLanguage = "kn";
                        break;
                    case 5:
                        mSelectedLanguage = "ml";
                        break;
                    case 6:
                        mSelectedLanguage = "te";
                        break;
                    case 7:
                        mSelectedLanguage = "guj";
                        break;
                    case 8:
                        mSelectedLanguage = "bg";
                        break;
                    case 9:
                        mSelectedLanguage = "da";
                        break;
                    case 10:
                        mSelectedLanguage = "de";
                        break;
                    case 11:
                        mSelectedLanguage = "el";
                        break;
                    case 12:
                        mSelectedLanguage = "es";
                        break;
                    case 13:
                        mSelectedLanguage = "fi";
                        break;
                    case 14:
                        mSelectedLanguage = "fil";
                        break;
                    case 15:
                        mSelectedLanguage = "fr";
                        break;
                    case 16:
                        mSelectedLanguage = "ha";
                        break;
                    case 17:
                        mSelectedLanguage = "in";
                        break;
                    case 18:
                        mSelectedLanguage = "it";
                        break;
                    case 19:
                        mSelectedLanguage = "ja";
                        break;
                    case 20:
                        mSelectedLanguage = "iv";
                        break;

                    case 21:
                        mSelectedLanguage = "ms";
                        break;
                    case 22:
                        mSelectedLanguage = "nl";
                        break;
                    case 23:
                        mSelectedLanguage = "pl";
                        break;
                    case 24:
                        mSelectedLanguage = "pt";
                        break;
                    case 25:
                        mSelectedLanguage = "ro";
                        break;
                    case 26:
                        mSelectedLanguage = "sk";
                        break;
                    case 27:
                        mSelectedLanguage = "th";
                        break;
                    case 28:
                        mSelectedLanguage = "tr";
                        break;
                    case 29:
                        mSelectedLanguage = "vi";
                        break;
                    case 30:
                        mSelectedLanguage = "sv";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(SpeechToTextActivity.this, "Please Select Language", Toast.LENGTH_SHORT).show();
            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptSpeechInput();
            }
        });
        binding.copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SpeechToTextActivity.this.mTextFromSpeech.getText().toString().isEmpty()) {
                    Toast.makeText(SpeechToTextActivity.this.getApplicationContext(), getResources().getString(R.string.convert_before_copy), Toast.LENGTH_SHORT).show();
                    return;
                }
                ((ClipboardManager) SpeechToTextActivity.this.getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText(SpeechToTextActivity.this.mTextFromSpeech.getText().toString(), SpeechToTextActivity.this.mTextFromSpeech.getText().toString()));
                Toast.makeText(SpeechToTextActivity.this.getApplicationContext(), (getResources().getString(R.string.text_copied)), Toast.LENGTH_SHORT).show();
            }
        });
        binding.llShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SpeechToTextActivity.this.mTextFromSpeech.getText().toString().isEmpty()) {
                    Toast.makeText(SpeechToTextActivity.this.getApplicationContext(), (getResources().getString(R.string.convert_text_to_share)), Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.SEND");
                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.TEXT", SpeechToTextActivity.this.mTextFromSpeech.getText().toString());
                    SpeechToTextActivity.this.startActivity(intent);
                }
            }
        });
        binding.llDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextFromSpeech.setText("");
            }
        });
    }

    public void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, mSelectedLanguage);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                binding.warningMsg.setVisibility(View.GONE);
                binding.promptSpeechOutput.setVisibility(View.VISIBLE);
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mTextFromSpeech.setText(result.get(0));
                }
                break;
            }
        }
    }

}