package com.gblatestversion.gbversion.gb.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gblatestversion.gbversion.gb.R;
import com.gblatestversion.gbversion.gb.adsw.WFSAppLoadAds;
import com.gblatestversion.gbversion.gb.utils.OnItemClickListener;
import com.gblatestversion.gbversion.gb.adaptor.SubFrameAdapter;
import com.gblatestversion.gbversion.gb.api.RenClient;
import com.gblatestversion.gbversion.gb.databinding.ActivityDpcreatorBinding;
import com.gblatestversion.gbversion.gb.model.DpGen.Emage;
import com.gblatestversion.gbversion.gb.model.DpGen.Example;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DPCreatorActivity extends AppCompatActivity implements OnItemClickListener {
    ActivityDpcreatorBinding binding;
    String frm;
    String imagePath;
    ArrayList<Emage> mList = new ArrayList<>();
    SubFrameAdapter myFrameAdapter;

    private static final int REQUEST_CODE_PICK_IMAGE = 1;
    LinearLayoutManager mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDpcreatorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        frm = getIntent().getStringExtra("Frames");
        binding.progres.setVisibility(View.VISIBLE);
        WFSAppLoadAds.getInstance().showNativeBottomDynamic(DPCreatorActivity.this, binding.frameViewAds);
        Glide.with(this).load(frm).into(binding.imgFrame);
        mLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        binding.recyclerView.setLayoutManager(mLayout);
        getDpFrame();

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Popular"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Text"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Basic"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Badge"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Cute"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Floral"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Gold"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Nature"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Pattern"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Texture"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("AnimalPrint"));

        binding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    binding.recyclerView.smoothScrollToPosition(0);
                } else if (position == 1) {
                    binding.recyclerView.smoothScrollToPosition(48);
                } else if (position == 2) {
                    binding.recyclerView.smoothScrollToPosition(65);
                } else if (position == 3) {
                    binding.recyclerView.smoothScrollToPosition(85);
                } else if (position == 4) {
                    binding.recyclerView.smoothScrollToPosition(100);
                } else if (position == 5) {
                    binding.recyclerView.smoothScrollToPosition(123);
                } else if (position == 6) {
                    binding.recyclerView.smoothScrollToPosition(143);
                } else if (position == 7) {
                    binding.recyclerView.smoothScrollToPosition(165);
                } else if (position == 8) {
                    binding.recyclerView.smoothScrollToPosition(190);
                } else if (position == 9) {
                    binding.recyclerView.smoothScrollToPosition(217);
                } else if (position == 10) {
                    binding.recyclerView.smoothScrollToPosition(238);
                } else {
                    binding.recyclerView.smoothScrollToPosition(0);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        binding.selectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);

            }
        });


        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // create the bitmap here
                Bitmap bitmap = Bitmap.createBitmap(binding.relMain.getWidth(), binding.relMain.getHeight(), Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(bitmap);
                binding.relMain.draw(canvas);
                Random rand = new Random();
                int rand_int2 = rand.nextInt(1000);
                if (binding.imgDP.getDrawable()==null){
                    Toast.makeText(DPCreatorActivity.this, "Please Select The Image", Toast.LENGTH_SHORT).show();
                }else{
                    saveImage(bitmap, String.valueOf(rand_int2));

                }

            }
        });

        binding.icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.title.setText("Dp Creator");

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            imagePath = getRealPathFromUri(imageUri); // Get the path of the image file
//            Uri imgUri = Uri.parse(imagePath);
            binding.progres.setVisibility(View.GONE);
            try (InputStream inputStream = getContentResolver().openInputStream(imageUri)) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                binding.imgDP.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Convert a content URI to a file path
    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(columnIndex);
            cursor.close();
            return path;
        } else {
            return uri.getPath();
        }
    }

    public void getDpFrame() {


        RenClient.getInstance().getApi().getDpGen()
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        try {
                            for (int i = 0; i <= response.body().getData().size() - 1; i++) {
                                mList.addAll(response.body().getData().get(i).getImage());
                            }

                            myFrameAdapter = new SubFrameAdapter(DPCreatorActivity.this, mList, DPCreatorActivity.this::onItemClick);
                            binding.recyclerView.setAdapter(myFrameAdapter);
                        } catch (Exception e) {
                            Log.e("TAG", e.toString());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Log.e("TAG", t.getMessage());

                    }
                });


    }

    public static void addImageToGallery(final String filePath, final Context context) {

        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.DATA, filePath);

        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    public static Bitmap viewToBitmap(View view, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private void saveImage(Bitmap finalBitmap, String image_name) {

        File myDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + "DP");
        myDir.mkdirs();
        String fname = "Image-" + image_name + ".png";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(this, "DP Save In Gallery", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(String item) {
        frm = item;
        Glide.with(this).load(frm).into(binding.imgFrame);

    }

    @Override
    public void onBackPressed() {
        WFSAppLoadAds.getInstance().showInterstitialBack(this, this::finish);
    }

}