package com.example.appdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appdoctruyen.R;
import com.example.appdoctruyen.model.taiKhoan;

import java.util.List;

public class adapterthongtin extends BaseAdapter {

    private Context context;
    private int layout;

    private List<taiKhoan> taiKhoanList;

    public adapterthongtin(Context context, int layout, List<taiKhoan> taiKhoanList) {
        this.context = context;
        this.layout = layout;
        this.taiKhoanList = taiKhoanList;
    }

    @Override
    public int getCount() {
        return taiKhoanList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        converView = inflater.inflate(layout, null);

        TextView txtTenTaiKhoan = (TextView) converView.findViewById(R.id.TEXTNAME);
        TextView txtEmail = (TextView) converView.findViewById(R.id.TextGmail);

        taiKhoan taiKhoan = taiKhoanList.get(position);

        txtTenTaiKhoan.setText(taiKhoan.getmTenTaiKhoan());
        txtEmail.setText(taiKhoan.getmEmail());

        return converView;
    }
}
