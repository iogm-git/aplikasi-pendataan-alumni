package dts.pnj.IlhamRahmatAkbar.alumni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import dts.pnj.IlhamRahmatAkbar.Helper;
import dts.pnj.IlhamRahmatAkbar.R;

public class AlumniDataActivity extends AppCompatActivity {

    private ListView listView;
    private Helper.DatabaseHelper dbHelper;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_data);

        // Mengatur toolbar di bagian atas layar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Data Alumni");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);

        // Menangani klik pada ikon kembali di toolbar
        toolbar.setNavigationOnClickListener(v -> {
            finish(); // Menutup aktivitas saat ikon kembali diklik
        });

        // Inisialisasi database helper untuk mengakses data
        dbHelper = new Helper.DatabaseHelper(this);

        // Mengatur adapter untuk ListView, menggunakan layout standar Android
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                null,
                new String[]{"_id", "nama_alumni"}, // Kolom yang akan ditampilkan
                new int[]{android.R.id.text1, android.R.id.text2}, // ID dari text view di layout
                0);

        // Menghubungkan ListView dengan adapter
        listView = findViewById(R.id.data_alumni);
        listView.setAdapter(adapter);

        // Menangani klik item di ListView
        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            showDetail(position); // Menampilkan detail alumni berdasarkan posisi yang diklik
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData(); // Memperbarui data saat aktivitas kembali aktif
    }

    // Fungsi untuk memperbarui data di ListView dari database
    private void refreshData() {
        Cursor cursor = dbHelper.getReadableDatabase()
                .rawQuery("SELECT " +
                        "nim AS _id, " +
                        "nama_alumni, " +
                        "tempat_lahir, " +
                        "tanggal_lahir," +
                        "alamat, " +
                        "agama, " +
                        "nomor_hp, " +
                        "tahun_masuk, " +
                        "tahun_lulus, " +
                        "pekerjaan, " +
                        "jabatan " +
                        "FROM alumni;", null);
        adapter.changeCursor(cursor); // Mengganti cursor pada adapter dengan data yang baru
    }

    // Menampilkan detail alumni berdasarkan posisi yang dipilih di ListView
    private void showDetail(int position) {
        Cursor cursor = (Cursor) adapter.getItem(position); // Mendapatkan item berdasarkan posisi
        if (cursor != null) {
            String nim = cursor.getString(cursor.getColumnIndexOrThrow("_id")); // Mendapatkan NIM dari kolom _id

            // Membuka AlumniDetailActivity dengan NIM yang dipilih
            Intent intent = new Intent(AlumniDataActivity.this, AlumniDetailActivity.class);
            intent.putExtra("id", nim); // Mengirim NIM sebagai extra
            startActivity(intent); // Memulai aktivitas detail alumni
            finish(); // Menutup aktivitas saat ini
        } else {
            Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show(); // Menampilkan pesan jika data tidak ditemukan
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

        if (itemId == R.id.action_tambah_data){
            startActivity(new Intent(AlumniDataActivity.this, AlumniTambahDataActivity.class)); // Membuka aktivitas untuk menambah data alumni
        } else if(itemId == R.id.action_data_alumni){
            startActivity(new Intent(AlumniDataActivity.this, AlumniDataActivity.class)); // Membuka kembali aktivitas data alumni
        } else{
            Helper.performLogout(AlumniDataActivity.this); // Melakukan logout dan menghapus status login
        }

        return true;
    }
}
