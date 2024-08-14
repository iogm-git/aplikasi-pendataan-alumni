package dts.pnj.IlhamRahmatAkbar.berita;

import android.content.Intent;

public class BeritaPojo {

    // Variabel untuk menyimpan data berita
    private Integer Image;
    private String Judul, Konten;

    // Konstruktor untuk inisialisasi objek BeritaPojo
    public BeritaPojo(Integer image, String judul, String konten) {
        Image = image;
        Judul = judul;
        Konten = konten;
    }

    // Getter untuk mendapatkan nilai Image
    public Integer getImage() {
        return Image;
    }

    // Setter untuk mengatur nilai Image
    public void setImage(Integer image) {
        Image = image;
    }

    // Getter untuk mendapatkan nilai Judul
    public String getJudul() {
        return Judul;
    }

    // Setter untuk mengatur nilai Judul
    public void setJudul(String judul) {
        Judul = judul;
    }

    // Getter untuk mendapatkan nilai Konten
    public String getKonten() {
        return Konten;
    }

    // Setter untuk mengatur nilai Konten
    public void setKonten(String konten) {
        Konten = konten;
    }
}
