package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appdoctruyen.database.databasedoctruyen;

public class Mandangnhap extends AppCompatActivity {
    EditText edtTaiKhoan, edtMatkhau;
    Button btnDangNhap, btnDangky;

    databasedoctruyen databasedoctruyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandangnhap);
        
        
        AnhXa();

        databasedoctruyen = new databasedoctruyen(this);

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Mandangnhap.this, ManDangKy.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentaikhoan =  edtTaiKhoan.getText().toString();
                String matkhau = edtMatkhau.getText().toString();

                Cursor cursor = databasedoctruyen.getData();

                while (cursor.moveToNext()){
                    String datatentaikhoan = cursor.getString(1);
                    String datamatkhau = cursor.getString(2);

                    if(datatentaikhoan.equals(tentaikhoan)&&datamatkhau.equals(matkhau)){
                        int phanquyen = cursor.getInt(4);
                        int idd = cursor.getInt(0);
                        String email = cursor.getString(3);
                        String tentk = cursor.getString(1);

                        Intent intent = new Intent(Mandangnhap.this, MainActivity.class);

                        intent.putExtra("phanq",phanquyen);
                        intent.putExtra("idd",idd);
                        intent.putExtra("email",email);
                        intent.putExtra("tentaikhoan",tentk);

                        startActivity(intent);
                    }
                }

                cursor.moveToFirst();
                cursor.close();

            }
        });
    }

    private void AnhXa() {
        edtMatkhau =  findViewById(R.id.matkhau);
        edtTaiKhoan = findViewById(R.id.taikhoan);
        btnDangky = findViewById(R.id.dangky);
        btnDangNhap = findViewById(R.id.dangnhap);
    }
}