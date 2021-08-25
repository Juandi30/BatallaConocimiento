package com.juandi.batallaconocimiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ConsultarPelicula extends AppCompatActivity {
    EditText Nombre;
    ImageView Imagen;
    TextView ano, descripcion;
    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_pelicula);
        Nombre = (EditText) findViewById(R.id.idNombre);
        ano = (TextView) findViewById(R.id.idAnoT);
        Imagen = (ImageView) findViewById(R.id.idImagen);
        descripcion = (TextView) findViewById(R.id.idDescripcionT);
        regresar = (Button) findViewById(R.id.idRegresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void consultaporcodigo(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();
        String nombre = Nombre.getText().toString();


        Cursor fila = bd.rawQuery(
                "select año,url,descripcion from articulos where titulo like '" + nombre + "'", null);

        if (fila.moveToFirst()) {

            Toast.makeText(this, "Consultando Nombre ...",
                    Toast.LENGTH_SHORT).show();
            ano.setText(fila.getString(0));
            descripcion.setText(fila.getString(2));
            Picasso.get().load(fila.getString(1)).into(Imagen);

        } else
            Toast.makeText(this, "No existe Pelicula",
                    Toast.LENGTH_SHORT).show();
        bd.close();

    }
}