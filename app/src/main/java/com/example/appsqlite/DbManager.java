package com.example.appsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.appsqlite.beans.Contact;

public class DbManager extends SQLiteOpenHelper {

    private static final String dbName="contact.db";
    private static final String TABLENAME="tbl_contact";

    public DbManager(Context context)
    {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String qry="create table tbl_contact (id integer primary key autoincrement,name text,contact text)";
//        String qry="create table tbl_contact (id integer primary key autoincrement,name text,contact text,image blob)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLENAME);
        onCreate(db);
    }

    public void addContacts(Contact c)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        String qry="insert into "+TABLENAME+"(name,contact) values"+"('" +c.getName() +"','"+c.getContact()+"')";
        db.execSQL(qry);
        db.close();

    }

    public  void updateContact(Contact c)
    {
        SQLiteDatabase db= this.getWritableDatabase();

        String qry="update "+TABLENAME+" set name='"+c.getName()+"', contact='"+c.getContact()+"' where id="+c.getId();

//        update tbl_contact set name ='rockkk',number = 'kk' where id= 1
        db.execSQL(qry);
        db.close();
    }

    public void deleteContact(int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String qry="delete from "+TABLENAME+" where id = "+id;
        db.execSQL(qry);
        db.close();
    }

    public Cursor getContact(String contact)
    {
        SQLiteDatabase db=  this.getWritableDatabase();
        String qry="select * from "+TABLENAME+" where contact="+contact;
        Cursor csr=db.rawQuery(qry,null);
        return  csr;
    }

    public Cursor getAllContacts()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String qry="select * from "+TABLENAME+" order by id desc";
        Cursor csr=db.rawQuery(qry,null);
        return  csr;
    }






}
