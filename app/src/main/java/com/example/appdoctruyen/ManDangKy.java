package com.example.appdoctruyen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appdoctruyen.database.databasedoctruyen;
import com.example.appdoctruyen.model.taiKhoan;

public class ManDangKy extends AppCompatActivity {
    EditText edtDKTaiKhoan, edtDKMatkhau, edtDKEmail;
    Button btnDKDangky, btnDKDangnhap;

    databasedoctruyen databasedoctruyen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_dang_ky);

        databasedoctruyen = new databasedoctruyen(this);

        AnhXa();

        btnDKDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = edtDKTaiKhoan.getText().toString();
                String matkhau = edtDKMatkhau.getText().toString();
                String email = edtDKEmail.getText().toString();

                taiKhoan taikhoan1 = CreateTaiKhoan();
                if (taikhoan.equals("") || matkhau.equals("") || email.equals("")) {
                    Log.e("Thông báo: ", "Chưa nhập đầy đủ thông tin!");
                } else {
                    databasedoctruyen.AddTaiKhoan(taikhoan1);
                    Toast.makeText(ManDangKy.this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDKDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private taiKhoan CreateTaiKhoan() {
        String taikhoan = edtDKTaiKhoan.getText().toString();
        String matkhau = edtDKMatkhau.getText().toString();
        String email = edtDKEmail.getText().toString();
        int phanquyen = 1;

        taiKhoan tk = new taiKhoan(taikhoan, matkhau, email, phanquyen);
        return tk;

    }


    private void AnhXa() {
        edtDKEmail = findViewById(R.id.dkemail);
        edtDKMatkhau = findViewById(R.id.dkmatkhau);
        edtDKTaiKhoan = findViewById(R.id.dktaikhoan);
        btnDKDangky = findViewById(R.id.dkdangky);
        btnDKDangnhap = findViewById(R.id.dkdangnhap);
    }
}