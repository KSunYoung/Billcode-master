package com.project.capstone_design.billcode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.project.capstone_design.billcode.addItem.AddItem_RecyclerItem;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    /**
     * Database가 존재하지 않을 때, 딱 한번만 실행
     * DB를 만드는 역할
     */
    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {

        // String 보다 StringBuffer가 Query 만들기 편하다.
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE DB_TABLE ( ");
        sb.append(" P_NUM INTEGER PRIMARY KEY, ");
        sb.append(" P_NAME STRING, ");
        sb.append(" P_EXPDATE STRING ) ");

        // SQLite Database로 쿼리 실행
        db.execSQL(sb.toString());

        //db.execSQL("CREATE TABLE DB_TABLE2 (P_NUM INTEGER PRIMARY KEY, P_NAME TEXT, P_EXPDATE INTEGER);");

        Toast.makeText(context, "내부db Table 생성완료", Toast.LENGTH_SHORT).show();
    }


    // DB 업그레이드를 위해 버전이 변경될 때 호출
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DB_TABLE");

        //새로 생성될 수 있도록 onCreate() 메소드를 생성.
        onCreate(db);

    }


    public void insert(ArrayList<AddItem_RecyclerItem> list) {
        // 읽고 쓰기가 가능하게 DB 열기
        //SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가

        // db.execSQL("INSERT INTO DB_TABLE VALUES(null, '" + P_NUM + "', " + P_NAME + ", '" + P_BLOB + "', '" + P_EXPDATE + "');");


        //db.close();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for(AddItem_RecyclerItem entity : list){
            values.put("P_NUM",entity.getP_NUM());
            values.put("P_NAME",entity.getP_NAME());
            values.put("P_EXPDATE",entity.getP_EXPDATE());
            db.insert("DB_TABLE",null,values);

        }

        db.close();
    }

    public ArrayList<AddItem_RecyclerItem> getItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery("SELECT P_NAME,P_NUM,P_EXPDATE FROM DB_TABLE", null);
        ArrayList<AddItem_RecyclerItem> list = new ArrayList<>();
        while(cursor.moveToNext()){
            AddItem_RecyclerItem entity = new AddItem_RecyclerItem();
            entity.setP_NUM(cursor.getInt(0));
            entity.setP_NAME(cursor.getString(1));
            entity.setP_EXPDATE(cursor.getString(2));
            list.add(entity);
        }
        return list;
    }

    public void update(int P_NUM, String P_NAME, String P_EXPDATE) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 이름 정보 수정
        db.execSQL("UPDATE DB_TABLE SET P_NAME=" + P_NAME + " WHERE P_NUM='" + P_NUM + "';");
        db.close();
    }

    public void delete(int P_NUM, String P_NAME, int P_EXPDATE) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM DB_TABLE WHERE P_NUM='" + P_NUM + "';");
        db.close();
    }

    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM DB_TABLE", null);
        while (cursor.moveToNext()) {
            result +=  "상품번호 : "
                    + cursor.getInt(0)
                    + "상품이름 : "
                    + cursor.getString(1)
                    + "유통기한 : "
                    + cursor.getString(2)
                    + "\n";
        }

        return result;
    }
    public void testDB() {
        SQLiteDatabase db = getReadableDatabase();
    }
}