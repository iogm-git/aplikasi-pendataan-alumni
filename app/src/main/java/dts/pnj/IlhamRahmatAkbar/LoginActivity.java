package dts.pnj.IlhamRahmatAkbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        Button buttonLogin = findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        // Cek apakah file sudah ada menggunakan SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isFileCreated = sharedPreferences.getBoolean("isFileCreated", false);

        if (!isFileCreated) {
            createUserdataFile();
            // Simpan status bahwa file sudah dibuat
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFileCreated", true);
            editor.apply();
        }
    }

    private void performLogin() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        // Path ke file userdata.txt
        File file = new File(getExternalFilesDir(null), "userdata.txt");

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                String fileContent = new String(data);

                // Cek apakah email dan password cocok
                if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Mohon lengkapi formulir", Toast.LENGTH_SHORT).show();
                } else if (email.equals("ilhamrhmtkbr@gmail.com") && password.equals("ilham123")) {
                    // Pisahkan konten file menjadi baris
                    String[] lines = fileContent.split("\n");

                    // Buat StringBuilder untuk menyusun konten baru
                    StringBuilder updatedContent = new StringBuilder();

                    for (String line : lines) {
                        if (line.startsWith("status login: ")) {
                            // Hapus status login yang lama
                            continue;
                        }
                        updatedContent.append(line).append("\n");
                    }

                    // Tambahkan status login yang baru
                    updatedContent.append("status login: true");

                    // Tulis konten yang telah diperbarui ke file
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        fos.write(updatedContent.toString().getBytes());
                        fos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Gagal memperbarui file", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Redirect ke fragment_profile
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("redirectTo", "fragment_profile");
                    startActivity(intent);
                    finish(); // Tutup LoginActivity
                } else {
                    Toast.makeText(this, "Email atau password salah", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Gagal membaca file", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "File userdata.txt tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    private void createUserdataFile() {
        // Nama file yang akan dibuat
        String fileName = "userdata.txt";
        // Data yang akan disimpan
        String data = "ilhamrhmtkbr@gmail.com\n" +
                "ilham123\n" +
                "112233\n" +
                "Ilham Rahmat Akbar\n" +
                "Lulusan Diploma 3";

        // Dapatkan direktori khusus aplikasi di penyimpanan eksternal
        File file = new File(getExternalFilesDir(null), fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes());
            fos.flush();
            // Informasi kepada pengguna bahwa file telah berhasil dibuat
            Toast.makeText(this, "File userdata.txt berhasil dibuat", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            // Informasi jika terjadi kesalahan
            Toast.makeText(this, "Gagal membuat file", Toast.LENGTH_SHORT).show();
        }
    }
}
