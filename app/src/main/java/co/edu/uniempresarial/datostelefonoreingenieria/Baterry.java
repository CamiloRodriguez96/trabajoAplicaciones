package co.edu.uniempresarial.datostelefonoreingenieria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Baterry extends AppCompatActivity {

    private Context context;
    private Activity activity;

    IntentFilter batteryFilter;
    private String tvLevelAndroid;


    public Baterry(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;

        batteryFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(bateriaCarga,batteryFilter);
    }

    public BroadcastReceiver bateriaCarga = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            tvLevelAndroid = "50";
            int nivelActual = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
            int escala = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
            if(escala!=100){
                int nivel = 0;
                if((nivelActual>=0) && (escala>0)) {
                    nivel = (nivelActual*100)/escala;
                    tvLevelAndroid = "Bateria : "+nivel+"%";
                }
            }else{
                tvLevelAndroid = "Bateria : "+nivelActual+"%";
            }

        }
    };

    public String getTvLevelAndroid() {
        return tvLevelAndroid;
    }

    public void setTvLevelAndroid(String tvLevelAndroid) {
        this.tvLevelAndroid = tvLevelAndroid;
    }

}