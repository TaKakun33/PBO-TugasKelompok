/* File       : PembayaranEWallet.java
 * Deskripsi  : Subclass Pembayaran untuk metode pembayaran e-wallet.
 */

/******** Class ********/
public class PembayaranEWallet extends Pembayaran {

    /******** Variable ********/
    private String namaWallet;    
    private String nomorWallet;

    /******** Method ********/

    /* Konstruktor */
    // Membuat objek PembayaranEWallet dengan parameter lengkap
    public PembayaranEWallet(String idPembayaran, double jumlahBayar, String namaWallet, String nomorWallet) {
        super(idPembayaran, jumlahBayar, "E-Wallet (" + namaWallet + ")");
        this.namaWallet   = namaWallet;
        this.nomorWallet  = nomorWallet;
    }

    /* Getter */
    // Mengambil nama wallet
    public String getNamaWallet() {
        return namaWallet;
    }

    // Mengambil nomor wallet
    public String getNomorWallet() {
        return nomorWallet;
    }

    /* Setter */
    // Mengeset nama wallet
    public void setNamaWallet(String namaWallet) {
        this.namaWallet = namaWallet;
    }

    // Mengeset nomor wallet
    public void setNomorWallet(String nomorWallet) {
        this.nomorWallet = nomorWallet;
    }

    /* Method Override */
    // Memvalidasi pembayaran e-wallet (overriding dari Pembayaran)
    @Override
    public void validasiPembayaran(double totalTagihan) throws PembayaranKurangException {
        System.out.println("Memvalidasi pembayaran " + namaWallet );
        if (getJumlahBayar() < totalTagihan) {
            double kurang = totalTagihan - getJumlahBayar();
            throw new PembayaranKurangException(kurang);
        }
        System.out.println("Pembayaran e-wallet valid. No. " + nomorWallet);
    }

    /* Method Overloading */
    // Memvalidasi pembayaran e-wallet dengan pesan error kustom jika gagal
    public void validasiPembayaran(double totalTagihan, String pesanKustom) throws PembayaranKurangException {
        System.out.println("Memvalidasi pembayaran " + namaWallet + " (dengan pesan kustom)");
        if (getJumlahBayar() < totalTagihan) {
            double kurang = totalTagihan - getJumlahBayar();
            throw new PembayaranKurangException(pesanKustom, kurang);
        }
        System.out.println("Pembayaran e-wallet valid. No. " + nomorWallet);
    }
}
