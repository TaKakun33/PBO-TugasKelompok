/* File       : Pembayaran.java
 * Deskripsi  : Abstract class yang merepresentasikan pembayaran secara umum.
 */

/******** Abstract Class ********/
public abstract class Pembayaran {

    /******** Variable ********/
    private String idPembayaran;
    private double jumlahBayar;
    private String metodePembayaran;

    /******** Method ********/

    /* Konstruktor */
    // Membuat objek Pembayaran tanpa parameter
    public Pembayaran() {}

    // Membuat objek Pembayaran dengan parameter
    public Pembayaran(String idPembayaran, double jumlahBayar, String metodePembayaran) {
        this.idPembayaran     = idPembayaran;
        this.jumlahBayar      = jumlahBayar;
        this.metodePembayaran = metodePembayaran;
    }

    /* Getter */
    // Mengambil ID pembayaran
    public String getIdPembayaran() {
        return idPembayaran;
    }

    // Mengambil jumlah bayar
    public double getJumlahBayar() {
        return jumlahBayar;
    }

    // Mengambil metode pembayaran
    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    /* Setter */
    // Mengeset jumlah bayar (digunakan subclass untuk sinkronisasi nilai)
    public void setJumlahBayar(double jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
    }

    /* Method Lainnya */
    // Menghitung kembalian dari pembayaran dikurangi total tagihan
    public double hitungKembalian(double totalTagihan) {
        return jumlahBayar - totalTagihan;
    }

    /* Method Abstract */
    // Memvalidasi kecukupan pembayaran — wajib diimplementasikan subclass
    public abstract void validasiPembayaran(double totalTagihan) throws PembayaranKurangException;

}
