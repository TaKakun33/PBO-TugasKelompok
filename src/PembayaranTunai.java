/* File       : PembayaranTunai.java
 * Deskripsi  : Subclass Pembayaran untuk metode pembayaran uang tunai.
 */

/******** Class ********/
public class PembayaranTunai extends Pembayaran {

    /******** Variable ********/
    private double uangDiterima;

    /******** Method ********/

    /* Konstruktor */
    // Membuat objek PembayaranTunai dengan parameter
    public PembayaranTunai(String idPembayaran, double uangDiterima) {
        super(idPembayaran, uangDiterima, "Uang Tunai");
        this.uangDiterima = uangDiterima;
    }

    /* Getter */
    // Mengambil nilai uang yang diterima dari pelanggan
    public double getUangDiterima() {
        return uangDiterima;
    }

    /* Setter */
    // Mengeset uang yang diterima dan sinkronisasi ke jumlahBayar parent
    public void setUangDiterima(double uangDiterima) {
        this.uangDiterima = uangDiterima;
        setJumlahBayar(uangDiterima);
    }

    /* Method Override */
    // Memvalidasi kecukupan pembayaran tunai (overriding dari Pembayaran)
    @Override
    public void validasiPembayaran(double totalTagihan) throws PembayaranKurangException {
        System.out.println("Memvalidasi pembayaran tunai");
        if (uangDiterima < totalTagihan) {
            double kurang = totalTagihan - uangDiterima;
            throw new PembayaranKurangException(kurang);
        }
        System.out.println("Pembayaran tunai valid.");
    }

    /* Method Overloading */
    // Memvalidasi pembayaran tunai dengan pesan error kustom jika gagal
    public void validasiPembayaran(double totalTagihan, String pesanKustom) throws PembayaranKurangException {
        System.out.println("Memvalidasi pembayaran tunai (dengan pesan kustom)");
        if (uangDiterima < totalTagihan) {
            double kurang = totalTagihan - uangDiterima;
            throw new PembayaranKurangException(pesanKustom, kurang);
        }
        System.out.println("Pembayaran tunai valid");
    }
}
