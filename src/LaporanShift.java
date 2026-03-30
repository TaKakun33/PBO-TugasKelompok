/* File       : LaporanShift.java
 * Deskripsi  : Class yang merepresentasikan laporan rekapitulasi seluruh transaksi dalam satu shift kerja kasir. 
 */

/******** Library ********/
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/******** Class ********/
public class LaporanShift implements CetakDokumen {

    /******** Variable ********/
    private String idLaporan;
    private LocalDateTime waktuGenerate;
    private List<Transaksi> daftarTransaksi; 
    private Kasir kasir;    
    private double totalPendapatan;

    /******** Method ********/

    /* Konstruktor */
    // Membuat objek LaporanShift tanpa parameter
    public LaporanShift() {}

    // Membuat objek LaporanShift dengan kasir yang bertugas
    public LaporanShift(Kasir kasir) {
        this.idLaporan       = "LAP-" + System.currentTimeMillis();
        this.waktuGenerate   = LocalDateTime.now();
        this.kasir           = kasir;
        this.daftarTransaksi = new ArrayList<>();
        this.totalPendapatan = 0.0;
    }

    /* Getter */
    // Mengambil ID laporan
    public String getIdLaporan() {
        return idLaporan;
    }

    // Mengambil waktu generate laporan
    public LocalDateTime getWaktuGenerate() {
        return waktuGenerate;
    }

    // Mengambil daftar transaksi dalam shift ini
    public List<Transaksi> getDaftarTransaksi() {
        return daftarTransaksi;
    }

    // Mengambil total pendapatan shift
    public double getTotalPendapatan() {
        return totalPendapatan;
    }

    /* Method Lainnya */
    // Menambahkan transaksi yang sudah selesai ke dalam laporan shift
    public void tambahTransaksi(Transaksi transaksi) {
        if (transaksi.isSelesai()) {
            daftarTransaksi.add(transaksi);
            totalPendapatan += transaksi.hitungTotalTagihan();
            System.out.println("[LAPORAN] " + transaksi.getIdTransaksi() + " ditambahkan ke laporan shift.");
        } else {
            System.out.println("[!] Transaksi " + transaksi.getIdTransaksi() + " belum selesai, tidak dicatat.");
        }
    }

    // Menghitung ulang total pendapatan dari seluruh transaksi yang tercatat
    public double hitungTotalPendapatan() {
        totalPendapatan = daftarTransaksi.stream().mapToDouble(Transaksi::hitungTotalTagihan).sum();
        return totalPendapatan;
    }

    /* Method Override dari CetakDokumen */
    // Mencetak laporan shift lengkap ke konsol (implementasi CetakDokumen)
    @Override
public void cetak() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        
        System.out.println("*******************************************");
        System.out.println("        LAPORAN SHIFT — TEH TARIK BAKAR   ");
        System.out.println("*******************************************");
        System.out.println("ID Laporan   : " + idLaporan);
        System.out.println("Digenerate   : " + waktuGenerate.format(fmt));
        System.out.println("Kasir        : " + kasir.getNamaKasir() + " (Shift: " + kasir.getShift() + ")");
        System.out.println("-------------------------------------------");
        System.out.println("Rincian Transaksi:");
        if (daftarTransaksi.isEmpty()) {
            System.out.println("(Belum ada transaksi dalam shift ini)");
        } else {
            for (Transaksi t : daftarTransaksi) {
                System.out.println(String.format("%-12s", t.getIdTransaksi()) + "  Rp " + String.format("%,.0f", t.hitungTotalTagihan()));
            }
        }
        System.out.println("-------------------------------------------");
        System.out.println("Jumlah Transaksi : " + daftarTransaksi.size());         
        System.out.println("Total Pendapatan : Rp " + String.format("%,.0f", hitungTotalPendapatan()));
        System.out.println("*******************************************");
    }
}
