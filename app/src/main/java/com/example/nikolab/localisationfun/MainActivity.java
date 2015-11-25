package com.example.nikolab.localisationfun;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    static private final int GET_TEXT_REQUEST_CODE = 1;

    Button mChangeDateAndTime;
    Button mChangeLanguage;
    Button mClearCheck;
    Button mAppInfo;
    Button mStartApp;
    String language;
    CheckBox mEnCheck;
    CheckBox mDeCheck;
    CheckBox mFrCheck;
    CheckBox mJaCheck;
    CheckBox mItCheck;
    CheckBox mEsCheck;
    CheckBox mPtCheck;
    CheckBox mKoCheck;
    CheckBox mZhCheck;
    CheckBox mRuCheck;
    CheckBox mTrCheck;
    CheckBox mArCheck;
    boolean[] checkboxArray = new boolean[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        final String pckgName = bundle.getString("message");
        final String appName = bundle.getString("name");

        getSupportActionBar().setTitle(appName);

        // First time only
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime-" + pckgName, false)) {
            clearCheckboxes();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime-" + pckgName, true);
            editor.apply();
        }

        // Initializing buttons and checkboxes
        mChangeDateAndTime = (Button) findViewById(R.id.ButtonDateAndTime);
        mChangeLanguage = (Button) findViewById(R.id.ButtonLanguage);
        mClearCheck = (Button) findViewById(R.id.clearCheck);
        mStartApp = (Button) findViewById(R.id.ButtonStartActivity);
        mAppInfo = (Button) findViewById(R.id.AppInfo);
        mEnCheck = (CheckBox) findViewById(R.id.enCheck);
        mDeCheck = (CheckBox) findViewById(R.id.deCheck);
        mFrCheck = (CheckBox) findViewById(R.id.frCheck);
        mJaCheck = (CheckBox) findViewById(R.id.jaCheck);
        mItCheck = (CheckBox) findViewById(R.id.itCheck);
        mEsCheck = (CheckBox) findViewById(R.id.esCheck);
        mPtCheck = (CheckBox) findViewById(R.id.ptCheck);
        mKoCheck = (CheckBox) findViewById(R.id.koCheck);
        mZhCheck = (CheckBox) findViewById(R.id.zhCheck);
        mRuCheck = (CheckBox) findViewById(R.id.ruCheck);
        mTrCheck = (CheckBox) findViewById(R.id.trCheck);
        mArCheck = (CheckBox) findViewById(R.id.arCheck);

        // Initializing OnClickListener
        mChangeDateAndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_DATE_SETTINGS));
            }
        });
        mChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Settings.ACTION_LOCALE_SETTINGS), GET_TEXT_REQUEST_CODE);
            }
        });

        mClearCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCheckboxes();
                loadArray(pckgName);
                //checkLang();
            }
        });

        mAppInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAppInfo(MainActivity.this, pckgName);

            }
        });

        mStartApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent launchIntent = getPackageManager().getLaunchIntentForPackage(pckgName);
                // startActivity(launchIntent);
                launchApp(pckgName);
            }
        });

        // Check for all checkboxes
        mEnCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkboxArray[0] = mEnCheck.isChecked();
            }
        });

        mDeCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkboxArray[1] = mDeCheck.isChecked();
            }
        });

        mFrCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkboxArray[2] = mFrCheck.isChecked();
            }
        });

        mJaCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkboxArray[3] = mJaCheck.isChecked();
            }
        });

        mItCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkboxArray[4] = mItCheck.isChecked();
            }
        });

        mEsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkboxArray[5] = mEsCheck.isChecked();
            }
        });

        mPtCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkboxArray[6] = mPtCheck.isChecked();
            }
        });

        mKoCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkboxArray[7] = mKoCheck.isChecked();
            }
        });

        mZhCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkboxArray[8] = mZhCheck.isChecked();
            }
        });

        mRuCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkboxArray[9] = mRuCheck.isChecked();
            }
        });

        mTrCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkboxArray[10] = mTrCheck.isChecked();
            }
        });

        mArCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkboxArray[11] = mArCheck.isChecked();
            }
        });

        loadArray(pckgName);
        checkLang();

    }

    private static void showAppInfo(Context context, String packageName) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", packageName, null);
        intent.setData(uri);
        context.startActivity(intent);
    }

    private void clearCheckboxes() {
        Arrays.fill(checkboxArray, Boolean.FALSE);
        Bundle bundle = getIntent().getExtras();
        final String pckgName = bundle.getString("message");
        save(checkboxArray, pckgName);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        checkLang();
        Log.wtf("TAG", language);

    }


    private void checkLang() {
        language = Locale.getDefault().getLanguage();
        switch (language) {
            case "en":
                mEnCheck.setChecked(true);
                checkboxArray[0] = true;
                break;
            case "de":
                mDeCheck.setChecked(true);
                checkboxArray[1] = true;
                break;
            case "fr":
                mFrCheck.setChecked(true);
                checkboxArray[2] = true;
                break;
            case "ja":
                mJaCheck.setChecked(true);
                checkboxArray[3] = true;
                break;
            case "it":
                mItCheck.setChecked(true);
                checkboxArray[4] = true;
                break;
            case "es":
                mEsCheck.setChecked(true);
                checkboxArray[5] = true;
                break;
            case "pt":
                mPtCheck.setChecked(true);
                checkboxArray[6] = true;
                break;
            case "ko":
                mKoCheck.setChecked(true);
                checkboxArray[7] = true;
                break;
            case "zh":
                mZhCheck.setChecked(true);
                checkboxArray[8] = true;
                break;
            case "ru":
                mRuCheck.setChecked(true);
                checkboxArray[9] = true;
                break;
            case "tr":
                mTrCheck.setChecked(true);
                checkboxArray[10] = true;
                break;
            case "ar":
                mArCheck.setChecked(true);
                checkboxArray[11] = true;
                break;
        }
    }

    private void save(final boolean[] isChecked, String packageName) {
        SharedPreferences sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < isChecked.length; i++) {
            editor.putBoolean("checkbox" + i, isChecked[i]);
            //Log.d("TAG SAVE" + packageName, String.valueOf(isChecked[i]));
        }
        editor.apply();
    }

    private void loadArray(String packageName) {
        SharedPreferences sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE);
        for (int i = 0; i < checkboxArray.length; i++) {
            checkboxArray[i] = sharedPreferences.getBoolean("checkbox" + i, true);
            Log.d("TAG" + packageName, String.valueOf(checkboxArray[i]));
            switch (i) {
                case 0:
                    mEnCheck.setChecked(checkboxArray[i]);
                    break;
                case 1:
                    mDeCheck.setChecked(checkboxArray[i]);
                    break;
                case 2:
                    mFrCheck.setChecked(checkboxArray[i]);
                    break;
                case 3:
                    mJaCheck.setChecked(checkboxArray[i]);
                    break;
                case 4:
                    mItCheck.setChecked(checkboxArray[i]);
                    break;
                case 5:
                    mEsCheck.setChecked(checkboxArray[i]);
                    break;
                case 6:
                    mPtCheck.setChecked(checkboxArray[i]);
                    break;
                case 7:
                    mKoCheck.setChecked(checkboxArray[i]);
                    break;
                case 8:
                    mZhCheck.setChecked(checkboxArray[i]);
                    break;
                case 9:
                    mRuCheck.setChecked(checkboxArray[i]);
                    break;
                case 10:
                    mTrCheck.setChecked(checkboxArray[i]);
                    break;
                case 11:
                    mArCheck.setChecked(checkboxArray[i]);
                    break;
            }
        }

    }

    // Start app
    protected void launchApp(String packageName) {
        Intent mIntent = getPackageManager().getLaunchIntentForPackage(
                packageName);
        if (mIntent != null) {
            try {
                startActivity(mIntent);
            } catch (ActivityNotFoundException err) {
                Toast t = Toast.makeText(getApplicationContext(),
                        "App not found", Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //Log.d("Lifecycle", "onPause");
        Bundle bundle = getIntent().getExtras();
        final String pckgName = bundle.getString("message");
        save(checkboxArray, pckgName);
    }

    public void onResume() {
        super.onResume();
        //Log.d("Lifecycle", "onResume");
        // loadArray();
    }

    public void onStop() {
        super.onStop();
        //Log.d("Lifecycle", "onStop");
    }

    public void onDestroy() {
        super.onDestroy();
        //Log.d("Lifecycle", "onDestroy");
    }

}


