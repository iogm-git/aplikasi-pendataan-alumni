package dts.pnj.IlhamRahmatAkbar.alumni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
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

public class AlumniTambahDataActivity extends AppCompatActivity {

    // Deklarasi variabel untuk input data alumni
    private EditText inputNim, inputNama, inputTempatLahir, inputAlamat, inputAgama, inputNomorHp, inputTahunMasuk, inputTahunLulus, inputPekerjaan, inputJabatan;
    private TextView inputTanggalLahir; // Untuk memilih tanggal lahir menggunakan DatePicker
    private Calendar calendar; // Untuk menyimpan tanggal saat ini
    private Button buttonSubmit; // Tombol untuk menyimpan data
    private Helper.DatabaseHelper dbHelper; // Database helper untuk mengelola database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni_tambah_data);

        // Mengatur toolbar di bagian atas layar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Tambah Data Alumni");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);

        // Menangani klik pada ikon kembali
        toolbar.setNavigationOnClickListener(v -> finish());

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

        dbHelper = new Helper.DatabaseHelper(this); // Inisialisasi database helper
        buttonSubmit = findViewById(R.id.button_simpan); // Inisialisasi tombol submit

        calendar = Calendar.getInstance(); // Mendapatkan tanggal saat ini

        // Mengatur DatePicker untuk memilih tanggal lahir
        inputTanggalLahir.setOnClickListener(v -> {
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Membuka DatePickerDialog untuk memilih tanggal
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AlumniTambahDataActivity.this,
                    (view, year1, month1, dayOfMonth) -> {
                        month1 = month1 + 1; // Bulan dimulai dari 0
                        String date = dayOfMonth + "/" + month1 + "/" + year1;
                        inputTanggalLahir.setText(date); // Menampilkan tanggal yang dipilih
                    },
                    year, month, day);
            datePickerDialog.show();
        });

        // Menangani klik pada tombol submit untuk menambah data alumni
        buttonSubmit.setOnClickListener(view -> tambahDataAlumni());
    }

    // Fungsi untuk menambah data alumni ke dalam database
    void tambahDataAlumni() {
        // Mengambil nilai dari inputan
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

        // Validasi form sebelum menyimpan data
        if(Helper.validasiForm(this, fieldValues, fieldNames)){
            ContentValues values = new ContentValues();
            for (int i = 0; i < fieldValues.length; i++){
                values.put(fieldNames[i], fieldValues[i]);
            }

            // Menyimpan data ke dalam database
            long result = dbHelper.getWritableDatabase().insert("alumni", null, values);
            if (result != -1) {
                Toast.makeText(this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                // Setelah sukses, kembali ke aktivitas data alumni
                startActivity(new Intent(AlumniTambahDataActivity.this, AlumniDataActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Terjadi kesalahan saat menambahkan data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu; menambahkan item ke action bar jika ada
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Menangani klik pada item menu di toolbar
        int itemId = item.getItemId();

        if (itemId == R.id.action_tambah_data){
            // Membuka aktivitas tambah data alumni
            startActivity(new Intent(AlumniTambahDataActivity.this, AlumniTambahDataActivity.class));
        } else if(itemId == R.id.action_data_alumni){
            // Membuka aktivitas data alumni
            startActivity(new Intent(AlumniTambahDataActivity.this, AlumniDataActivity.class));
        } else{
            // Logout pengguna
            Helper.performLogout(AlumniTambahDataActivity.this);
        }

        return true;
    }
}
