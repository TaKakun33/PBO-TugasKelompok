/* File       : Cemilan.java
 * Deskripsi  : Subclass dari MenuItem yang merepresentasikan kategori Cemilan. 
 */

/******** Class ********/
public class Cemilan extends MenuItem {

    /******** Variable ********/
    private String  jenisTopping;
    private boolean tersediaGluten;

    /******** Method ********/

    /* Konstruktor */
    // Membuat objek Cemilan dengan parameter lengkap
    public Cemilan(String kodeItem, String namaItem, double harga, String jenisTopping, boolean tersediaGluten) {
        super(kodeItem, namaItem, harga);
        this.jenisTopping   = jenisTopping;
        this.tersediaGluten = tersediaGluten;
    }

    /* Getter */
    // Mengambil nilai jenisTopping
    public String getJenisTopping() {
        return jenisTopping;
    }

    // Mengambil status gluten
    public boolean isTersediaGluten() {
        return tersediaGluten;
    }

    /* Setter */
    // Mengeset jenisTopping
    public void setJenisTopping(String jenisTopping) {
        this.jenisTopping = jenisTopping;
    }

    // Mengeset status gluten
    public void setTersediaGluten(boolean tersediaGluten) {
        this.tersediaGluten = tersediaGluten;
    }

    /* Method Override */
    // Mengembalikan info lengkap cemilan: kode, nama, harga, topping, dan info gluten
    @Override
    public void getInfo() {
        System.err.println("\nKodeItem Cemilan : " + getKodeItem());
        System.err.println("Harga Cemilan : Rp " + String.format("%,.0f", getHarga()));
        System.out.println("Jenis Topping Cemilan : " + getJenisTopping());
        System.out.println("Apakah Tersedia Gluten? " + isTersediaGluten());
    }

}