package com.scimic.uhf_demo.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgConection {

    private String drive="org.postgresql.Driver";
    private String username="postgres";
    private String password="cildgbhiegbbile";
    private String db="posperu2";

    private Connection conn=null;

    public PgConection(){

    }

    public Connection getConection(){

        try {
            Class.forName(drive);
            try {
                conn = DriverManager.getConnection("jdbc:postgresql://192.168.1.24:5432/" + db, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }



}
