package com.example.mohamed.sw2_1;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final int Erorr = 9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started");
        if (isServiceOK())
            init();
    }
    public boolean isServiceOK(){
        Log.d(TAG, "isServiceOK: Checking goole map version");
        int avail = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if (avail == ConnectionResult.SUCCESS){
            Log.d(TAG, "isServiceOK: Is found");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(avail)){
            Log.d(TAG, "isServiceOK: handeled error");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, avail, Erorr);
            dialog.show();;
        }
        else{
            Toast.makeText(this,"You cant make request", Toast.LENGTH_LONG).show();

        }
        return false;
    }
    private void init(){
        Button button = findViewById(R.id.buttonok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }
}
