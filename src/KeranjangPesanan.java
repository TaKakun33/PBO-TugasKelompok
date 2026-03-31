/* File       : KeranjangPesanan.java
 * Deskripsi  : Class yang Menyimpan kumpulan ItemPesanan  dan menyediakanoperasi tambah, hapus, serta perhitungan subtotal. Menggunakan
 *              Assertion untuk memastikan keranjang tidak kosong saat checkout.
 */

/******** Library ********/
import java.util.ArrayList;
import java.util.List;

/******** Class ********/
public class KeranjangPesanan {

    /******** Variable ********/
    private List<ItemPesanan> daftarItem;  

    /******** Method ********/

    /* Konstruktor */
    // Membuat keranjang pesanan baru dalam keadaan kosong
    public KeranjangPesanan() {
        this.daftarItem = new ArrayList<>();
    }

    /* Getter */
    // Mengambil seluruh daftar item pesanan
    public List<ItemPesanan> getDaftarItem() {
        return daftarItem;
    }

    /* Method Lainnya */
    // Menambahkan satu ItemPesanan ke dalam keranjang
    public void tambahItem(ItemPesanan item) {
        daftarItem.add(item);
    }

    // Menghapus item dari keranjang berdasarkan kodeItem
    public void hapusItem(String kodeItem) {
        for (int i = daftarItem.size() - 1; i >= 0; i--) {
            ItemPesanan item = daftarItem.get(i); 
            if (item.getMenuItem().getKodeItem().equals(kodeItem)) {
                daftarItem.remove(i); 
            }
        }
    }

    // Mengecek apakah keranjang dalam keadaan kosong
    public boolean isEmpty() {
        return daftarItem.size() == 0;
    }

    // Menghitung subtotal seluruh item sebelum pajak
    public double hitungSubtotal() {
        // Assertion: keranjang tidak boleh kosong saat menghitung subtotal
        assert !daftarItem.isEmpty() : "daftarItem tidak boleh kosong";
        double total = 0.0; 
        for (int i = 0; i < daftarItem.size(); i++) {
            ItemPesanan item = daftarItem.get(i); 
            total = total + item.getSubtotal();  
        }
        return total; 
    }

    // Menampilkan seluruh isi keranjang ke konsol
    public void tampilKeranjang() {
        if (daftarItem.isEmpty()) {
            System.out.println("(Keranjang kosong)");
        } else {
            for (int i = 0; i < daftarItem.size(); i++) {
                ItemPesanan item = daftarItem.get(i);
                System.out.println( item.getMenuItem().getNamaItem() + " x" + item.getQty()+ " = Rp " + String.format("%,.0f", item.getSubtotal()));
            }
        }
        
    }
}
