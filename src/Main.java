/* File       : Main.java
 * Deskripsi  : Simulasi lengkap sistem kasir Kedai Teh Tarik Bakar.
 *              Mencakup: pembuatan menu, pesanan, berbagai metode pembayaran,
 *              penanganan exception, dan laporan shift.
 */

public class Main {

    public static void main(String[] args) {

        // =====================================================================
        // BAGIAN 1 — SETUP MENU
        // Membuat item-item menu (Minuman & Cemilan) yang tersedia di kedai
        // =====================================================================
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("  SELAMAT DATANG DI KEDAI TEH TARIK BAKAR  ");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // --- Minuman ---
        Minuman tehTarikPanas  = new Minuman("M001", "Teh Tarik Bakar Panas",  18_000, "Regular", true);
        Minuman tehTarikDingin = new Minuman("M002", "Teh Tarik Bakar Dingin", 20_000, "Large",   false);
        Minuman kopiSusu       = new Minuman("M003", "Kopi Susu Kekinian",     22_000, "Regular", false);
        Minuman matchaLatte    = new Minuman("M004", "Matcha Latte",           25_000, "Large",   true);

        // --- Cemilan ---
        Cemilan rotiBakar     = new Cemilan("C001", "Roti Bakar Spesial",  15_000, "Keju & Coklat", false);
        Cemilan singkongKeju  = new Cemilan("C002", "Singkong Keju",       12_000, "Keju",          true);
        Cemilan pisangBakar   = new Cemilan("C003", "Pisang Bakar Milo",   14_000, "Milo",          false);

        // Tampilkan info beberapa item menu
        System.out.println("=== INFO MENU ===");
        tehTarikPanas.getInfo();
        rotiBakar.getInfo();

        // =====================================================================
        // BAGIAN 2 — SETUP KASIR
        // =====================================================================
        System.out.println("\n=== KASIR BERTUGAS ===");
        Kasir kasir1 = new Kasir("K001", "Budi Santoso", "Pagi");
        Kasir kasir2 = new Kasir("K002", "Sari Dewi",    "Siang");
        kasir1.printInfo();
        kasir2.printInfo();

        // =====================================================================
        // BAGIAN 3 — TRANSAKSI 1: Pembayaran Tunai Berhasil (Kasir 1)
        // Pelanggan memesan teh tarik panas + roti bakar, bayar tunai
        // =====================================================================
        System.out.println("\n\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  TRANSAKSI 1 — Pelanggan: Pak Agus");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        KeranjangPesanan keranjang1 = new KeranjangPesanan();
        keranjang1.tambahItem(new ItemPesanan(tehTarikPanas, 2));
        keranjang1.tambahItem(new ItemPesanan(rotiBakar,     1));

        Transaksi trx1 = new Transaksi(keranjang1);

        // Tampilkan ringkasan dengan nama kasir
        kasir1.tampilRingkasanTransaksi(trx1, true);

        // Bayar tunai — uang lebih (kembalian ada)
        PembayaranTunai bayarTunai1 = new PembayaranTunai("PAY-001", 100_000);
        boolean berhasil1 = kasir1.prosesTransaksi(trx1, bayarTunai1);
        System.out.println("Status proses: " + (berhasil1 ? "BERHASIL" : "GAGAL"));

        // =====================================================================
        // BAGIAN 4 — TRANSAKSI 2: Pembayaran E-Wallet Berhasil (Kasir 1)
        // Pelanggan memesan matcha latte + singkong keju + teh dingin, bayar GoPay
        // =====================================================================
        System.out.println("\n\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  TRANSAKSI 2 — Pelanggan: Mbak Rina");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        KeranjangPesanan keranjang2 = new KeranjangPesanan();
        keranjang2.tambahItem(new ItemPesanan(matchaLatte,    1));
        keranjang2.tambahItem(new ItemPesanan(singkongKeju,   2));
        keranjang2.tambahItem(new ItemPesanan(tehTarikDingin, 1));

        Transaksi trx2 = new Transaksi(keranjang2);
        kasir1.tampilRingkasanTransaksi(trx2);

        PembayaranEWallet bayarGopay = new PembayaranEWallet("PAY-002", 90_000, "GoPay", "0812-3456-7890");
        boolean berhasil2 = kasir1.prosesTransaksi(trx2, bayarGopay);
        System.out.println("Status proses: " + (berhasil2 ? "BERHASIL" : "GAGAL"));

        // =====================================================================
        // BAGIAN 5 — TRANSAKSI 3: Pembayaran Tunai GAGAL — Uang Kurang (Kasir 1)
        // Demonstrasi PembayaranKurangException dengan pesan default
        // =====================================================================
        System.out.println("\n\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  TRANSAKSI 3 — Pelanggan: Mas Doni (Uang Kurang!)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        KeranjangPesanan keranjang3 = new KeranjangPesanan();
        keranjang3.tambahItem(new ItemPesanan(kopiSusu,    2));
        keranjang3.tambahItem(new ItemPesanan(pisangBakar, 2));

        Transaksi trx3 = new Transaksi(keranjang3);
        kasir1.tampilRingkasanTransaksi(trx3, true);

        // Bayar tunai — uang kurang, akan melempar PembayaranKurangException
        PembayaranTunai bayarTunaiKurang = new PembayaranTunai("PAY-003", 30_000);
        boolean berhasil3 = kasir1.prosesTransaksi(trx3, bayarTunaiKurang);
        System.out.println("Status proses: " + (berhasil3 ? "BERHASIL" : "GAGAL"));

        // =====================================================================
        // BAGIAN 6 — TRANSAKSI 4: Overloading validasiPembayaran + Retry (Kasir 1)
        // Mas Doni coba lagi dengan pesan kustom, lalu ganti ke OVO
        // =====================================================================
        System.out.println("\n\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  TRANSAKSI 4 — Pelanggan: Mas Doni (Retry + Pesan Kustom)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        KeranjangPesanan keranjang4 = new KeranjangPesanan();
        keranjang4.tambahItem(new ItemPesanan(kopiSusu,    2));
        keranjang4.tambahItem(new ItemPesanan(pisangBakar, 2));

        Transaksi trx4 = new Transaksi(keranjang4);
        double totalTrx4 = trx4.hitungTotalTagihan();
        System.out.println("Total tagihan: Rp " + String.format("%,.0f", totalTrx4));

        // Demonstrasi overloading validasiPembayaran(total, pesanKustom) — tunai
        PembayaranTunai bayarTunaiKustom = new PembayaranTunai("PAY-004", 50_000);
        System.out.println("Mencoba tunai Rp 50.000 dengan pesan kustom...");
        try {
            bayarTunaiKustom.validasiPembayaran(
                totalTrx4,
                "Maaf, uang kurang. Silakan tambah atau ganti metode pembayaran."
            );
        } catch (PembayaranKurangException e) {
            System.out.println("[EXCEPTION] " + e.getMessage());
            System.out.println("Kekurangan: Rp " + String.format("%,.0f", e.getJumlahKurang()));
        }

        // Mas Doni akhirnya bayar pakai OVO
        System.out.println("\nMas Doni memutuskan bayar dengan OVO...");
        PembayaranEWallet bayarOvo = new PembayaranEWallet("PAY-004B", totalTrx4, "OVO", "0857-9988-1122");
        boolean berhasil4 = kasir1.prosesTransaksi(trx4, bayarOvo);
        System.out.println("Status proses: " + (berhasil4 ? "BERHASIL" : "GAGAL"));

        // =====================================================================
        // BAGIAN 7 — TRANSAKSI 5: E-Wallet Gagal + Pesan Kustom + Retry (Kasir 2)
        // Demonstrasi overloading pada PembayaranEWallet
        // =====================================================================
        System.out.println("\n\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  TRANSAKSI 5 — Pelanggan: Bu Tini (Shift Siang, Kasir 2)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        KeranjangPesanan keranjang5 = new KeranjangPesanan();
        keranjang5.tambahItem(new ItemPesanan(matchaLatte,  2));
        keranjang5.tambahItem(new ItemPesanan(rotiBakar,    1));
        keranjang5.tambahItem(new ItemPesanan(pisangBakar,  1));

        Transaksi trx5 = new Transaksi(keranjang5);
        kasir2.tampilRingkasanTransaksi(trx5, true);

        double totalTrx5 = trx5.hitungTotalTagihan();

        // Overloading validasiPembayaran e-wallet dengan pesan kustom
        PembayaranEWallet danaSaldo = new PembayaranEWallet("PAY-005", 40_000, "DANA", "0821-5566-7788");
        System.out.println("Mencoba DANA saldo Rp 40.000 (kurang) dengan pesan kustom...");
        try {
            danaSaldo.validasiPembayaran(totalTrx5, "Saldo DANA tidak cukup. Silakan top-up terlebih dahulu!");
        } catch (PembayaranKurangException e) {
            System.out.println("[EXCEPTION] " + e.getMessage());
            System.out.println("Kekurangan: Rp " + String.format("%,.0f", e.getJumlahKurang()));
        }

        // Bu Tini top-up dan coba lagi
        System.out.println("\nBu Tini top-up DANA, saldo sesuai tagihan...");
        danaSaldo.setJumlahBayar(totalTrx5);
        boolean berhasil5 = kasir2.prosesTransaksi(trx5, danaSaldo);
        System.out.println("Status proses: " + (berhasil5 ? "BERHASIL" : "GAGAL"));

        // =====================================================================
        // BAGIAN 8 — TRANSAKSI 6: Hapus Item dari Keranjang (Kasir 2)
        // Demonstrasi hapusItem() di KeranjangPesanan sebelum checkout
        // =====================================================================
        System.out.println("\n\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  TRANSAKSI 6 — Pelanggan: Pak Joko (Edit Pesanan)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        KeranjangPesanan keranjang6 = new KeranjangPesanan();
        keranjang6.tambahItem(new ItemPesanan(tehTarikDingin, 3));
        keranjang6.tambahItem(new ItemPesanan(kopiSusu,       1));
        keranjang6.tambahItem(new ItemPesanan(singkongKeju,   2));

        System.out.println("Keranjang awal Pak Joko:");
        keranjang6.tampilKeranjang();

        System.out.println("\nPak Joko membatalkan Kopi Susu (M003)...");
        keranjang6.hapusItem("M003");

        System.out.println("Keranjang setelah edit:");
        keranjang6.tampilKeranjang();

        Transaksi trx6 = new Transaksi(keranjang6);
        PembayaranTunai bayarTunai6 = new PembayaranTunai("PAY-006", 200_000);
        boolean berhasil6 = kasir2.prosesTransaksi(trx6, bayarTunai6);
        System.out.println("Status proses: " + (berhasil6 ? "BERHASIL" : "GAGAL"));

        // =====================================================================
        // BAGIAN 9 — LAPORAN SHIFT KASIR 1 (Shift Pagi)
        // Tutup shift, tambahkan transaksi selesai, cetak laporan
        // =====================================================================
        System.out.println("\n\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  TUTUP SHIFT — KASIR 1: Budi Santoso (Shift Pagi)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        LaporanShift laporanKasir1 = kasir1.tutupShift();
        laporanKasir1.tambahTransaksi(trx1);  // selesai
        laporanKasir1.tambahTransaksi(trx2);  // selesai
        laporanKasir1.tambahTransaksi(trx3);  // GAGAL — tidak akan masuk
        laporanKasir1.tambahTransaksi(trx4);  // selesai
        laporanKasir1.cetak();

        // =====================================================================
        // BAGIAN 10 — LAPORAN SHIFT KASIR 2 (Shift Siang)
        // =====================================================================
        System.out.println("\n\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  TUTUP SHIFT — KASIR 2: Sari Dewi (Shift Siang)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        LaporanShift laporanKasir2 = kasir2.tutupShift();
        laporanKasir2.tambahTransaksi(trx5);  // selesai
        laporanKasir2.tambahTransaksi(trx6);  // selesai
        laporanKasir2.cetak();

        // =====================================================================
        // PENUTUP
        // =====================================================================
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║     Simulasi Selesai. Selamat Bertugas!     ║");
        System.out.println("╚══════════════════════════════════════════╝\n");
    }
}