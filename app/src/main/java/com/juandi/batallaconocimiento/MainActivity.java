package com.juandi.batallaconocimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    EditText Codigo,Titulo,Ano,Url;
    TextInputEditText Descripcion;
    Button COnsultar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Codigo = (EditText) findViewById(R.id.idCodigo);
        Titulo = (EditText) findViewById(R.id.idTitulo);
        Ano = (EditText) findViewById(R.id.idAno);
        Url = (EditText) findViewById(R.id.idUrl);
        Descripcion = (TextInputEditText) findViewById(R.id.idDescripcion);
        COnsultar = (Button)findViewById(R.id.idConsultar);


        COnsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ConsultarPelicula.class);
                startActivity(intent);
            }
        });
    }
    public void ingresar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = Codigo.getText().toString();
        String titulo = Titulo.getText().toString();
        String ano = Ano.getText().toString();
        String url = Url.getText().toString();
        String descri = Descripcion.getText().toString();
        bd.execSQL("insert into articulos (codigo,titulo,año,url,descripcion) values ("+cod+",'"+titulo+"',"+ano+","+url+","+url+")");
        bd.close();
        Codigo.setText("");
        Titulo.setText("");
        Ano.setText("");
        Url.setText("");
        Descripcion.setText("");
        Toast.makeText(this, "Se cargaron los datos del artículo",
                Toast.LENGTH_SHORT).show();
    }
}