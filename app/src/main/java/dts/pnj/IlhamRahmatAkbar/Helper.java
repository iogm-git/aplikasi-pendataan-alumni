package dts.pnj.IlhamRahmatAkbar;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Helper {
    public static void performLogout(Context context) {
        // Path ke file userdata.txt
        File file = new File(context.getExternalFilesDir(null), "userdata.txt");

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                String fileContent = new String(data);

                // Konten file menjadi baris
                String[] lines = fileContent.split("\n");

                // Buat StringBuilder untuk menyusun konten baru
                StringBuilder updatedContent = new StringBuilder();

                for (String line : lines) {
                    if (line.startsWith("status login: ")) {
                        // Hapus status login
                        continue;
                    }
                    updatedContent.append(line).append("\n");
                }

                // Tulis konten yang telah diperbarui ke file
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    fos.write(updatedContent.toString().getBytes());
                    fos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Gagal memperbarui file", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Redirect ke LoginActivity
                Intent intent = new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "Gagal membaca file", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "File userdata.txt tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "ilhamrhmtkbr.db";
        private static final int DATABASE_VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Membuat tabel
            db.execSQL("CREATE TABLE alumni" +
                    " (nim INTEGER PRIMARY KEY, " +
                    "nama_alumni TEXT, " +
                    "tempat_lahir TEXT," +
                    "tanggal_lahir TEXT," +
                    "alamat TEXT," +
                    "agama TEXT," +
                    "nomor_hp TEXT," +
                    "tahun_masuk TEXT," +
                    "tahun_lulus TEXT," +
                    "pekerjaan TEXT," +
                    "jabatan TEXT);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Menghapus tabel lama dan membuat tabel baru
            db.execSQL("DROP TABLE IF EXISTS alumni");
            onCreate(db);
        }
    }

    public static boolean validasiForm(Context context, String[] fieldValues, String[] fieldNames){
        boolean isDataValid = false;

        for (int i = 0; i < fieldValues.length; i++) {
            if (fieldValues[i].isEmpty()) {
                // Tampilkan pesan error
                Toast.makeText(context, fieldNames[i] + " tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                isDataValid = true;
            }
        }

        return isDataValid;
    }
}
