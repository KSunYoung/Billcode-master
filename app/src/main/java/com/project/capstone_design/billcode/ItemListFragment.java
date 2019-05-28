package com.project.capstone_design.billcode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.capstone_design.billcode.addItem.AddItem_RecyclerAdapter;
import com.project.capstone_design.billcode.addItem.AddItem_RecyclerItem;
import com.project.capstone_design.billcode.login.LoginActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ItemListFragment extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView mRecyclerView;
    private ArrayList<AddItem_RecyclerItem> mItems = new ArrayList<>();
    private boolean isPushChecked;
    private boolean isTotalPushChecked;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_itemlist);
        mRecyclerView = findViewById(R.id.ItemList_RecyclerViewItemList);
        adapter = new AddItem_RecyclerAdapter(mItems); // 어답터에 아이템 연결

        SharedPreferences mAppData = getSharedPreferences("AppData", MODE_PRIVATE);

        isPushChecked = mAppData.getBoolean("IS_PUSH_CHECKED", false);
        isTotalPushChecked = mAppData.getBoolean("IS_TOTAL_PUSH_CHECKED", false);

        //RecyclerView mRecyclerView = findViewById(R.id.RecyclerViewItemList);
        // setRecyclerView(); // 리사이클러뷰를 어답터에 연결, 여러가지 기본 세팅해준다.

        DBHelper dbHelper = new DBHelper(getApplicationContext(), "DB_TABLE.db", null, 1);
        ArrayList list = dbHelper.getItems();

        for (int i = 0; i < list.size(); i++) {

        }

    }

    private void setRecyclerView() {
// 각 Item 들이 RecyclerView 의 전체 크기를 변경하지 않는 다면
// setHasFixedSize() 함수를 사용해서 성능을 개선할 수 있습니다.
// 변경될 가능성이 있다면 false 로 , 없다면 true를 설정해주세요.
        mRecyclerView.setHasFixedSize(false);


// RecyclerView에 Adapter를 설정해줍니다.

        mRecyclerView.setAdapter(adapter); // 리사이클러뷰에 어답터 연결

// 다양한 LayoutManager 가 있습니다. 원하시는 방법을 선택해주세요.
// 지그재그형의 그리드 형식
//mainBinding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
// 그리드 형식
//mainBinding.recyclerView.setLayoutManager(new GridLayoutManager(this,4));
// 가로 또는 세로 스크롤 목록 형식
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // setData();

    }


    private void setData(String[] listName, String[] listExpDate) {
        mItems.clear();

        if (listName == null || listExpDate == null) {
            this.onRestart();
        }

// RecyclerView 에 들어갈 데이터를 추가합니다.
        for (int i = 0; i < listName.length; i++) {
            mItems.add(new AddItem_RecyclerItem(listName[i], listExpDate[i], listName[i]));
            if(isPushChecked && isTotalPushChecked)
                mItems.get(i).setPushChecked(true);
        }

        // 구코드
        //for (String name,ExpDate : listName,listExpDate) {
        //    mItems.add(new AddItem_RecyclerItem(name, "20190123", name));
        //    //mItems.add(new AddItem_RecyclerItem(name));
        //}
// 데이터 추가가 완료되었으면 notifyDataSetChanged() 메서드를 호출해 데이터 변경 체크를 실행합니다.
        adapter.notifyDataSetChanged();
    }

}
