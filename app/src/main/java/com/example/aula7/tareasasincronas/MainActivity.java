package com.example.aula7.tareasasincronas;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        ProgressBar progressBar;
        TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=(ProgressBar)findViewById(R.id.id_pb_loadata);
        textView=(TextView)findViewById(R.id.id_tv_data);

    }

    //  Metodo para validar el estado de la Red
    public Boolean isOnLine(){
        //Obtener el servicio de Conectividad en android
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //Obtener la informacion del estado de la red
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        if (networkInfo != null){
            return true;

        }else {
            return  false;

        }

    }

    //Evento para el Boton Principal

    public void loadData(View view){
     if(isOnLine()){

      MyTask myTask = new MyTask();
      myTask.execute();
     }else{
         Toast.makeText(this, "No hay Conexion a Internet", Toast.LENGTH_SHORT).show();
         }

    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //Metodo para Procesar los datos
    public void processData(String s){
        textView.setText("Numero:" +s);
        textView.setTextSize(Integer.parseInt(s));
    }
    // Clase para crear una tarea Asincrona
    public class MyTask extends AsyncTask<String, String , String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            for(int i = 1; i < 50; i++){
                publishProgress(String.valueOf(i));
            }
            return  "Fin";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            processData(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
        }
    }

}
