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
import com.gblatestversion.gbversion.gb.adaptor.NumberStyleAdapter;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.databinding.ActivityNumericListBinding;

public class NumericListActivity extends AppCompatActivity {
    public static String text = "12345";
    NumberStyleAdapter adapter;
    String[] list;
    int pos;
    Toolbar toolbarNumberSearch;
ActivityNumericListBinding binding;
    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
binding=ActivityNumericListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tool.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.tool.title.setText("Number Text Effect");
        WFSAppLoadAds.getInstance().showNativeBottomDynamic(this,binding.frameViewAds);


        toolbarNumberSearch = findViewById(R.id.toolbarNumberSearchId);
        setSupportActionBar(toolbarNumberSearch);
        getSupportActionBar().setTitle((CharSequence) getResources().getString(R.string.preview));

        new AsynTaskBase().execute(new Void[0]);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint(getResources().getString(R.string.enter_number));
        searchView.setInputType(2);
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

    private class AsynTaskBase extends AsyncTask<Void, Void, Void> {
        private AsynTaskBase() {
        }

        public void onPreExecute() {
            super.onPreExecute();
        }

        public Void doInBackground(Void... voidArr) {

            list = getResources().getStringArray(R.array.number_style);
            return null;
        }

        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);

            adapter = new NumberStyleAdapter(NumericListActivity.this, list);
            RecyclerView recyclerView = findViewById(R.id.numberRecyclerId);
            recyclerView.setLayoutManager(new LinearLayoutManager(NumericListActivity.this, RecyclerView.VERTICAL, false));
            recyclerView.setAdapter(adapter);

            ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                public void onItemClicked(RecyclerView recyclerView, int i, View view) {
                    pos = i;
                    text = text.replace(" ", "");
                    WFSAppLoadAds.getInstance().showInterstitial(NumericListActivity.this, () -> {
                        CallIntent(NumericListActivity.this, NumberStyleDetailActivity.class, i, text);
                    });
                }
            });
        }
    }

    public int getItemCount() {
        return this.list.length;
    }

    public void CallIntent(Context context, Class<?> cls, int i, String str) {
        Intent intent = new Intent(context, cls);
        intent.putExtra("position", i);
        intent.putExtra("text", str);
        intent.putExtra("size", getItemCount());
        context.startActivity(intent);
    }

}