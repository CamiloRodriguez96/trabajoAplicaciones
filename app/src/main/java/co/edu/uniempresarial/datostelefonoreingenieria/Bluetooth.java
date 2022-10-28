package co.edu.uniempresarial.datostelefonoreingenieria;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

public class Bluetooth extends AppCompatActivity {

    private BluetoothAdapter btAdapter;
    private boolean flag = false;
    private Context context;
    private Activity activity;

    public Bluetooth(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
        getPermission();
    }

    //Habilitar y deshabilitar BT
    @SuppressLint("MissingPermission")
    public void habBT(View view){
        methodFlag();
        if(btAdapter==null){
            Snackbar.make(view,"Dispositivo no posee BT." ,Snackbar.LENGTH_SHORT).show();
            btAdapter.disable();
        }
        if(!btAdapter.isEnabled()){
            btAdapter.enable();
            Snackbar.make(view,"Bluetooth encendido." ,Snackbar.LENGTH_SHORT).show();
        }else{
            Snackbar.make(view,"Ya esta encendido." ,Snackbar.LENGTH_SHORT).show();
        }
    }
    //Deshabilitar BT
    @SuppressLint("MissingPermission")
    public void desBT(View view){
        methodFlag();
        if(btAdapter.isEnabled()){
            btAdapter.disable();
            Snackbar.make(view,"Bluetooth apagado." ,Snackbar.LENGTH_SHORT).show();
        }else{
            Snackbar.make(view,"Ya esta apagado." ,Snackbar.LENGTH_SHORT).show();
        }
    }
    private  void methodFlag(){
        if(!flag) {
            btAdapter = BluetoothAdapter.getDefaultAdapter();
            flag = true;
        }
    }
    public void getPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this.activity,Manifest.permission.BLUETOOTH)){
            Toast.makeText(context,"Es Necesario el permiso",Toast.LENGTH_SHORT).show();

        }else{
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.BLUETOOTH},100);
        }
    }


}
