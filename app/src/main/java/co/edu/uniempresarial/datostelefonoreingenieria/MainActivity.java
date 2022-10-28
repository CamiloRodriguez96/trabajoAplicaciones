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

public class MainActivity extends AppCompatActivity {

    private Context context;
    private Activity activity;
    private TextView tvVersionAndroid;
    private TextView tvLevelAndroid;
    private TextView tvState;
    private EditText nameFile;
    private Archivo archivo;
    private Internet internet;

    IntentFilter batteryFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        activity = this;

        begin();
        //1.Versiòn
        obtenerVersion();
        //2.BT se hace con ONclick
        //3.Bateria
        obtenerBateria();
        //4.File se hace con ONclick
        //5.Conexion
        conocerInternet();
    }
    //1----------------------------.Versiòn.-----------------------------------------//
    public void obtenerVersion(){
        VersionCelular version = new VersionCelular();
        tvVersionAndroid.setText(version.getTvVersionAndroid());
    }
    //2----------------------------.Bluetooth.-----------------------------------------//
    public void prenderBt(View view){
        Bluetooth BT = new Bluetooth(context,activity);
        BT.habBT(view);
    }
    public void apagarBt(View view){
        Bluetooth BT = new Bluetooth(context,activity);
        BT.desBT(view);
    }
    //3----------------------------.Bateria.-----------------------------------------//
    public void obtenerBateria(){
        Baterry baterry = new Baterry(context,activity);

//        BroadcastReceiver b1 = baterry.enviarBro();
//        batteryFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//        registerReceiver(b1, batteryFilter);
//        tvLevelAndroid.setText(baterry.getTvLevelAndroid());
    }

    //4----------------------------.Archivo.-----------------------------------------//
    public void saveFile(View view){
        String name = nameFile.getText().toString()+".txt";
        String dataBattery = "20%";
        archivo = new Archivo(context,activity);
        archivo.saveFile(name,dataBattery);
    }
    //5----------------------------.Internet.-----------------------------------------//
    public void conocerInternet(){
        internet = new Internet(context,activity);
        internet.conexionVerificar();
        tvState.setText(internet.getEstadoConexion());
    }

    private  void begin(){
        tvVersionAndroid = findViewById(R.id.tvVersionAndroid);
        tvLevelAndroid = findViewById(R.id.tvLevelBaterry);
        nameFile = findViewById(R.id.etNameFile);
        tvState = findViewById(R.id.tvState);
    }

}