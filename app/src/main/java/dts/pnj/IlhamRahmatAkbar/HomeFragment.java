package dts.pnj.IlhamRahmatAkbar;

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

import dts.pnj.IlhamRahmatAkbar.alumni.AlumniDataActivity;
import dts.pnj.IlhamRahmatAkbar.alumni.AlumniTambahDataActivity;

/**
 * Fragment ini mewakili halaman beranda aplikasi.
 * Gunakan metode {@link HomeFragment#newInstance} untuk membuat instance fragment ini.
 */
public class HomeFragment extends Fragment {

    // Kunci argumen untuk inisialisasi fragment
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Parameter fragment
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Konstruktor kosong diperlukan agar fragment dapat dibuat tanpa argumen
    }

    /**
     * Metode factory untuk membuat instance baru dari HomeFragment.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return Instance baru dari HomeFragment.
     */
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // Menandakan bahwa fragment ini memiliki menu opsi
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout untuk fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Inisialisasi Toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        // Atur Toolbar sebagai ActionBar
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        // Opsional: atur judul toolbar dan menangani navigasi di sini
        toolbar.setTitle("Home");
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // Inflate menu; ini menambahkan item ke action bar jika ada
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Menangani klik item di action bar
        int itemId = item.getItemId();

        if (itemId == R.id.action_tambah_data){
            // Navigasi ke AlumniTambahDataActivity
            startActivity(new Intent(requireContext(), AlumniTambahDataActivity.class));
        } else if(itemId == R.id.action_data_alumni){
            // Navigasi ke AlumniDataActivity
            startActivity(new Intent(requireContext(), AlumniDataActivity.class));
        } else{
            // Melakukan logout
            Helper.performLogout(getContext());
        }

        return super.onOptionsItemSelected(item);
    }
}
