package com.bingerdranch.android.notes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    String[] data = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};

    private ArrayList<Object>list; // тут будут храниться все элементы - папки и текстовые документы

    private GridView gvMain; // поле, где находятся все эелемнты
    private ArrayAdapter<String> adapter; // адаптер для основного gridLayout

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        generateList();
        adjustGridView();
    }

    private void adjustGridView() {
        gvMain.setNumColumns(GridView.AUTO_FIT);
        gvMain.setVerticalSpacing(5);
        gvMain.setHorizontalSpacing(5);
        gvMain.setNumColumns(5);
    }

    public void generateList(){
        /*
        * Для тестирования создадим 5 папок и 5 текстовых заметок
        * */
        ArrayList<String>names = new ArrayList<>(); // тут храняться все имена папок и документов в нашем поле
        ArrayList<Integer>images = new ArrayList<>(); // тут храняться все изображения папок и документов в нашем поле

        for (int i = 0;i<20;i++){ // добавляем для текстирования 20 папок
            int image = R.drawable.folder;
            Folder folder = new Folder("name_folder_" + i,image,null);
            list.add(folder);
            names.add(folder.getName()); // добавляем имена в массив имен
            images.add(folder.getImage()); // добавляем зображения в массив изображений
        }

        for (int i = 0;i<10;i++){
            int image = R.drawable.note;
            Note note = new Note("name_note_" + i,"name_Note_" + i,image);
            list.add(note);
            names.add(note.getName()); // добавляем имена в массив имен
            images.add(note.getImage()); // добавляем зображения в массив изображений
        }

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(list.size());
        Map<String, Object> m;

        for (int i = 0; i < list.size(); i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, names.get(i));
            m.put(ATTRIBUTE_NAME_IMAGE, images.get(i));// тут надо будет вставлять картинку
            data.add(m);
        }

        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE };
        int[] to = { R.id.tvText, R.id.imageObject};

        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        gvMain = (GridView) findViewById(R.id.gvMain);
        gvMain.setAdapter(sAdapter);

        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public static final String LOG_TAG = "MyLogs";

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(LOG_TAG, list.get(position).toString());
                if (list.get(position).toString().contains("Folder")){
                    Intent intent = new Intent(MainActivity.this,FolderContain.class);
                    startActivity(intent);
                }else if(list.get(position).toString().contains("Note")){
                    Intent intent = new Intent(MainActivity.this,NoteText.class);
                    startActivity(intent);
                }
            }
        });
    }
}
