package com.example.maxii.cargar;

import android.app.Activity;
import android.app.DatePickerDialog;
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

import java.util.Calendar;


public class MainActivity extends Activity {

    private EditText nombre, apellido, edad, llegada, salida, email;
    private Button btn;
    private DatePickerDialog llega, sale, fechaEdad;
    private Spinner spinner1;


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
        spinner1 = (Spinner) findViewById(R.id.spinner);
        String[] opciones = new String[10];
        for (int i = 0; i < opciones.length; i++) {
            opciones[i] = "Cabaña " + (i + 1);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);


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
        AdminSQLiteOpenHelper adminc = new AdminSQLiteOpenHelper(this,"cabana");
        AdminSQLiteOpenHelper admina = new AdminSQLiteOpenHelper(this,"Alquiladas");
        AdminSQLiteOpenHelper adminp = new AdminSQLiteOpenHelper(this,"Persona");
        SQLiteDatabase dbc = adminc.getWritableDatabase();
        SQLiteDatabase dba = admina.getWritableDatabase();
        SQLiteDatabase dbp = adminp.getWritableDatabase();

    }
}