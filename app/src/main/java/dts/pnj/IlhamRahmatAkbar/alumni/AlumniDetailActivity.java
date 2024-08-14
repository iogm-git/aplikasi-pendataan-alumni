package dts.pnj.IlhamRahmatAkbar.alumni;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import dts.pnj.IlhamRahmatAkbar.Helper;
import dts.pnj.IlhamRahmatAkbar.R;

public class AlumniDetailActivity extends AppCompatActivity {

    // Deklarasi variabel untuk menampilkan detail alumni dan tombol aksi
    private TextView textNim, textNama, textTempatLahir, textTanggalLahir, textAlamat, textAgama, textNomorHp, textTahunMasuk, textTahunLulus, textPekerjaan, textJabatan;
    private Button buttonUbah, buttonHapus;
    private Helper.DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_detail);

        // Mengatur toolbar di bagian atas layar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Detail Alumni");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);

        // Menangani klik pada ikon kembali di toolbar
        toolbar.setNavigationOnClickListener(v -> finish()); // Menutup aktivitas saat ikon kembali diklik

        // Inisialisasi database helper untuk mengakses data
        dbHelper = new Helper.DatabaseHelper(this);

        // Menghubungkan variabel dengan komponen UI di layout
        textNim = findViewById(R.id.text_nim);
        textNama = findViewById(R.id.text_nama);
        textTempatLahir = findViewById(R.id.text_tempat_lahir);
        textTanggalLahir = findViewById(R.id.text_tanggal_lahir);
        textAlamat = findViewById(R.id.text_alamat);
        textAgama = findViewById(R.id.text_agama);
        textNomorHp = findViewById(R.id.text_nomor_hp);
        textTahunMasuk = findViewById(R.id.text_tahun_masuk);
        textTahunLulus = findViewById(R.id.text_tahun_lulus);
        textPekerjaan = findViewById(R.id.text_pekerjaan);
        textJabatan = findViewById(R.id.text_jabatan);

        // Inisialisasi tombol aksi
        buttonHapus = findViewById(R.id.button_hapus);
        buttonUbah = findViewById(R.id.button_ubah);

        // Mendapatkan NIM alumni dari intent yang dikirim dari aktivitas sebelumnya
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        // Menangani klik pada tombol hapus
        buttonHapus.setOnClickListener(view -> new AlertDialog.Builder(AlumniDetailActivity.this)
                .setTitle("Konfirmasi Hapus") // Judul dialog konfirmasi
                .setMessage("Hapus data alumni dengan NIM = " + id + "?") // Pesan konfirmasi
                .setIcon(android.R.drawable.ic_dialog_alert) // Ikon peringatan
                .setPositiveButton("Ya", (dialog, whichButton) -> {
                    // Menghapus data alumni dari database
                    dbHelper.getWritableDatabase().delete("alumni", "nim = ?", new String[]{id});
                    // Membuka kembali aktivitas AlumniDataActivity setelah penghapusan
                    startActivity(new Intent(AlumniDetailActivity.this, AlumniDataActivity.class));
                    finish();
                })
                .setNegativeButton("Tidak", null).show()); // Tombol untuk membatalkan penghapusan

        // Menangani klik pada tombol ubah
        buttonUbah.setOnClickListener(view -> {
            // Membuka aktivitas untuk mengubah data alumni
            Intent toUbahData = new Intent(AlumniDetailActivity.this, AlumniUbahDataActivity.class);
            toUbahData.putExtra("id", id); // Mengirimkan NIM alumni sebagai extra
            startActivity(toUbahData); // Memulai aktivitas untuk mengubah data alumni
            finish(); // Menutup aktivitas saat ini
        });

        // Memuat data alumni berdasarkan NIM yang diterima
        reloadData(id);
    }

    // Fungsi untuk memuat data alumni dari database dan menampilkannya di UI
    private void reloadData(String id) {
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                "SELECT * FROM alumni WHERE nim = ?",
                new String[]{id}
        );

        // Jika cursor berisi data, tampilkan di TextView masing-masing
        if (cursor != null && cursor.moveToFirst()) {
            textNim.setText(cursor.getString(cursor.getColumnIndexOrThrow("nim")));
            textNama.setText(cursor.getString(cursor.getColumnIndexOrThrow("nama_alumni")));
            textTempatLahir.setText(cursor.getString(cursor.getColumnIndexOrThrow("tempat_lahir")));
            textTanggalLahir.setText(cursor.getString(cursor.getColumnIndexOrThrow("tanggal_lahir")));
            textAlamat.setText(cursor.getString(cursor.getColumnIndexOrThrow("alamat")));
            textAgama.setText(cursor.getString(cursor.getColumnIndexOrThrow("agama")));
            textNomorHp.setText(cursor.getString(cursor.getColumnIndexOrThrow("nomor_hp")));
            textTahunMasuk.setText(cursor.getString(cursor.getColumnIndexOrThrow("tahun_masuk")));
            textTahunLulus.setText(cursor.getString(cursor.getColumnIndexOrThrow("tahun_lulus")));
            textPekerjaan.setText(cursor.getString(cursor.getColumnIndexOrThrow("pekerjaan")));
            textJabatan.setText(cursor.getString(cursor.getColumnIndexOrThrow("jabatan")));
        }

        // Tutup cursor setelah selesai digunakan
        if (cursor != null) {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Menampilkan menu di toolbar
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Menangani klik pada item menu di toolbar
        int itemId = item.getItemId();

        if (itemId == R.id.action_tambah_data) {
            // Membuka aktivitas untuk menambah data alumni
            startActivity(new Intent(AlumniDetailActivity.this, AlumniTambahDataActivity.class));
        } else if (itemId == R.id.action_data_alumni) {
            // Membuka kembali aktivitas data alumni
            startActivity(new Intent(AlumniDetailActivity.this, AlumniDataActivity.class));
        } else {
            // Melakukan logout dan menghapus status login
            Helper.performLogout(AlumniDetailActivity.this);
        }

        return true;
    }
}
