package cf.ericrocha.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

public class Home extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    TextView txnome, txemail;
    ImageView img;
    Bitmap bm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent it = getIntent();
        String Email = it.getStringExtra("email");
        String Nome = it.getStringExtra("nome");
        String image = it.getStringExtra("uri");

        txnome = findViewById(R.id.tx_nome);
        txemail = findViewById(R.id.tx_email);
        img = findViewById(R.id.pic);
        if(Nome != null){
           txnome.setText(Nome);
        }
        if(image != null){
            Glide.with(this).load(image).into(img);
        }


        txemail.setText(Email);
    }

    public void logout(View v){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        Intent intToMain = new Intent(this, MainActivity.class);
        startActivity(intToMain);
    }

    @Override
    public void onBackPressed(){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        Intent intToMain = new Intent(this, MainActivity.class);
        startActivity(intToMain);
    }




}
