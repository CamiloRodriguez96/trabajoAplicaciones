package co.edu.uniempresarial.datostelefonoreingenieria;

import android.os.Build;

public class VersionCelular {
    private String tvVersionAndroid;

    public VersionCelular(){
        obtenerVersionCelular();
    }

    public String getTvVersionAndroid() {
        return tvVersionAndroid;
    }

    public void obtenerVersionCelular(){
        try {
            String versionSO = Build.VERSION.RELEASE;
            int versionSDK = Build.VERSION.SDK_INT;
            tvVersionAndroid = "Version SO : "+ versionSO + " SDK :" + versionSDK;

        }catch (NumberFormatException e){
            System.out.println("Error en generar la Versiòn");
            tvVersionAndroid = "Version no encontrada";
        }

    }
}
