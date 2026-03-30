/* File       : ItemPesanan.java
 * Deskripsi  : Class yang merepresentasikan satu baris item di dalam KeranjangPesanan.
 */

/******** Class ********/
public class ItemPesanan {

    /******** Variable ********/
    private MenuItem menuItem; 
    private int qty;

    /******** Method ********/

    /* Konstruktor */
    // Membuat objek ItemPesanan tanpa parameter
    public ItemPesanan() {}

    // Membuat objek ItemPesanan dengan parameter
    public ItemPesanan(MenuItem menuItem, int qty) {
        this.menuItem = menuItem;
        this.qty = qty;
    }

    /* Getter */
    // Mengambil MenuItem yang dipesan
    public MenuItem getMenuItem() {
        return menuItem;
    }

    // Mengambil jumlah qty
    public int getQty() {
        return qty;
    }

    /* Setter */
    // Mengeset MenuItem
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    // Mengeset qty
    public void setQty(int qty) {
        this.qty = qty;
    }

    /* Method Lainnya */
    // Menghitung subtotal harga untuk item ini (harga x qty)
    public double getSubtotal() {
        return menuItem.getHarga() * qty;
    }
}
