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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import dts.pnj.IlhamRahmatAkbar.alumni.AlumniDataActivity;
import dts.pnj.IlhamRahmatAkbar.alumni.AlumniTambahDataActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private TextView textEmail, textNim, textNama, textKelas;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        textEmail = view.findViewById(R.id.text_email);
        textNim = view.findViewById(R.id.text_nim);
        textNama = view.findViewById(R.id.text_nama);
        textKelas = view.findViewById(R.id.text_kelas);

        // Load user data from file
        loadUserData();

        Button buttonLogout = view.findViewById(R.id.button_logout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.performLogout(getContext());
            }
        });

        return view;
    }

    private void loadUserData() {
        // Path ke file userdata.txt
        File file = new File(getContext().getExternalFilesDir(null), "userdata.txt");

        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                String fileContent = new String(data);

                // Pisahkan konten file menjadi baris
                String[] lines = fileContent.split("\n");

                // Asumsikan data terpisah dengan urutan yang benar
                if (lines.length >= 5) {
                    textEmail.setText(lines[0]);
                    textNim.setText(lines[2]);
                    textNama.setText(lines[3]);
                    textKelas.setText(lines[4]);
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Gagal membaca file", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "File userdata.txt tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        toolbar.setTitle("Profile");
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.action_tambah_data){
            startActivity(new Intent(requireContext(), AlumniTambahDataActivity.class));
        } else if(itemId == R.id.action_data_alumni){
            startActivity(new Intent(requireContext(), AlumniDataActivity.class));
        } else{
            // Melakukan logout
            Helper.performLogout(getContext());
        }

        return true;
    }
}