/* File       : Minuman.java
 * Deskripsi  : Subclass dari MenuItem yang merepresentasikan kategori Minuman.
 */

/******** Class ********/
public class Minuman extends MenuItem {

    /******** Variable ********/
    private String  ukuran; 
    private boolean panas;   

    /******** Method ********/

    /* Konstruktor */
    // Membuat objek Minuman tanpa parameter
    public Minuman() {}

    // Membuat objek Minuman dengan parameter lengkap
    public Minuman(String kodeItem, String namaItem, double harga, String ukuran, boolean panas) {
        super(kodeItem, namaItem, harga);
        this.ukuran = ukuran;
        this.panas  = panas;
    }

    /* Getter */
    // Mengambil nilai ukuran
    public String getUkuran() {
        return ukuran;
    }

    // Mengambil status suhu minuman
    public boolean isPanas() {
        return panas;
    }

    /* Setter */
    // Mengeset ukuran minuman
    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    // Mengeset status suhu minuman
    public void setPanas(boolean panas) {
        this.panas = panas;
    }

    /* Method Override */
    // Mengembalikan info lengkap minuman: kode, nama, harga, ukuran, dan suhu
    @Override
    public void getInfo() {
        String suhu = panas ? "Panas" : "Dingin";
        System.out.println("[" + getKodeItem() + "] " + getNamaItem() + " - Rp " + String.format("%,.0f", getHarga()) + " | " + ukuran + " (" + suhu + ")");
    }
    /* Method Overloading */
    // Memesan minuman dengan ukuran dan suhu default dari objek
    public String pesan() {
        String suhu = panas ? "Panas" : "Dingin";
        return "Memesan: " + getNamaItem() + " - " + ukuran + " (" + suhu + ")";
    }

    // Memesan minuman dengan ukuran pilihan
    public String pesan(String ukuranPilihan) {
        return "Memesan: " + getNamaItem() + " - " + ukuranPilihan;
    }

    // Memesan minuman dengan ukuran dan suhu pilihan
    public String pesan(String ukuranPilihan, boolean panasRequest) {
        String suhu = panasRequest ? "Panas" : "Dingin";
        return "Memesan: " + getNamaItem() + " - " + ukuranPilihan + " (" + suhu + ")";
    }
}