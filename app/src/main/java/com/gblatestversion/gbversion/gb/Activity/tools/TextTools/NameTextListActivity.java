package com.gblatestversion.gbversion.gb.Activity.tools.TextTools;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityNameTextListBinding;

public class NameTextListActivity extends AppCompatActivity {
    public static String text;
    NameAdapter adapter;
    String[] list;
    int pos;
    String[] split;
    Toolbar toolbarNameSearch;

    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }
ActivityNameTextListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        text = this.getResources().getString(R.string.preview);
        binding=ActivityNameTextListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        WFSAppLoadAds.getInstance().showNativeBottomDynamic(this,binding.frameViewAds);
        binding.tool.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.tool.title.setText("Name Text Effect");


        toolbarNameSearch = findViewById(R.id.toolbarNameSearchId);
        setSupportActionBar(toolbarNameSearch);
        getSupportActionBar().setTitle((CharSequence) getString(R.string.preview));

        new AsynTaskBase().execute(new Void[0]);
    }

    private class AsynTaskBase extends AsyncTask<Void, Void, Void> {
        private AsynTaskBase() {
        }

        public void onPreExecute() {
            super.onPreExecute();
        }

        public Void doInBackground(Void... voidArr) {
            list = getResources().getStringArray(R.array.name_style);
            return null;
        }

        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            adapter = new NameAdapter(NameTextListActivity.this, list);
            RecyclerView recyclerView = findViewById(R.id.nameRecyclerId);
            recyclerView.setLayoutManager(new LinearLayoutManager(NameTextListActivity.this, RecyclerView.VERTICAL, false));
            recyclerView.setAdapter(adapter);
            ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                public void onItemClicked(RecyclerView recyclerView, int i, View view) {
                    pos = i;
                    WFSAppLoadAds.getInstance().showInterstitial(NameTextListActivity.this,()->{
                        CallIntent(NameTextListActivity.this, NameStyleDetailActivity.class, i);
                    });
                }
            });
        }
    }

    public int getItemCount() {
        return this.list.length;
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint(getString(R.string.enter_name));
        searchView.setInputType(1);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                return false;
            }

            public boolean onQueryTextChange(String str) {
                adapter.filter(str);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    public void CallIntent(Context context, Class<?> cls, int i) {
        Intent intent = new Intent(context, cls);
        intent.putExtra("position", i);
        intent.putExtra("text", text);
        intent.putExtra("size", getItemCount());
        context.startActivity(intent);
    }
}