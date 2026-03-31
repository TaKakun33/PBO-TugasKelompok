/* File       : Transaksi.java
 * Deskripsi  : Class yang merepresentasikan satu transaksi penjualan.
 */

/******** Library ********/
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/******** Class ********/
public class Transaksi implements CetakDokumen {

    /******** Variable ********/
    private static int counterTransaksi = 0;   
    private LocalDateTime waktuTransaksi;
    private boolean selesai;
    private KeranjangPesanan keranjang;   
    private Pembayaran pembayaran;  
    private String idTransaksi;
    private static final double PAJAK = 0.10;

    /******** Method ********/

    /* Konstruktor */
    // Membuat objek Transaksi dengan keranjang yang sudah terisi
    public Transaksi(KeranjangPesanan keranjang) {
        counterTransaksi++;
        this.idTransaksi    = String.format("TRX-%04d", counterTransaksi);
        this.waktuTransaksi = LocalDateTime.now();
        this.keranjang      = keranjang;
        this.selesai        = false;
    }

    /* Getter */
    // Mengambil ID transaksi
    public String getIdTransaksi() {
        return idTransaksi;
    }

    // Mengambil waktu transaksi
    public LocalDateTime getWaktuTransaksi() {
        return waktuTransaksi;
    }

    // Mengambil objek KeranjangPesanan
    public KeranjangPesanan getKeranjang() {
        return keranjang;
    }

    // Mengambil objek Pembayaran
    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    // Mengambil status selesai transaksi
    public boolean isSelesai() {
        return selesai;
    }

    /* Setter */
    // Mengeset pembayaran untuk transaksi ini
    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }

    /* Method Lainnya */
    // Menghitung total tagihan setelah pajak ditambahkan
    public double hitungTotalTagihan() {
        return keranjang.hitungSubtotal() + hitungPajak();
    }

    // Menghitung nilai pajak saja (10% dari subtotal)
    public double hitungPajak() {
        return keranjang.hitungSubtotal() * PAJAK;
    }

    // Menyelesaikan transaksi setelah pembayaran berhasil divalidasi
    public void selesaikanTransaksi() {
        this.selesai = true;
        System.out.println("Transaksi : " + idTransaksi + " selesai.");
    }

    /* Method Override dari CetakDokumen */
    // Mencetak struk transaksi dari sebuah pesanan
    @Override
    public void cetak() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("===========================================");
        System.out.println("         KEDAI TEH TARIK BAKAR            ");
        System.out.println("===========================================");
        System.out.println("ID Transaksi : " + idTransaksi);
        System.out.println("Waktu        : " + waktuTransaksi.format(fmt));
        System.out.println("-------------------------------------------");
        keranjang.tampilKeranjang();
        System.out.println("-------------------------------------------");
        System.out.println("Subtotal     : Rp " + String.format("%,.0f", keranjang.hitungSubtotal()));
        System.out.println("Pajak (10%)  : Rp " + String.format("%,.0f", hitungPajak()));
        System.out.println("TOTAL        : Rp " + String.format("%,.0f", hitungTotalTagihan()));
        if (pembayaran != null) {
            System.out.println("-------------------------------------------");
            System.out.println("Bayar " + String.format("%-10s", "(" + pembayaran.getMetodePembayaran() + ")") + ": Rp " + String.format("%,.0f", pembayaran.getJumlahBayar()));
            double kembalian = pembayaran.hitungKembalian(hitungTotalTagihan());
            System.out.println("Kembalian    : Rp " + String.format("%,.0f", Math.max(kembalian, 0)));
        }
        System.out.println("===========================================");
        System.out.println("       Terima kasih, sampai jumpa!        ");
        System.out.println("===========================================");
    }
}
