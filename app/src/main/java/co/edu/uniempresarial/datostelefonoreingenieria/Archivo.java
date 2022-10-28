package co.edu.uniempresarial.datostelefonoreingenieria;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileWriter;

public class Archivo {
    public static final int CODE = 200;
    private Context context;
    private Activity activity;

    public Archivo(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public boolean statusPermisoSD(){
        int respuesta = ContextCompat.checkSelfPermission
                (this.context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if ((respuesta == PackageManager.PERMISSION_GRANTED))return true;
        else return false;
    }
    public void solicitarPermisoWES(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (ActivityCompat.shouldShowRequestPermissionRationale
                    (this.activity, Manifest.permission.WRITE_EXTERNAL_STORAGE))
                Toast.makeText(context, "El permiso ha sido negado, pero se necesita para guardar el archivo", Toast.LENGTH_SHORT).show();
            else {
                ActivityCompat.requestPermissions(this.activity, new String[]
                        {Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE);
                Toast.makeText(context, "Ortorgada", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void createDir(File file){
        if(!file.exists()){
            file.mkdirs();
        }
    }

    public void saveFile(String nameFile,String info){
        File directorio = null;
        solicitarPermisoWES();
        if(statusPermisoSD()){
            try {
                if(Build.VERSION.SDK_INT < Build.VERSION_CODES.P){
                    directorio = new File(Environment.getExternalStorageDirectory(),"ArchivoAPP");
                    createDir(directorio);
                    Toast.makeText(context, "Ruta: "+directorio, Toast.LENGTH_LONG).show();
                }else{
                    directorio = new File(context.getExternalFilesDir(Environment.DIRECTORY_DCIM),"ArchivoAPP");
                    createDir(directorio);
                    Toast.makeText(context, "Ruta: "+directorio, Toast.LENGTH_LONG).show();
                }
                if(directorio != null){
                    // Creación del archivo
                    File file = new File(directorio, nameFile);
                    FileWriter writer = new FileWriter(file);
                    writer.append(info);
                    writer.flush();
                    writer.close();
                    Toast.makeText(context, "Se ha guardado el archivo", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context, "No se pudo crear el directorio", Toast.LENGTH_LONG).show();
                }
            }
            catch (Exception exception){
                exception.printStackTrace();
                Toast.makeText(context, ""+exception, Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(context, "No hay permiso", Toast.LENGTH_LONG).show();

        }
    }

}
