/* File       : MenuItem.java
 * Deskripsi  : Abstract class sebagai representasi umum dari setiap item menu yang tersedia di kedai. 
 */

/******** Abstract Class ********/
public abstract class MenuItem {

    /******** Variable ********/
    private String kodeItem;
    private String namaItem;
    private double harga;

    /******** Method ********/

    /* Konstruktor */
    // Membuat objek MenuItem tanpa parameter
    public MenuItem() {}

    // Membuat objek MenuItem dengan parameter
    public MenuItem(String kodeItem, String namaItem, double harga) {
        this.kodeItem = kodeItem;
        this.namaItem = namaItem;
        this.harga    = harga;
    }

    /* Getter */
    // Mengambil nilai kodeItem
    public String getKodeItem() {
        return kodeItem;
    }

    // Mengambil nilai namaItem
    public String getNamaItem() {
        return namaItem;
    }

    // Mengambil nilai harga
    public double getHarga() {
        return harga;
    }

    /* Setter */
    // Mengeset kodeItem
    public void setKodeItem(String kodeItem) {
        this.kodeItem = kodeItem;
    }

    // Mengeset namaItem
    public void setNamaItem(String namaItem) {
        this.namaItem = namaItem;
    }

    // Mengeset harga
    public void setHarga(double harga) {
        this.harga = harga;
    }

    /* Method Abstract */
    // Mengembalikan info lengkap item: kode, nama, harga, dan deskripsi spesifik
    public abstract void getInfo(); 
}