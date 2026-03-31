/* File       : Main.java
 * Deskripsi  : Main Menu aplikasi POS Kedai Teh Tarik Bakar.
 */

public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   SISTEM POS - KEDAI TEH TARIK BAKAR     ║");
        System.out.println("╚══════════════════════════════════════════╝");

        // ---- Inisialisasi Item Menu ----
        Minuman tehTarik     = new Minuman("M001", "Teh Tarik Original", 15000,
                                           "Regular", true);
        Minuman kopiTarik    = new Minuman("M002", "Kopi Tarik",         18000,
                                           "Large",   true);
        Cemilan rotiBarang   = new Cemilan("C001", "Roti Bakar Coklat",  12000,
                                           "Coklat Meses", true);
        Cemilan pisangCoklat = new Cemilan("C002", "Pisang Coklat",      10000,
                                           "Coklat Keju",  false);

        System.out.println("\n=== MENU TERSEDIA ===");
        tehTarik.getInfo();
        kopiTarik.getInfo();
        rotiBarang.getInfo();
        pisangCoklat.getInfo();

        // ---- Demo Overriding getInfo() ----
        System.out.println("\n=== DEMO METHOD OVERRIDING (getInfo) ===");
        MenuItem[] menu = {tehTarik, kopiTarik, rotiBarang, pisangCoklat};
        for (MenuItem m : menu) {
            m.getInfo();
        }

        // ---- Inisialisasi Kasir ----
        Kasir kasir = new Kasir("K001", "Budi Santoso", "Pagi");
        System.out.println("\n=== KASIR BERTUGAS ===");
        kasir.printInfo();

        // ==============================================================
        // SKENARIO 1: Pembayaran Tunai GAGAL (uang kurang)
        // ==============================================================
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println("SKENARIO 1 : Pembayaran Tunai (Kurang)    ");
        System.out.println("══════════════════════════════════════════");

        KeranjangPesanan keranjang1 = new KeranjangPesanan();
        keranjang1.tambahItem(new ItemPesanan(tehTarik,   1));
        keranjang1.tambahItem(new ItemPesanan(rotiBarang, 2));

        Transaksi trx1 = new Transaksi(keranjang1);
        PembayaranTunai bayarKurang = new PembayaranTunai("PAY-001", 30000);

        // --------------------------------------------------------------
        // TAMBAHAN: DEMO METHOD OVERLOADING 
        // --------------------------------------------------------------
        System.out.println("\n--- DEMO METHOD OVERLOADING (tampilRingkasanTransaksi) ---");
        System.out.println("-> Pemanggilan Versi 1 (Hanya 1 parameter Transaksi):");
        kasir.tampilRingkasanTransaksi(trx1); 
        
        System.out.println("\n-> Pemanggilan Versi 2 (Dengan parameter boolean untuk nama kasir):");
        kasir.tampilRingkasanTransaksi(trx1, true); 

        System.out.println("\n--- DEMO METHOD OVERLOADING (validasiPembayaran) ---");
        double totalTagihanTrx1 = trx1.hitungTotalTagihan();
        
        System.out.println("-> Pemanggilan Versi 1 (Validasi standar):");
        try {
            bayarKurang.validasiPembayaran(totalTagihanTrx1);
        } catch (Exception e) {
            System.out.println("Exception ditangkap: " + e.getMessage());
        }

        System.out.println("\n-> Pemanggilan Versi 2 (Validasi dengan custom error message):");
        try {
            bayarKurang.validasiPembayaran(totalTagihanTrx1, "Uang tunai pelanggan tidak mencukupi!");
        } catch (Exception e) {
            System.out.println("Exception ditangkap: " + e.getMessage());
        }
        System.out.println("--------------------------------------------------------------\n");

        // Lanjut ke proses transaksi utama di Skenario 1
        boolean hasil1 = kasir.prosesTransaksi(trx1, bayarKurang);
        System.out.println("Hasil proses akhir: " + (hasil1 ? "BERHASIL" : "GAGAL"));

        // ==============================================================
        // SKENARIO 2: Pembayaran Tunai BERHASIL
        // ==============================================================
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println("SKENARIO 2 : Pembayaran Tunai (Cukup)     ");
        System.out.println("══════════════════════════════════════════");

        KeranjangPesanan keranjang2 = new KeranjangPesanan();
        keranjang2.tambahItem(new ItemPesanan(tehTarik,     2));
        keranjang2.tambahItem(new ItemPesanan(pisangCoklat, 1));

        Transaksi trx2 = new Transaksi(keranjang2);
        PembayaranTunai bayarCukup = new PembayaranTunai("PAY-002", 100000);

        boolean hasil2 = kasir.prosesTransaksi(trx2, bayarCukup);
        System.out.println("Hasil proses: " + (hasil2 ? "BERHASIL" : "GAGAL"));

        // ==============================================================
        // SKENARIO 3: Pembayaran E-Wallet BERHASIL
        // ==============================================================
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println("SKENARIO 3 : Pembayaran E-Wallet          ");
        System.out.println("══════════════════════════════════════════");

        KeranjangPesanan keranjang3 = new KeranjangPesanan();
        keranjang3.tambahItem(new ItemPesanan(kopiTarik,    1));
        keranjang3.tambahItem(new ItemPesanan(rotiBarang,   1));
        keranjang3.tambahItem(new ItemPesanan(pisangCoklat, 1));

        Transaksi trx3 = new Transaksi(keranjang3);
        double totalTrx3 = trx3.hitungTotalTagihan();
        PembayaranEWallet bayarEWallet = new PembayaranEWallet(
                "PAY-003", totalTrx3, "GoPay", "0812-3456-7890");

        boolean hasil3 = kasir.prosesTransaksi(trx3, bayarEWallet);
        System.out.println("Hasil proses: " + (hasil3 ? "BERHASIL" : "GAGAL"));

        // ==============================================================
        // SKENARIO 4: Tutup Shift & Cetak LaporanShift
        // ==============================================================
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println("SKENARIO 4 : Tutup Shift & Laporan        ");
        System.out.println("══════════════════════════════════════════");

        LaporanShift laporan = kasir.tutupShift();

        // Hanya transaksi yang selesai yang dimasukkan ke laporan
        laporan.tambahTransaksi(trx1);   // Gagal — tidak akan masuk
        laporan.tambahTransaksi(trx2);   // Berhasil — masuk
        laporan.tambahTransaksi(trx3);   // Berhasil — masuk

        // Cetak laporan (implementasi interface CetakDokumen)
        laporan.cetak();

        // ==============================================================
        // DEMO ASSERTION: Keranjang Kosong
        // ==============================================================
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println("  DEMO ASSERTION : Keranjang Kosong        ");
        System.out.println("══════════════════════════════════════════");
        System.out.println("[INFO] Jalankan dengan flag -ea untuk mengaktifkan assertion.");

        try {
            KeranjangPesanan keranjangKosong = new KeranjangPesanan();
            double sub = keranjangKosong.hitungSubtotal();
            System.out.println("[PASS] Assertion tidak aktif, subtotal = " + sub);
        } catch (AssertionError ae) {
            System.out.println("[ASSERTION ERROR] " + ae.getMessage());
        }

        // ==============================================================
        // DEMO INTERFACE CetakDokumen (polymorphism)
        // ==============================================================
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println("DEMO INTERFACE CetakDokumen              ");
        System.out.println("══════════════════════════════════════════");
        System.out.println("  Mencetak ulang struk trx2 via interface:");
        CetakDokumen dokumen = trx2;   // Polymorphism: Transaksi sebagai CetakDokumen
        dokumen.cetak();

        System.out.println("\n[SISTEM] Semua skenario selesai dijalankan.");
    }
}