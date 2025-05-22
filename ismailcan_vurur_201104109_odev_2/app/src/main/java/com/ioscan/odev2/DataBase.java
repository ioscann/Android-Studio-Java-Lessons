package com.ioscan.odev2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {

    private static final String TABLE_NAME="kullanicilar";
    private static final String DATABASE_NAME="odev3";
    private static final int DATABASE_VERSION=1;
    private static  String KULLANICI_AD="ad";
    private static  String KULLANICI_SOYAD="soyad";
    private static  String KULLANICI_KADI="kadi";
    private static  String KULLANICI_SIFRE="sifre";

    private static String KULLANICI_CINSIYET="cinsiyet";

    public DataBase(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION); };
    SQLiteDatabase sqLiteDatabase;

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE kullanicilar ('id' INTEGER PRIMARY KEY AUTOINCREMENT ,'ad' TEXT, 'soyad' TEXT,'cinsiyet' TEXT ,'kadi' TEXT,'sifre' TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE İF EXISTS "+TABLE_NAME);
        this.onCreate(db);
    }
    public void veriSil(String kadi){
        SQLiteDatabase db=this.getWritableDatabase();//SQLiteDatabase sınıfında yazılabilir bağlantı açıyoruz.
        db.delete(TABLE_NAME,KULLANICI_AD + "=?",new String[]{String.valueOf(kadi)});//personel adı girilerek veri silme işlemi yapıyoruz.
        db.close();//veritabanı kapatma işlemi
    }
    public void veriEkle(String ad,String soyad,String cinsiyet,String kadi,String sifre)
    {
        SQLiteDatabase db=this.getWritableDatabase();//SQLiteDatabase sınıfında yazılabilir bağlantı açıyoruz.
        ContentValues cv=new ContentValues();//contentValues sınıfından bir nesne oluşturuyoruz.ContentValues değerleri tutmamızı sağlıyor.

        cv.put(KULLANICI_AD,ad.trim());//Ad alıyoruz.
        cv.put(KULLANICI_SOYAD,soyad.trim());//Soyad alıyoruz.
        cv.put(KULLANICI_CINSIYET,cinsiyet.trim()); // cinsiyeti alıyoruz
        cv.put(KULLANICI_KADI,kadi.trim()); //kullanıcı adı alyoruz
        cv.put(KULLANICI_SIFRE,sifre.trim()); // Şifre alıyoruz

        long r=db.insert(TABLE_NAME,null,cv);//Tabloya ekleme yaptığımız fonksiyon.
        if(r>-1)
            Log.i("tag","İşlem Başarılı");//ekleme olması durumunda bu çıktıyı verir.
        else
            Log.e("tag","İşlem Başarısız");//ekleme olmaması durumunda bu çıktıyı verir.

        MainActivity3.control = r;
        db.close();//Veritabanı kapatma işlemi
    }

    public void veriBul(String kadi,String sifre) {
        String sorgu = "SELECT * FROM " + TABLE_NAME + " WHERE kadi=" + "'" +kadi + "'" + " AND sifre = " + "'" +sifre + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sorgu, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            MainActivity2.kontrol = true;
        }
        cursor.close();
        db.close();
    }

    @SuppressLint("Range")
    public List<String> veriListele(String ad)
    {
        List<String> veriler=new ArrayList<String>();//String türünde bir liste oluşturduk.
        SQLiteDatabase db=this.getWritableDatabase();//SQLiteDatabase sınıfında yazılabilir bağlantı açıyoruz.
        String[] sutunlar={KULLANICI_AD,KULLANICI_SOYAD,KULLANICI_CINSIYET,KULLANICI_KADI,KULLANICI_SIFRE};

        String sorgu = "SELECT * FROM kullanicilar where ad = " + "'" + ad + "'";
        Cursor cr = db.rawQuery(sorgu,null);

        //Cursor cr=db.query(TABLE_NAME,sutunlar,"WHERE",null ,null,null,null);//query fonksiyonu ile aldığımız parametreler yoluyla komutu kendi içerisinde yapılandırıyoruz.
        while(cr.moveToNext())
        {
            veriler.add(cr.getString(cr.getColumnIndex("ad"))+"-"+cr.getString(cr.getColumnIndex("soyad"))+"-"+cr.getString(cr.getColumnIndex("cinsiyet"))+"-"+cr.getString(cr.getColumnIndex("kadi"))); //sırasıyla verileri listelememizi sağlıyor.
        }
        return veriler;
    }
}
