/* File       : Kasir.java
 * Deskripsi  : Class yang merepresentasikan pegawai kasir yang memproses transaksi dan menutup shift dengan membuat LaporanShift.
 */

/******** Class ********/
public class Kasir {

    /******** Variable ********/
    private String idKasir;
    private String namaKasir;
    private String shift;  

    /******** Method ********/

    /* Konstruktor */
    // Membuat objek Kasir dengan parameter lengkap
    public Kasir(String idKasir, String namaKasir, String shift) {
        this.idKasir   = idKasir;
        this.namaKasir = namaKasir;
        this.shift     = shift;
    }

    /* Getter */
    // Mengambil ID kasir
    public String getIdKasir() {
        return idKasir;
    }

    // Mengambil nama kasir
    public String getNamaKasir() {
        return namaKasir;
    }

    // Mengambil shift kasir
    public String getShift() {
        return shift;
    }

    /* Setter */
    // Mengeset shift kasir
    public void setShift(String shift) {
        this.shift = shift;
    }

    /* Method Lainnya */
    // Memproses transaksi: validasi pembayaran, selesaikan, dan cetak struk
    public boolean prosesTransaksi(Transaksi transaksi, Pembayaran bayar) {
        System.out.println("\n" + namaKasir + " memproses " + transaksi.getIdTransaksi() + "...");
        // Assertion: keranjang tidak boleh kosong sebelum proses
        assert !transaksi.getKeranjang().isEmpty(): "Tidak bisa memproses transaksi dengan keranjang kosong!";

        double total = transaksi.hitungTotalTagihan();
        System.out.println("Total tagihan: Rp " + String.format("%,.0f", total));

        transaksi.setPembayaran(bayar);

        try {
            bayar.validasiPembayaran(total);
            transaksi.selesaikanTransaksi();
            transaksi.cetak();
            return true;

        } catch (PembayaranKurangException e) {
            System.out.println(e.getMessage());
            System.out.println("Kekurangan: Rp " + String.format("%,.0f", e.getJumlahKurang()));
            System.out.println("Transaksi dibatalkan.");
            return false;
        }
    }

    // Menampilkan ringkasan transaksi tanpa memprosesnya
    public void tampilRingkasanTransaksi(Transaksi transaksi) {
        System.out.println("\nRingkasan " + transaksi.getIdTransaksi());
        transaksi.getKeranjang().tampilKeranjang();
        System.out.println("Total (termasuk pajak): Rp " + String.format("%,.0f", transaksi.hitungTotalTagihan()));
    }

    // Overloading
    // Menampilkan ringkasan dengan opsi header nama kasir (overloading)
    public void tampilRingkasanTransaksi(Transaksi transaksi, boolean tampilNamaKasir) {
        if (tampilNamaKasir) {
            System.out.println("\n[KASIR: " + namaKasir + " | Shift: " + shift + "]");
        }
        tampilRingkasanTransaksi(transaksi);
    }

    // Menutup shift dan menghasilkan LaporanShift untuk shift ini
    public LaporanShift tutupShift() {
        System.out.println("\n" + namaKasir + " menutup shift " + shift);
        LaporanShift laporan = new LaporanShift(this);
        return laporan;
    }

    /* Method Lainnya */
    // Mencetak informasi kasir yang sedang bertugas
    public void printInfo() {
        System.out.println("Kasir[" + idKasir + "] " + namaKasir + " (Shift: " + shift + ")");
    }

}
