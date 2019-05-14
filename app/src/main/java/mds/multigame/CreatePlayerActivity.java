package mds.multigame;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import mds.multigame.manager.PlayerManager;
import mds.multigame.model.Player;
import mds.multigame.utils.ActivityUtils;

public class CreatePlayerActivity extends AppCompatActivity {

    private static final int REQUEST_PICK_PICTURE = 1;
    private static final int REQUEST_LOCATION = 1;


    private EditText input_name;
    private EditText input_prenom;
    private EditText input_age;
    private EditText input_ville;
    private ImageView image;
    private Button btn_valider;
    private Button btn_joueur;
    private ImageView localisation;

    private String imageUri;
    private String localisationData;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_PICTURE && resultCode == RESULT_OK) {
            imageUri = data.getData().getPath();
            Picasso.get().load(data.getData()).into(image);

        }
    }

    @SuppressLint("MissingPermission")
    private void getUserLocation() {
        final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationManager.requestSingleUpdate(new Criteria(), new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                localisationData = (location.getLatitude() + " : " + location.getLongitude());
                input_ville.setText(localisationData);
                locationManager.removeUpdates(this);

                Toast.makeText(CreatePlayerActivity.this, "erreur", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        }, null);
    }

    private void getAllPlayers() {
        Realm mRealmInstance = Realm.getDefaultInstance();
        RealmQuery query = mRealmInstance.where(Player.class);
        ArrayList<Player> players = new ArrayList<Player>(query.findAll());
        String a = "";
    }

    private void addUserDbb(Player player) {
        Realm mRealmInstance = Realm.getDefaultInstance();
        mRealmInstance.beginTransaction();
        try {
            mRealmInstance.copyToRealmOrUpdate(player);
            mRealmInstance.commitTransaction();
        }
        catch (Exception e)
        {
            String a = "";
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION) {
            if (ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                getUserLocation();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);

        localisation = findViewById(R.id.iconlocalisation);
        image = findViewById(R.id.img);
        input_name = findViewById(R.id.input_name);
        input_prenom = findViewById(R.id.input_prenom);
        input_age = findViewById(R.id.input_age);
        input_ville = findViewById(R.id.input_ville);
        btn_valider = findViewById(R.id.btn_valider);
        btn_joueur = findViewById(R.id.btn_joueur);

        for (int i = 0; i <= 5; i++)
        {
            addUserDbb(new Player("Spencer" + i , "Toby", "annecy", "img"  ,+ 27,0,0,0,0));
        }
        getAllPlayers();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(Intent.createChooser(intent, "Choix de la photo"), REQUEST_PICK_PICTURE);
            }
        });

        localisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CreatePlayerActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                } else {

                    getUserLocation();
                }
            }
        });

        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_age != null && input_name != null && input_prenom != null && localisationData != null && imageUri != null && input_ville != null) {
                    int year = Integer.parseInt(input_age.getText().toString());

                    int scoreDnD = 0;
                    int scoreFTG =0;
                    int scoreIpac =0;
                    int scoreSwipe =0;
                    Player Joueur = new Player(input_name.getText().toString(), imageUri, input_prenom.getText().toString(), localisationData, year,scoreDnD,scoreFTG,scoreIpac,scoreSwipe);
                    addUserDbb(Joueur);
                    ActivityUtils.launchActivity(CreatePlayerActivity.this, MainActivity.class, true,1);
                    PlayerManager.getInstance().setPlayer(Joueur);

                } else {
                    Toast.makeText(CreatePlayerActivity.this, "Information manquante", Toast.LENGTH_LONG).show();

                }
            }
        });


        btn_joueur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.launchActivity(CreatePlayerActivity.this, ShowPlayerActivity.class, false, ActivityUtils.SLIDE_RIGHT);
            }
        });

    }
}
