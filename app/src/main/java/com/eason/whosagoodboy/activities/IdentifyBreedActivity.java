package com.eason.whosagoodboy.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.eason.whosagoodboy.WhosAGoodBoy;
import com.eason.whosagoodboy.whosagoodboy.R;

import java.io.FileInputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eason on 2018-01-15.
 */

public class IdentifyBreedActivity extends AppCompatActivity
{
    @BindView(R.id.user_photo)
    ImageView userImage;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_identify_breed);

	((WhosAGoodBoy) getApplication()).getAppComponent().inject(this);
	ButterKnife.bind(this);

	setUp();
    }

    private void setUp()
    {
	Bitmap bitmap = null;
	String filename = getIntent().getStringExtra("image");
	try {
	    FileInputStream is = this.openFileInput(filename);
	    bitmap = BitmapFactory.decodeStream(is);
	    is.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	userImage.setImageBitmap(bitmap);
    }

}
