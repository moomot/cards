package com.appsolutions.cards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import view.CustomListAdapter;


public class MainActivity extends ActionBarActivity {
    private ListView navList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        __init();
    }

    private void __init() {
        this.navList = (ListView) findViewById(R.id.navListView);
        setAdapter();
    }

    private void setAdapter() {
        String[] array = getResources().getStringArray(R.array.navigation);
        ArrayAdapter adapter = new CustomListAdapter(this, array);
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(onItemClickListener());
        navList.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    return true; // Indicates that this has been handled by you and will not be forwarded further.
                }
                return false;
            }
        });
    }

    private AdapterView.OnItemClickListener onItemClickListener() {
        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        Intent newGame = new Intent(getApplicationContext(), CreateGameActivity.class);
                        startActivity(newGame);
                        break;
                    }
                    case 1: {
                        //HighScores
                        break;
                    }
                    case 2: {
                        //Settings
                        break;
                    }
                }
            }
        };
        return clickListener;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //return true;
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
