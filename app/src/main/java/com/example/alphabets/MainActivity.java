package com.example.alphabets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements listFrag.Itemselected {

    ImageView image;
    TextView title, intro;
    Button website, sound;
    String [] descriptions;
    String [] title_description;
    ArrayList<Integer> img = new ArrayList<>();
    String [] wiki;
    ArrayList<Integer> songlist = new ArrayList<>();
    MediaPlayer mediaPlayer;
    AdView ad;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this);

        intro = findViewById(R.id.intro);
        website = findViewById(R.id.website);
        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        sound = findViewById(R.id.sound);


        Field[] fields=R.raw.class.getFields();
        for (Field field : fields) {
            int resourceID = 0;
            try {
                resourceID = field.getInt(field);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            songlist.add(resourceID);
        }

        //TITLE
        title_description = getResources().getStringArray(R.array.title);

        //Introduction
        descriptions = getResources().getStringArray(R.array.introduction);

        //Wiki
        wiki = getResources().getStringArray(R.array.wikipedia);

        //image
        img.add(R.drawable.apple);
        img.add(R.drawable.banana);
        img.add(R.drawable.cat);
        img.add(R.drawable.dog);
        img.add(R.drawable.elephant);
        img.add(R.drawable.fish);
        img.add(R.drawable.goat);
        img.add(R.drawable.horse);
        img.add(R.drawable.ice_cream);
        img.add(R.drawable.jackal);
        img.add(R.drawable.koala);
        img.add(R.drawable.lion);
        img.add(R.drawable.mango);
        img.add(R.drawable.nest);
        img.add(R.drawable.orange);
        img.add(R.drawable.peacock);
        img.add(R.drawable.queen);
        img.add(R.drawable.rat);
        img.add(R.drawable.strawberry);
        img.add(R.drawable.tomato);
        img.add(R.drawable.umbrella);
        img.add(R.drawable.violin);
        img.add(R.drawable.watermelon);
        img.add(R.drawable.xylophone);
        img.add(R.drawable.yak);
        img.add(R.drawable.zebra);

        //phone is in portrait mode
        if (findViewById(R.id.layout_portrait) != null) {
            FragmentManager manager = this.getSupportFragmentManager();

            manager.beginTransaction()
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.detailsFrag)))
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.listFrag)))
                    .commit();
        }

        //phone is in landscape mode
        if (findViewById(R.id.layout_land) != null){
            FragmentManager manager = this.getSupportFragmentManager();

            manager.beginTransaction()
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.detailsFrag)))
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.listFrag)))
                    .commit();
        }
    }

    @Override
    public void onItemSelected(final int index) {
        mediaPlayer = MediaPlayer.create(this, songlist.get(index));
        intro.setText(descriptions[index]);
        image.setImageResource(img.get(index));
        title.setText(title_description[index]);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(wiki[index]));
                startActivity(intent);
            }
        });

        //phone is in portrait mode
        if (findViewById(R.id.layout_portrait) != null) {
            FragmentManager manager = this.getSupportFragmentManager();

            manager.beginTransaction()
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.detailsFrag)))
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.listFrag)))
                    .addToBackStack(null)
                    .commit();
        }
    }
}