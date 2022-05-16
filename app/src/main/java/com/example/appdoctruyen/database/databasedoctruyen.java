package com.example.appdoctruyen.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.appdoctruyen.model.Truyen;
import com.example.appdoctruyen.model.taiKhoan;

public class databasedoctruyen extends SQLiteOpenHelper {

// database name
    private static String DATABASE_NAME = "doctruyen";
    //biến bảng tài khoản
    private static String TABLE_TAIKHOAN = "taikhoan";
    private static String ID_TAI_KHOAN = "idtaikhoan";
    private static String TEN_TAI_KHOAN = "tentaikhoan";
    private static String MAT_KHAU = "matkhau";
    private static String PHAN_QUYEN = "phanquyen";
    private static String EMAIL = "email";
    private static int VERSION = 1;

    //biến bảng truyện
    private static String TABLE_TRUYEN = "truyen";
    private static String ID_TRUYEN = "idtruyen";
    private static String TEN_TRUYEN = "tieude";
    private static String NOI_DUNG = "noidung";
    private static String IMAGE = "anh";


    private Context context;
//biến lưu câu lệnh bằng tạo tài khoản
    private String SQLQuery = "CREATE TABLE "+ TABLE_TAIKHOAN +" ( "+ID_TAI_KHOAN+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TEN_TAI_KHOAN+" TEXT UNIQUE, "
            +MAT_KHAU+" TEXT, "
            +EMAIL+" TEXT, "
            + PHAN_QUYEN+" INTEGER) ";
//biến lưu câu lệnh bằng tạo bảng truyện
    private String SQLQuery1 = "CREATE TABLE "+ TABLE_TRUYEN +" ( "+ID_TRUYEN+" integer primary key AUTOINCREMENT, "
            +TEN_TRUYEN+" TEXT UNIQUE, "
            +NOI_DUNG+" TEXT, "
            +IMAGE+" TEXT, "+ID_TAI_KHOAN+" INTEGER , FOREIGN KEY ( "+ ID_TAI_KHOAN +" ) REFERENCES "+
            TABLE_TAIKHOAN+"("+ID_TAI_KHOAN+"))";
//Insert dữ liệu vào bảng tài khoản
    //phân quyền : 1. admin, 2. người dùng
    private String SQLQuery2 = "INSERT INTO TaiKhoan VAlUES (null,'admin','admin','admin@gmail.com',2)";
    private String SQLQuery3 = "INSERT INTO TaiKhoan VAlUES (null,'huongphan','huong','huongphan@gmail.com',1)";
//Insert truyện

//tạo bảng tại phương thức này
    public databasedoctruyen(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//Thực hiện các câu lệnh truy vấn không trả về kết quả
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
        db.execSQL(SQLQuery2);
        db.execSQL(SQLQuery3);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //Phương thức lấy tất cả tài khoản
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery(" SELECT * FROM "+TABLE_TAIKHOAN, null);
        return res;
    }

    public void AddTaiKhoan(taiKhoan taikhoan){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TEN_TAI_KHOAN, taikhoan.getmTenTaiKhoan());
        values.put(MAT_KHAU, taikhoan.getmTenMatKhau());
        values.put(EMAIL, taikhoan.getmEmail());
        values.put(PHAN_QUYEN, taikhoan.getmPhanQuyen());

        db.insert(TABLE_TAIKHOAN, null, values);

        db.close();
        Log.e( "AddTaiKhoan: ", "TC" );
    }

    public Cursor getData1(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_TRUYEN+" ORDER BY "+ ID_TRUYEN + " DESC LIMIT 3 ", null);
        return res;
    }

    public Cursor getData2(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery(" SELECT * FROM " + TABLE_TRUYEN, null);
        return res;
    }

    public void Addtruyen(Truyen truyen){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TEN_TRUYEN, truyen.getTenTruyen());
        values.put(NOI_DUNG, truyen.getNoiDung());
        values.put(IMAGE, truyen.getAnh());
        values.put(ID_TAI_KHOAN, truyen.getID_TK());

        db.insert(TABLE_TRUYEN, null, values);
        db.close();
    }

    public int Delete(int i){
        SQLiteDatabase db = this.getReadableDatabase();
        int res = db.delete(TABLE_TRUYEN, ID_TRUYEN+"="+i, null);
        return res;
    }
}
