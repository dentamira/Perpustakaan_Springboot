
package id.ac.ub.filkom.contoh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** Entity: objek data yang dipetakan ke tabel "buku". */
@Entity
@Table(name = "buku")
public class Buku {

    @Id
    @Column(name = "id_buku", length = 10, nullable = false)
    private String idBuku;

    @Column(name = "judul", length = 200, nullable = false)
    private String judul;

    @Column(name = "stok", nullable = false)
    private int stok;

    // Constructor untuk JPA
    protected Buku() {
    }

    // Constructor untuk membuat buku baru
    public Buku(String idBuku, String judul, int stok) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.stok = stok;
    }

    public String getIdBuku() {
        return idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public int getStok() {
        return stok;
    }

    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}