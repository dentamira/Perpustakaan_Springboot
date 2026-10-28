
package id.ac.ub.filkom.contoh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "anggota")
public class Anggota {

    @Id
    @Column(name = "id_anggota", length = 10, nullable = false)
    private String idAnggota;

    @Column(name = "nama", length = 100, nullable = false)
    private String nama;

    @Column(name = "batas_pinjam", nullable = false)
    private int batasPinjam;

    // Constructor untuk JPA
    protected Anggota() {
    }

    // Constructor untuk membuat anggota baru
    public Anggota(String idAnggota, String nama, int batasPinjam) {
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.batasPinjam = batasPinjam;
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public String getNama() {
        return nama;
    }

    public int getBatasPinjam() {
        return batasPinjam;
    }

    public void setIdAnggota(String idAnggota) {
        this.idAnggota = idAnggota;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setBatasPinjam(int batasPinjam) {
        this.batasPinjam = batasPinjam;
    }
}