package dts.pnj.IlhamRahmatAkbar.berita;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import dts.pnj.IlhamRahmatAkbar.R;

public class BeritaAdapter extends ArrayAdapter<BeritaPojo> {
    private Context beritaContext;  // Menyimpan konteks dari Activity atau Fragment yang menggunakan adapter ini
    private List<BeritaPojo> beritaList = new ArrayList<>();  // Menyimpan daftar berita yang akan ditampilkan

    // Constructor adapter, mengambil konteks dan daftar berita sebagai parameter
    public BeritaAdapter(@NonNull Context context, @NonNull ArrayList<BeritaPojo> list) {
        super(context, 0, list);
        beritaContext = context;
        beritaList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        // Jika tidak ada view yang dapat didaur ulang, inflate layout baru untuk item berita
        if (listItem == null) {
            listItem = LayoutInflater.from(beritaContext).inflate(R.layout.list_item_berita, parent, false);
        }

        // Mendapatkan berita yang akan ditampilkan pada posisi saat ini
        BeritaPojo currentBerita = beritaList.get(position);

        // Menampilkan gambar berita
        ImageView image = listItem.findViewById(R.id.item_image);
        image.setImageResource(currentBerita.getImage());

        // Menampilkan judul berita, memotong teks jika terlalu panjang
        TextView judul = listItem.findViewById(R.id.item_judul);
        String fullJudul = currentBerita.getJudul();
        if (fullJudul.length() > 20) {
            judul.setText(fullJudul.substring(0, 20) + "...");
        } else {
            judul.setText(fullJudul);
        }

        // Menampilkan konten berita, memotong teks jika terlalu panjang
        TextView konten = listItem.findViewById(R.id.item_konten);
        String fullKonten = currentBerita.getKonten();
        if (fullKonten.length() > 70) {
            konten.setText(fullKonten.substring(0, 70) + "...");
        } else {
            konten.setText(fullKonten);
        }

        // Menangani klik pada tombol "Detail", membuka aktivitas detail berita
        Button detailButton = listItem.findViewById(R.id.item_button);
        detailButton.setOnClickListener(v -> {
            // Mempersiapkan intent untuk membuka BeritaDetailActivity dengan detail berita
            Intent intent = new Intent(beritaContext, BeritaDetailActivity.class);
            intent.putExtra("berita.image", currentBerita.getImage());
            intent.putExtra("berita.judul", currentBerita.getJudul());
            intent.putExtra("berita.konten", currentBerita.getKonten());
            beritaContext.startActivity(intent);
        });

        // Mengembalikan view yang sudah diatur untuk ditampilkan di ListView atau GridView
        return listItem;
    }
}
