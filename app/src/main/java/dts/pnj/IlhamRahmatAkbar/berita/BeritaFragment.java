package dts.pnj.IlhamRahmatAkbar.berita;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import dts.pnj.IlhamRahmatAkbar.Helper;
import dts.pnj.IlhamRahmatAkbar.R;
import dts.pnj.IlhamRahmatAkbar.alumni.AlumniDataActivity;
import dts.pnj.IlhamRahmatAkbar.alumni.AlumniTambahDataActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BeritaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeritaFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BeritaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BeritaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BeritaFragment newInstance(String param1, String param2) {
        BeritaFragment fragment = new BeritaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true); // Mengaktifkan menu di fragment ini
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate layout untuk fragment
        View rootView = inflater.inflate(R.layout.fragment_berita, container, false);

        // Inisialisasi ListView
        GridView gridViewBerita = rootView.findViewById(R.id.berita_list);

        // Membuat daftar berita untuk ditampilkan
        ArrayList<BeritaPojo> beritaList = new ArrayList<>();
        beritaList.add(new BeritaPojo(R.drawable.berita_1, "Gisela Lulus Unair dengan Gelar Ganda dan IPK 3,97, Begini Kisahnya", "Perkenalkan ini Gisela Keyla Mathea, mahasiswa dengan predikat wisudawan terbaik dari Fakultas Hukum (FH) Universitas Airlangga. Predikat ini didapatkannya bukan tanpa sebab.\n" +
                "Gisela panggilan akrabnya berhasil menyelesaikan studi di jurusan Ilmu Hukum Unair dengan indeks prestasi kumulatif (IPK) nyaris sempurna yakni 3,97. Tidak hanya itu, ia juga berhasil meraih gelar ganda dari Unair dan Maastricht University, Belanda."));
        beritaList.add(new BeritaPojo(R.drawable.berita_2, "Survei 2024: Rerata Biaya Hidup Mahasiswa Jogja Rp 2,96 Juta/Bulan, Melebihi UMR", "Yogyakarta adalah wilayah yang sering disebut sebagai kota pelajar dengan 128 perguruan tinggi dan lebih dari 640 ribu mahasiswa. Namun, berapa sebenarnya biaya hidup mahasiswa di Yogyakarta pada 2024?\n" +
                "Menurut Survei Biaya Hidup Mahasiswa (SBHM) tahun 2024, mahasiswa Yogyakarta atau Jogja menghabiskan rata-rata pengeluaran Rp 2.966.514,00 per bulan. Angka ini meningkat dibanding survei yang sama pada tahun 2020 sebesar Rp 2.917.264,00."));
        beritaList.add(new BeritaPojo(R.drawable.berita_3, "Bank Indonesia Jakarta Buka Lowongan Magang, Tersedia 7 Posisi!", "Bank Indonesia Jakarta membuka kesempatan magang batch 2 untuk periode Juli-Oktober 2024. Program magang ini terbuka bagi mahasiswa dan mahasiswi dan dibuka mulai 28 Juni sampai 3 Juli 2024.\n" +
                "\"Bank Indonesia Jakarta membuka kesempatan magang batch 2 untuk periode Juli-Oktober 2024 bagi para mahasiswa/i yang ingin mendapatkan pengalaman langsung dan belajar tentang kebanksentralan, serta berbagai area pendukungnya,\" tulis unggahan Instagram Bank Indonesia @bank_indonesia_jakarta, dilihat detikcom Selasa (2/6/2024)."));
        beritaList.add(new BeritaPojo(R.drawable.berita_4, "Beasiswa ADB Jepang ke University of Tokyo 2025 Dibuka, Cek Ya!", "Asian Development Bank - Japan Scholarship Program (ADB-JSP) membuka pendaftaran bagi calon mahasiswa yang berencana studi di University of Tokyo (UTokyo), Jepang pada 2025 mendatang. Peminat beasiswa S2 ini dapat mendaftar dan mengunggah dokumen persyaratan paling lambat 10 Desember 2024.\n" +
                "Beasiswa ADB-JSP di University of Tokyo 2025 ini meliputi uang kuliah penuh, tunjangan subsisten bulanan termasuk tempat tinggal, buku dan materi, asuransi kesehatan, serta ongkos perjalanan. Jika penerima beasiswa melakukan riset, akan ada dana khusus persiapan tesis yang diberikan, seperti dikutip dari laman ADB."));
        beritaList.add(new BeritaPojo(R.drawable.berita_5, "Axelsen Bandingkan Medali Emas Olimpiade Tokyo Vs Olimpiade Paris", "Viktor Axelsen sukses mempertahankan medali emas di Olimpiade Paris 2024. Sang pebulutangkis asal Denmark itu membandingkan medali emas dari Tokyo dan Paris.\n" +
                "Viktor Axelsen sabet medali emas Olimpiade Paris 2024 di cabor badminton nomor tunggal putra. Axelsen kalahkan wakil Thailand, Kunlavut Vitidsarn di final 21-11, 21-11."));
        beritaList.add(new BeritaPojo(R.drawable.berita_6, "Apa Itu Efek Dunning-Kruger? Memahami Ilusi Kemampuan dalam Psikologi", "Efek Dunning-Kruger adalah salah satu istilah dalam bidang psikologi. Efek ini disebut memiliki ciri percaya diri berlebihan dan keputusan yang buruk dalam berbagai aspek kehidupan. Simak ulasan berikut!\n" +
                "Fenomena psikologis ini memperlihatkan individu dengan pengetahuan atau keterampilan yang terbatas cenderung menilai diri mereka sebagai lebih kompeten daripada kenyataannya\n" +
                "\n" +
                "Dilansir dari laman Britannica, Efek Dunning-Kruger adalah istilah psikologis yang mengacu pada bias kognitif di mana individu dengan pengetahuan atau keterampilan terbatas di bidang tertentu cenderung melebih-lebihkan keahlian mereka di bidang tersebut jika dibandingkan dengan standar obyektif atau kinerja rekan-rekan mereka."));
        beritaList.add(new BeritaPojo(R.drawable.berita_7, "3 Prodi Unesa Masih Buka Pendaftaran Sampai 5 Agustus, Cek Informasinya di Sini","Universitas Negeri Surabaya (Unesa) masih membuka pendaftaran untuk mahasiswa baru sampai 5 Agustus mendatang. Kendati demikian, pendaftaran hanya dibuka pada tiga program studi (prodi) ini.\n" +
                "Pendaftaran ini dikhususkan bagi 3 prodi yang baru dibuka, yaitu S1 Masase, S1 Hubungan Internasional, dan D4 Teknologi Rekayasa Otomotif. Tiga prodi tersebut masih bisa dipilih pada jalur mandiri Jalur Tes Masuk Unesa Berbasis Komputer (TMUBK) Gelombang III."));
        beritaList.add(new BeritaPojo(R.drawable.berita_8, "5 Beasiswa yang Masih Buka Pendaftaran di Agustus 2024", "1. Beasiswa Cendekia BAZNAS 2024\n" +
                "Beasiswa Cendekia BAZNAS (BCB) 2024 ditujukan untuk mahasiswa D4/S1 yang berkuliah di kampus mitra. Program ini menyediakan pembiayaan dan pembinaan selama 4 semester atau 2 tahun."));
        beritaList.add(new BeritaPojo(R.drawable.berita_9, "Banyak Hewan Juga Bermigrasi Jarak Jauh, Kenapa Tak Alami Jet Lag?", "Ada lebih dari 4.500 spesies yang bermigrasi di bumi. Beberapa di antaranya menempuh jarak yang sangat jauh.\n" +
                "Misalnya, burung laut Arktik (Sterna paradisaea) adalah pemegang rekor untuk jarak perjalanan pulang pergi terjauh melalui udara (44.100 mil). Lalu, burung karibu menempuh jarak perjalanan pulang pergi terjauh di darat (745 mil). Sementara, paus abu-abu timur (Eschrichtius robustus) menempuh jarak terjauh di air (hingga 14.000 mil).\n" +
                "\n" +
                "Namun, tidak satu pun dari hewan yang bermigrasi ini mengalami jet lag. Di sisi lain, manusia sering mengalami jet lag saat mereka terbang di antara zona waktu yang berbeda."));
        beritaList.add(new BeritaPojo(R.drawable.berita_10, "Debut Horor Raphael Varane di Como", "Raphael Varane jalani debut bersama Como 1907 di Coppa Italia. Apes buat bek tengah itu, baru main 20-an menit langsung ditarik keluar karena cedera!\n" +
                "Sampdoria kontra Como 1907 tersaji pada ronde pertama Coppa Italia 2024/2025, Senin (12/8) dini hari WIB. Skor tuntas 1-1 di waktu normal, Loannou bawa Sampdoria unggul duluan di menit ke-37 lalu disamakan Cutrone di menit ke-44."));

        // Membuat adapter dan mengatur ke ListView
        BeritaAdapter adapter = new BeritaAdapter(getContext(), beritaList);
        gridViewBerita.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Mengatur Toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        toolbar.setTitle("Berita");
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // Meng-inflate menu untuk toolbar di fragment ini
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.action_tambah_data) {
            // Membuka activity untuk menambah data alumni
            startActivity(new Intent(requireContext(), AlumniTambahDataActivity.class));
        } else if (itemId == R.id.action_data_alumni) {
            // Membuka activity untuk melihat data alumni
            startActivity(new Intent(requireContext(), AlumniDataActivity.class));
        } else {
            // Melakukan logout menggunakan Helper method
            Helper.performLogout(getContext());
        }

        return true;
    }
}