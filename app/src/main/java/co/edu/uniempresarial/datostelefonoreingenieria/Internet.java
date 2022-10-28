package co.edu.uniempresarial.datostelefonoreingenieria;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AppCompatActivity;

public class Internet extends AppCompatActivity {

    private String estadoConexion;
    ConnectivityManager conexion;
    private Context context;
    private Activity activity;

    public Internet(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public String getEstadoConexion() {
        return estadoConexion;
    }

    public void conexionVerificar(){

            conexion = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = conexion.getActiveNetworkInfo();
            boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (isConnected){
                estadoConexion = "Estas conectado a Internet";
            }
            else{
                estadoConexion = "No tienes Internet";
            }

    }
}
