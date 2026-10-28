
package id.ac.ub.filkom.contoh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import id.ac.ub.filkom.contoh.entity.Buku;
import id.ac.ub.filkom.contoh.repository.BukuRepository;

/** Service: tempat logika bisnis/aturan aplikasi. */
@Service
public class BukuService {

    private final BukuRepository bukuRepository;

    // Dependency injection lewat constructor
    public BukuService(BukuRepository bukuRepository) {
        this.bukuRepository = bukuRepository;
    }

    // Melihat seluruh buku atau mencari berdasarkan judul
    public List<Buku> cariBuku(String kata) {
        if (kata == null || kata.isBlank()) {
            return bukuRepository.findAll();
        }

        return bukuRepository.findByJudulContainingIgnoreCase(
            kata.trim()
        );
    }

    // Menambahkan buku baru
    public Buku tambahBuku(String idBuku, String judul, int stok) {

        if (idBuku == null || idBuku.isBlank()) {
            throw new IllegalArgumentException(
                "ID buku tidak boleh kosong."
            );
        }

        if (idBuku.trim().length() > 10) {
            throw new IllegalArgumentException(
                "ID buku maksimal 10 karakter."
            );
        }

        if (judul == null || judul.isBlank()) {
            throw new IllegalArgumentException(
                "Judul buku tidak boleh kosong."
            );
        }

        if (judul.trim().length() > 200) {
            throw new IllegalArgumentException(
                "Judul buku maksimal 200 karakter."
            );
        }

        if (stok < 0) {
            throw new IllegalArgumentException(
                "Stok tidak boleh negatif."
            );
        }

        String id = idBuku.trim();

        if (bukuRepository.existsById(id)) {
            throw new IllegalArgumentException(
                "ID buku sudah terdaftar."
            );
        }

        Buku buku = new Buku(id, judul.trim(), stok);

        return bukuRepository.save(buku);
    }
}