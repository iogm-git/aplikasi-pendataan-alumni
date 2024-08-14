package dts.pnj.IlhamRahmatAkbar.alumni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import dts.pnj.IlhamRahmatAkbar.Helper;
import dts.pnj.IlhamRahmatAkbar.R;

public class AlumniUbahDataActivity extends AppCompatActivity {

    // Deklarasi variabel untuk komponen UI
    private EditText inputNim, inputNama, inputTempatLahir,
            inputAlamat, inputAgama, inputNomorHp, inputTahunMasuk, inputTahunLulus,
            inputPekerjaan, inputJabatan;

    private TextView inputTanggalLahir;

    private Button buttonSubmit;

    private Calendar calendar;

    private Helper.DatabaseHelper dbHelper;

    private String id; // Menyimpan ID alumni yang akan diubah datanya

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_ubah_data);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Ubah Data Alumni");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);

        // Menangani klik pada tombol navigasi (ikon kembali)
        toolbar.setNavigationOnClickListener(v -> finish());

        // Ambil ID dari Intent
        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        // Menghubungkan variabel dengan komponen UI di layout
        inputNim = findViewById(R.id.input_nim);
        inputNama = findViewById(R.id.input_nama_alumni);
        inputTempatLahir = findViewById(R.id.input_tempat_lahir);
        inputTanggalLahir = findViewById(R.id.input_tanggal_lahir);
        inputAlamat = findViewById(R.id.input_alamat);
        inputAgama = findViewById(R.id.input_agama);
        inputNomorHp = findViewById(R.id.input_nomor_hp);
        inputTahunMasuk = findViewById(R.id.input_tahun_masuk);
        inputTahunLulus = findViewById(R.id.input_tahun_lulus);
        inputPekerjaan = findViewById(R.id.input_pekerjaan);
        inputJabatan = findViewById(R.id.input_jabatan);

        // Inisialisasi DatabaseHelper
        dbHelper = new Helper.DatabaseHelper(this);

        // Menghubungkan button dengan layout dan menambahkan listener
        buttonSubmit = findViewById(R.id.button_simpan);

        // Inisialisasi kalender untuk DatePicker
        calendar = Calendar.getInstance();

        // Menangani klik pada TextView tanggal lahir untuk menampilkan DatePickerDialog
        inputTanggalLahir.setOnClickListener(v -> {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Menampilkan DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AlumniUbahDataActivity.this,
                    (view, year1, month1, dayOfMonth) -> {
                        month1 = month1 + 1; // Bulan dimulai dari 0, jadi tambahkan 1
                        String date = dayOfMonth + "/" + month1 + "/" + year1;
                        inputTanggalLahir.setText(date); // Atur tanggal yang dipilih ke TextView
                    },
                    year, month, day);
            datePickerDialog.show();
        });

        // Menambahkan listener ke button untuk menyimpan perubahan
        buttonSubmit.setOnClickListener(view -> ubahDataAlumni());

        // Memuat data alumni yang akan diubah berdasarkan ID
        reloadData(id);
    }

    // Fungsi untuk mengubah data alumni
    void ubahDataAlumni() {
        // Mengambil data dari form
        String nim = inputNim.getText().toString();
        String nama = inputNama.getText().toString();
        String tempatLahir = inputTempatLahir.getText().toString();
        String tanggalLahir = inputTanggalLahir.getText().toString();
        String alamat = inputAlamat.getText().toString();
        String agama = inputAgama.getText().toString();
        String nomorHp = inputNomorHp.getText().toString();
        String tahunMasuk = inputTahunMasuk.getText().toString();
        String tahunLulus = inputTahunLulus.getText().toString();
        String pekerjaan = inputPekerjaan.getText().toString();
        String jabatan = inputJabatan.getText().toString();

        // Masukkan semua data ke dalam array
        String[] fieldValues = {nim, nama, tempatLahir, tanggalLahir, alamat, agama, nomorHp, tahunMasuk, tahunLulus, pekerjaan, jabatan};
        String[] fieldNames = {"nim", "nama_alumni", "tempat_lahir", "tanggal_lahir", "alamat", "agama", "nomor_hp", "tahun_masuk", "tahun_lulus", "pekerjaan", "jabatan"};

        // Validasi form
        if (Helper.validasiForm(this, fieldValues, fieldNames)) {
            ContentValues values = new ContentValues();
            for (int i = 0; i < fieldValues.length; i++) {
                values.put(fieldNames[i], fieldValues[i]); // Mengisi ContentValues dengan data
            }

            // Update data di database
            long result = dbHelper.getWritableDatabase().update("alumni", values, "nim = ?", new String[]{id});
            if (result != -1) {
                Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AlumniUbahDataActivity.this, AlumniDataActivity.class));
                finish(); // Mengakhiri aktivitas ini setelah data berhasil diubah
            } else {
                Toast.makeText(this, "Terjadi kesalahan saat mengubah data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Fungsi untuk memuat data alumni yang akan diubah
    private void reloadData(String id) {
        // Query untuk mengambil data alumni dari database berdasarkan NIM
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                "SELECT * FROM alumni WHERE nim = ?",
                new String[]{id}
        );

        // Mengisi form dengan data yang diambil dari database
        if (cursor != null && cursor.moveToFirst()) {
            inputNim.setText(cursor.getString(cursor.getColumnIndexOrThrow("nim")));
            inputNama.setText(cursor.getString(cursor.getColumnIndexOrThrow("nama_alumni")));
            inputTempatLahir.setText(cursor.getString(cursor.getColumnIndexOrThrow("tempat_lahir")));
            inputTanggalLahir.setText(cursor.getString(cursor.getColumnIndexOrThrow("tanggal_lahir")));
            inputAlamat.setText(cursor.getString(cursor.getColumnIndexOrThrow("alamat")));
            inputAgama.setText(cursor.getString(cursor.getColumnIndexOrThrow("agama")));
            inputNomorHp.setText(cursor.getString(cursor.getColumnIndexOrThrow("nomor_hp")));
            inputTahunMasuk.setText(cursor.getString(cursor.getColumnIndexOrThrow("tahun_masuk")));
            inputTahunLulus.setText(cursor.getString(cursor.getColumnIndexOrThrow("tahun_lulus")));
            inputPekerjaan.setText(cursor.getString(cursor.getColumnIndexOrThrow("pekerjaan")));
            inputJabatan.setText(cursor.getString(cursor.getColumnIndexOrThrow("jabatan")));
        }

        // Menutup cursor untuk menghindari kebocoran memori
        if (cursor != null) {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Menangani klik pada item menu
        int itemId = item.getItemId();

        if (itemId == R.id.action_tambah_data) {
            startActivity(new Intent(AlumniUbahDataActivity.this, AlumniTambahDataActivity.class));
        } else if (itemId == R.id.action_data_alumni) {
            startActivity(new Intent(AlumniUbahDataActivity.this, AlumniDataActivity.class));
        } else {
            Helper.performLogout(AlumniUbahDataActivity.this); // Logout jika item yang dipilih adalah logout
        }

        return true;
    }
}
