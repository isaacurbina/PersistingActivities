package com.mobileappsco.training.day3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String LOG_TAG = "MYTAG123";
    static int TAKE_PICTURE = 1;
    EditText edt2;
    ImageView imgviewcam;
    Bitmap bitMap;
    Button btnCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d(LOG_TAG, "  -->  onRestart   2");

        edt2 = (EditText) findViewById(R.id.sEditT);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras!=null) {
            String text = extras.getString("text");
            edt2.setText(text);
            Log.d(LOG_TAG, "  -->  2 text received with value " + text);
        }

        // add onclick listener to the button
        btnCam = (Button) findViewById(R.id.btnCam);
        imgviewcam = (ImageView) findViewById(R.id.imgviewcam);
        btnCam.setOnClickListener(this);

        if (getIntent().hasExtra("myParcel")) {
            MyParcelable myParcel = (MyParcelable) getIntent().getParcelableExtra("myParcel");
            Toast.makeText(this, "Parceable received: *** "+myParcel.getmName()+" *** "+myParcel.getmDate(), Toast.LENGTH_LONG).show();
            Log.d(LOG_TAG, "  -->  2 parcel received "+myParcel.toString());
        } else {
            Log.d(LOG_TAG, "  -->  2 no parcel ");
        }

    }

    public void btnClicked(View view) {
        Intent i = new Intent(this, MainActivity.class);
        String text = edt2.getText().toString();
        i.putExtra("text", text);
        Log.d(LOG_TAG, "  -->  2 text sending value " + text);
        startActivity(i);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "  -->  onSaveInstanceState   2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "  -->  onDestroy   2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "  -->  onStop   2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "  -->  onPause   2");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(LOG_TAG, "  -->  onRestoreInstanceState   2");
        savedInstanceState.getString("text");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "  -->  onResume   2");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "  -->  onRestart   2");
    }

    // on button "btnTackPic" is clicked
    @Override
    public void onClick(View view) {

        // create intent with ACTION_IMAGE_CAPTURE action
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // start camera activity
        startActivityForResult(intent, TAKE_PICTURE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == TAKE_PICTURE && resultCode== RESULT_OK && intent != null){
            // get bundle
            Bundle extras = intent.getExtras();

            // get bitmap
            bitMap = (Bitmap) extras.get("data");
            imgviewcam.setImageBitmap(bitMap);

        }
    }

}
