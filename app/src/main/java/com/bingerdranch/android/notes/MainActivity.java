package com.bingerdranch.android.notes;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.constraint.solver.SolverVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

import static android.R.attr.data;

public class MainActivity extends Activity {
    private static final String LOG_TAG = "MyLogs";

    private GridView gvMain;
    private SharedPreferences sPref;

    SimpleAdapter adapter;
    Map<String, Object> m;
    ArrayList<Map<String, Object>> data;

    final String SAVED_NAME = "saved_name";
    final String SAVED_NUM = "num";
    final String SAVED_IMAGE = "saved_text";

    private ArrayList<Object>list;

    ArrayList<String>names; // тут храняться все имена папок и документов в нашем поле
    ArrayList<Integer>images; // тут храняться все изображения папок и документов в нашем поле
    ArrayList<Note>containFolder;

    final String ATTRIBUTE_NAME = "name";
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_IMAGE = "image";
    final String CONTAIN = "contain";

    private static final int CM_DELETE_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        names = new ArrayList<>();
        images = new ArrayList<>();
        containFolder = new ArrayList<>();
        gvMain = (GridView)findViewById(R.id.gvMain);
        gvMain.setNumColumns(4);
        gvMain.setColumnWidth(80);
        gvMain.setVerticalSpacing(5);
        gvMain.setHorizontalSpacing(5);
        gvMain.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d(LOG_TAG, position+"");
                click(position);
            }
        });
        registerForContextMenu(gvMain);
        test();
        generateArapter();
    }

    private void click(int position) {
        //Log.d(LOG_TAG, list.get(position).getClass().getSimpleName()); // Note, Folder
        if (list.get(position).getClass().getSimpleName().equals(Note.class.getSimpleName())){
            Log.d(LOG_TAG, list.get(position).getClass().getSimpleName());
        }else if(list.get(position).getClass().getSimpleName().equals(Folder.class.getSimpleName())){
            Log.d(LOG_TAG, list.get(position).getClass().getSimpleName());
        }
    }

    void test(){
        for (int i = 0; i<5;i++){
            Note note = new Note("name","text");
            images.add(note.getImageNote());
            names.add(note.getName());
            list.add(note);
        }
        for (int i = 0; i<5;i++){
            Folder folder = new Folder("nameFolder",null);
            images.add(folder.getImageFolder());
            names.add(folder.getName());
            list.add(folder);
        }
    }

    void generateArapter(){
        data = new ArrayList<Map<String, Object>>(list.size());
        for (int i = 0; i < list.size(); i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME, names.get(i));
            m.put(ATTRIBUTE_NAME_IMAGE, images.get(i));
            data.add(m);
        }
        String[] from = { ATTRIBUTE_NAME, ATTRIBUTE_NAME_IMAGE };
        int[] to = { R.id.textView, R.id.imageView };
        adapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        gvMain.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        sPref = getPreferences(MODE_PRIVATE);

        names = new ArrayList<String>(sPref.getStringSet(SAVED_NAME, null));
        ArrayList<String> listInt = new ArrayList<String>(sPref.getStringSet(SAVED_IMAGE,null));

        for (int i = 0; i<listInt.size();i++){
            int k = Integer.parseInt(listInt.get(i));
            images.add(k);
        }
        Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();

        ed.putStringSet(SAVED_NAME, new HashSet<>(names));

        HashSet<String> strs = new HashSet<String>(images.size());
        for(Integer integer : images){
            strs.add(integer.toString());
        }
        ed.putStringSet(SAVED_IMAGE, new HashSet<String>(strs));

        ed.commit();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //Log.d(LOG_TAG, v.toString());
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        list.remove(info.position);
        adapter.notifyDataSetChanged();
        //Log.d(LOG_TAG, "delete - " + acmi.position);
        return true;
    }
}