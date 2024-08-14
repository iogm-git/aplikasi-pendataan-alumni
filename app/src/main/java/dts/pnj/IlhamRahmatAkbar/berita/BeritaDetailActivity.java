package dts.pnj.IlhamRahmatAkbar.berita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import dts.pnj.IlhamRahmatAkbar.Helper;
import dts.pnj.IlhamRahmatAkbar.R;
import dts.pnj.IlhamRahmatAkbar.alumni.AlumniDataActivity;
import dts.pnj.IlhamRahmatAkbar.alumni.AlumniTambahDataActivity;

public class BeritaDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_detail);

        // Mengatur Toolbar dengan judul "Detail" dan ikon kembali
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Detail");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);

        // Menangani klik pada ikon kembali untuk menutup activity ini
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        // Mengambil data dari Intent yang dikirim dari activity sebelumnya
        int image = getIntent().getIntExtra("berita.image", -1);
        String judul = getIntent().getStringExtra("berita.judul");
        String konten = getIntent().getStringExtra("berita.konten");

        // Mengatur tampilan gambar, judul, dan konten berita di layout
        ImageView detailImage = findViewById(R.id.detail_image);
        TextView detailJudul = findViewById(R.id.detail_judul);
        TextView detailKonten = findViewById(R.id.detail_konten);

        if (image != -1) { // Jika ada gambar yang disediakan, atur gambar tersebut
            detailImage.setImageResource(image);
        }
        detailJudul.setText(judul); // Menampilkan judul berita
        detailKonten.setText(konten); // Menampilkan konten berita
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Meng-inflate menu; menambahkan item ke action bar jika ada.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Menangani klik item pada action bar.
        int itemId = item.getItemId();

        if (itemId == R.id.action_tambah_data) {
            // Membuka activity untuk menambah data alumni
            startActivity(new Intent(BeritaDetailActivity.this, AlumniTambahDataActivity.class));
        } else if (itemId == R.id.action_data_alumni) {
            // Membuka activity untuk melihat data alumni
            startActivity(new Intent(BeritaDetailActivity.this, AlumniDataActivity.class));
        } else {
            // Melakukan logout menggunakan Helper method
            Helper.performLogout(BeritaDetailActivity.this);
        }

        return true;
    }
}
