package com.example.maxii.cargar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends Activity {

    public EditText nombre, apellido, etDni, llegada, salida, email;
    public Button btn;
    public DatePickerDialog llega, sale;
    public Spinner spinner1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = (EditText) findViewById(R.id.etNombre);
        apellido = (EditText) findViewById(R.id.etApe);
        llegada = (EditText) findViewById(R.id.etLlegada);
        salida = (EditText) findViewById(R.id.etSalida);
        email = (EditText) findViewById(R.id.etEmail);
        btn = (Button) findViewById(R.id.btn);
        etDni = (EditText)findViewById(R.id.etDni);

        llegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final Calendar calendarL = Calendar.getInstance();
                llega = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        calendarL.set(Calendar.YEAR, i);
                        calendarL.set(Calendar.MONTH, i1);
                        calendarL.set(Calendar.DAY_OF_MONTH, i2);

                        String fecha = DateUtils.formatDateTime(view.getContext(), calendarL.getTimeInMillis(), DateUtils.FORMAT_NUMERIC_DATE);

                        String[] tmpArray = fecha.split("/");
                        String ret = "";

                        String tmp = tmpArray[0];
                        tmpArray[0] = tmpArray[1];
                        tmpArray[1] = tmp;

                        for (int j = 0; j < tmpArray.length; j++) {
                            ret += tmpArray[j];
                            if (!(j == tmpArray.length - 1)) {
                                ret += "/";

                            }
                        }

                        llegada.setText(ret);

                    }
                }, calendarL.get(Calendar.YEAR), calendarL.get(Calendar.MONTH), calendarL.get(Calendar.DAY_OF_MONTH));

                llega.show();
            }


        });


        salida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                final Calendar calendarS = Calendar.getInstance();
                sale = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        calendarS.set(Calendar.YEAR, i);
                        calendarS.set(Calendar.MONTH, i1);
                        calendarS.set(Calendar.DAY_OF_MONTH, i2);

                        String fecha = DateUtils.formatDateTime(view.getContext(), calendarS.getTimeInMillis(), DateUtils.FORMAT_NUMERIC_DATE);

                        String[] tmpArray = fecha.split("/");
                        String ret = "";

                        String tmp = tmpArray[0];
                        tmpArray[0] = tmpArray[1];
                        tmpArray[1] = tmp;

                        for (int j = 0; j < tmpArray.length; j++) {
                            ret += tmpArray[j];
                            if (!(j == tmpArray.length - 1)) {
                                ret += "/";

                            }
                        }
                        salida.setText(ret);

                    }
                }, calendarS.get(Calendar.YEAR), calendarS.get(Calendar.MONTH), calendarS.get(Calendar.DAY_OF_MONTH));

                sale.show();
            }


        });
        // todo no carga los datos en el spinner
        AdminSQLiteOpenHelper adminc = new AdminSQLiteOpenHelper(this,"cabana");
        AdminSQLiteOpenHelper admina = new AdminSQLiteOpenHelper(this,"Alquiladas");
        SQLiteDatabase dbc = adminc.getWritableDatabase();
        SQLiteDatabase dba = admina.getWritableDatabase();
        List<String> labels = new ArrayList<>();
        spinner1 = (Spinner) findViewById(R.id.spinner);
        Cursor fila = dba.rawQuery("select * from Alquiladas", null);
        if (fila.moveToFirst()){ //si tiene algo en la tabla que ponga el cursor en la posicion 0
            Cursor filaT = dbc.rawQuery("select * from cabanas where id='"+labels+"'", null);
            if (filaT.moveToFirst()){
                do {
                    labels.add(filaT.getString(1)); //mientras va avanzando uno en uno agrega lo que tiene a un vector que se agrega al spinner
                } while (filaT.moveToNext());
            }
        }else{ //Cuando no hay nada en la tabla de alquiladas que saque * lo de cabañas porque que no hay nada alquilado
            Cursor filaT = dbc.rawQuery("select * from cabanas", null);
            if (filaT.moveToFirst()) {
                do {
                    labels.add(filaT.getString(1));
                } while (filaT.moveToNext());
            }
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, labels);
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void cargar(View view) {
        //Abrimos la base de datos y le pedimos las tablas.
        AdminSQLiteOpenHelper adminc = new AdminSQLiteOpenHelper(this,"cabana");
        AdminSQLiteOpenHelper admina = new AdminSQLiteOpenHelper(this,"Alquiladas");
        AdminSQLiteOpenHelper adminp = new AdminSQLiteOpenHelper(this,"Persona");
        SQLiteDatabase dbc = adminc.getWritableDatabase();
        SQLiteDatabase dba = admina.getWritableDatabase();
        SQLiteDatabase dbp = adminp.getWritableDatabase();

        ContentValues foo = new ContentValues();
        foo.put("nombre", "hola");
        dbc.insert("cabanas", null, foo);

        //Get a las variables para poder guardarlas.
        String name = nombre.getText().toString() + apellido.getText().toString() ;
        String mail = email.getText().toString();
        int dni = Integer.parseInt(etDni.getText().toString());
        String nombreCabana = "hola";   //spinner1.getSelectedItem().toString();
        Cursor fil = dbc.rawQuery("select * from cabanas where nombre like '" + nombreCabana + "'", null);
        fil.moveToFirst();
        String id = fil.getString(0);
        int aux_id = Integer.parseInt(id);
        ContentValues toAlquilada = new ContentValues();
        toAlquilada.put(Cabana.KEY_ID,aux_id);
        toAlquilada.put(Alquiladas.KEY_checkin,llegada.getText().toString());
        toAlquilada.put(Alquiladas.KEY_checkout, salida.getText().toString());
        ContentValues toPersona = new ContentValues();
        toPersona.put(Cabana.KEY_ID,aux_id);
        toPersona.put(Persona.kEY_dni,dni);
        toPersona.put(Persona.KEY_nombre,name);
        toPersona.put(Persona.KEY_email, mail);
        dba.insert("Alquiladas", null, toAlquilada);
        dbp.insert("Persona", null, toPersona);
        dba.close();
        dbc.close();
        dbp.close();
        nombre.setText("");
        apellido.setText("");
        email.setText("");
        etDni.setText("");
        Toast.makeText(this,"Guardado correctamente",Toast.LENGTH_LONG).show();
        System.out.println(toPersona.toString());
        System.out.println(toAlquilada.toString());
    }
}