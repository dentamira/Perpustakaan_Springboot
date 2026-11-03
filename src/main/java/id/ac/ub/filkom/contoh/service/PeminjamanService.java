
package id.ac.ub.filkom.contoh.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.entity.Buku;
import id.ac.ub.filkom.contoh.entity.Peminjaman;
import id.ac.ub.filkom.contoh.repository.AnggotaRepository;
import id.ac.ub.filkom.contoh.repository.BukuRepository;
import id.ac.ub.filkom.contoh.repository.PeminjamanRepository;

@Service
public class PeminjamanService {

    private final PeminjamanRepository peminjamanRepository;
    private final AnggotaRepository anggotaRepository;
    private final BukuRepository bukuRepository;

    public PeminjamanService(
            PeminjamanRepository peminjamanRepository,
            AnggotaRepository anggotaRepository,
            BukuRepository bukuRepository) {

        this.peminjamanRepository = peminjamanRepository;
        this.anggotaRepository = anggotaRepository;
        this.bukuRepository = bukuRepository;
    }

    // Melihat seluruh transaksi peminjaman
    public List<Peminjaman> lihatSemuaPeminjaman() {
        return peminjamanRepository.findAll();
    }

    // Melakukan peminjaman buku
    @Transactional
    public Peminjaman tambahPeminjaman(
            String idAnggota,
            String idBuku) {

        Anggota anggota = anggotaRepository.findById(idAnggota)
                .orElseThrow(() ->
                    new IllegalArgumentException(
                        "Anggota tidak ditemukan."
                    ));

        Buku buku = bukuRepository.findById(idBuku)
                .orElseThrow(() ->
                    new IllegalArgumentException(
                        "Buku tidak ditemukan."
                    ));

        if (buku.getStok() <= 0) {
            throw new IllegalArgumentException(
                "Stok buku sedang habis."
            );
        }

        long jumlahDipinjam = peminjamanRepository
                .findByAnggota_IdAnggota(idAnggota)
                .stream()
                .filter(p -> "DIPINJAM".equals(p.getStatus()))
                .count();

        if (jumlahDipinjam >= anggota.getBatasPinjam()) {
            throw new IllegalArgumentException(
                "Anggota sudah mencapai batas peminjaman."
            );
        }

        buku.setStok(buku.getStok() - 1);
        bukuRepository.save(buku);

        Peminjaman peminjaman = new Peminjaman(
                anggota,
                buku,
                LocalDate.now()
        );

        return peminjamanRepository.save(peminjaman);
    }

    // Melakukan pengembalian buku
    @Transactional
    public Peminjaman kembalikanBuku(Long idPeminjaman) {

        Peminjaman peminjaman = peminjamanRepository
                .findById(idPeminjaman)
                .orElseThrow(() ->
                    new IllegalArgumentException(
                        "Data peminjaman tidak ditemukan."
                    ));

        if (!"DIPINJAM".equals(peminjaman.getStatus())) {
            throw new IllegalArgumentException(
                "Buku sudah dikembalikan."
            );
        }

        Buku buku = peminjaman.getBuku();

        buku.setStok(buku.getStok() + 1);
        bukuRepository.save(buku);

        peminjaman.setTanggalKembali(LocalDate.now());
        peminjaman.setStatus("DIKEMBALIKAN");

        return peminjamanRepository.save(peminjaman);
    }
}
