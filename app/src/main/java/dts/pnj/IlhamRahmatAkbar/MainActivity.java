package dts.pnj.IlhamRahmatAkbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import dts.pnj.IlhamRahmatAkbar.berita.BeritaFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = new HomeFragment();

                if (item.getItemId() == R.id.to_fragment_home) {
                    selectedFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.to_fragment_berita) {
                    selectedFragment = new BeritaFragment();
                } else if (item.getItemId() == R.id.to_fragment_profile) {
                    selectedFragment = new ProfileFragment();
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();

                return true;
            }
        });

        // Cek intent untuk menentukan fragment mana yang akan ditampilkan setelah login
        Intent intent = getIntent();
        if (intent != null && "fragment_profile".equals(intent.getStringExtra("redirectTo"))) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new ProfileFragment())
                    .commit();
            bottomNavigationView.setSelectedItemId(R.id.to_fragment_profile);
        } else {
            // Default fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
    }
}