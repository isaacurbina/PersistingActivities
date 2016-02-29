package com.mobileappsco.training.day3;

import android.content.Intent;
import android.os.Parcel;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "MYTAG123";
    private ShareActionProvider mShareActionProvider;
    Intent mShareIntent;
    String text;
    EditText edt1;
    Button btnParcel;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "  -->  onDestroy   1");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "  -->  onStop   1");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "  -->  onPause   1");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(LOG_TAG, "  -->  onRestoreInstanceState   1");
        text = savedInstanceState.getString("text").toString();
        edt1.setText(text);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "  -->  onResume   1");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "  -->  onRestart   1");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "  -->  onCreate   1");
        mShareIntent = new Intent();
        mShareIntent.setAction(Intent.ACTION_SEND);
        mShareIntent.setType("text/plain");
        mShareIntent.putExtra(Intent.EXTRA_TEXT, "From me to you, this text is new.");

        edt1 = (EditText) findViewById(R.id.textView);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        if (extras!=null) {
            String text = extras.getString("text");
            edt1.setText(text);
            Log.d(LOG_TAG, "  -->  1 text received with value " + text);
        }

        btnParcel = (Button) findViewById(R.id.btnParcel);
    }

    public void goNewActivity(View view) {
        Intent i = new Intent(this, SecondActivity.class);
        String text = edt1.getText().toString();
        i.putExtra("text", text);
        Log.d(LOG_TAG, "  -->  1 text sending value " + text);
        startActivity(i);
    }

    public void goBackStack(View view) {
        onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(LOG_TAG, "  -->  onSaveInstanceState   1");
        text = edt1.getText().toString();
        outState.putString("text", text);
        Log.d(LOG_TAG, "  -->  text   " + text);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(LOG_TAG, "  -->  onBackPressed   1");
        //    finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu resource file.
        getMenuInflater().inflate(R.menu.menu_share, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menuitem1);
        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        // Connect the dots: give the ShareActionProvider its Share Intent
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(mShareIntent);
        }

        // Return true to display menu
        return true;
    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    public void sendParcel(View view) {
        MyParcelable dataToSend = new MyParcelable();
        dataToSend.setmDate("06/04/1989");
        dataToSend.setmName("ISAAC URBINA");
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra("myParcel", dataToSend); // using the (String name, Parcelable value) overload!
        startActivity(i); // dataToSend is now passed to the new Activity
    }
}
