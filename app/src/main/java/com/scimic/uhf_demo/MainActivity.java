package com.scimic.uhf_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.scimic.uhf_demo.Service.PgConection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private PgConection conection;
    private List<String> dataspinner=new ArrayList<>();
    private Spinner spinner;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner=(Spinner)findViewById(R.id.spinner1);
        sqlThread.start();
        ArrayAdapter<String> dataadapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,dataspinner);
        dataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataadapter);

    }

    Thread sqlThread=new Thread(){
        public void run(){
            conection=new PgConection();
            String stsql = "select stk_almacen.almacen from stk_almacen where stk_almacen.tipo=1";
            Statement st = null;

            try {
                st = conection.getConection().createStatement();
                ResultSet rs = st.executeQuery(stsql);
                while(rs.next()){
                    dataspinner.add(rs.getString(1));
                }

                conection.getConection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    };
}
