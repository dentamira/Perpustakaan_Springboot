
package id.ac.ub.filkom.contoh.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "peminjaman")
public class Peminjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeminjaman;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_anggota", nullable = false)
    private Anggota anggota;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_buku", nullable = false)
    private Buku buku;

    @Column(name = "tanggal_pinjam", nullable = false)
    private LocalDate tanggalPinjam;

    @Column(name = "tanggal_kembali")
    private LocalDate tanggalKembali;

    @Column(name = "status", nullable = false)
    private String status;

    protected Peminjaman() {
    }

    public Peminjaman(Anggota anggota, Buku buku, LocalDate tanggalPinjam) {
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.status = "DIPINJAM";
    }

    public Long getIdPeminjaman() {
        return idPeminjaman;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public LocalDate getTanggalKembali() {
        return tanggalKembali;
    }

    public String getStatus() {
        return status;
    }

    public void setTanggalKembali(LocalDate tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
