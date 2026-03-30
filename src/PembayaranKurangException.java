/* File       : PembayaranKurangException.java
 * Deskripsi  : Custom Exception yang dilempar ketika jumlah pembayaran yang diberikan kurang dari total tagihan transaksi.
 */

/******** Class Exception ********/
public class PembayaranKurangException extends Exception {

    /******** Variable ********/
    private double jumlahKurang;

    /******** Method ********/

    /* Konstruktor — Overloading */
    // Membuat exception dengan pesan otomatis berdasarkan jumlah kekurangan
    public PembayaranKurangException(double jumlahKurang) {
        super("Pembayaran kurang! Kekurangan: Rp " + String.format("%,.0f", jumlahKurang) + ". Harap tambahkan uang.");
        this.jumlahKurang = jumlahKurang;
    }

    // Membuat exception dengan pesan kustom dan jumlah kekurangan
    public PembayaranKurangException(String pesan, double jumlahKurang) {
        super(pesan);
        this.jumlahKurang = jumlahKurang;
    }

    /* Getter */
    // Mengambil nilai kekurangan pembayaran
    public double getJumlahKurang() {
        return jumlahKurang;
    }
}
