
package id.ac.ub.filkom.contoh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.repository.AnggotaRepository;

/** Service: logika untuk fitur anggota. */
@Service
public class AnggotaService {

    private final AnggotaRepository anggotaRepository;

    public AnggotaService(AnggotaRepository anggotaRepository) {
        this.anggotaRepository = anggotaRepository;
    }

    // Mengambil seluruh data anggota
    public List<Anggota> lihatSemuaAnggota() {
        return anggotaRepository.findAll();
    }

    // Menambahkan anggota baru
    public Anggota tambahAnggota(
            String idAnggota,
            String nama,
            int batasPinjam) {

        if (idAnggota == null || idAnggota.isBlank()) {
            throw new IllegalArgumentException(
                "ID anggota tidak boleh kosong."
            );
        }

        if (idAnggota.trim().length() > 10) {
            throw new IllegalArgumentException(
                "ID anggota maksimal 10 karakter."
            );
        }

        if (nama == null || nama.isBlank()) {
            throw new IllegalArgumentException(
                "Nama anggota tidak boleh kosong."
            );
        }

        if (nama.trim().length() > 100) {
            throw new IllegalArgumentException(
                "Nama anggota maksimal 100 karakter."
            );
        }

        if (batasPinjam < 0) {
            throw new IllegalArgumentException(
                "Batas pinjam tidak boleh negatif."
            );
        }

        String id = idAnggota.trim();

        if (anggotaRepository.existsById(id)) {
            throw new IllegalArgumentException(
                "ID anggota sudah terdaftar."
            );
        }

        Anggota anggota = new Anggota(
            id,
            nama.trim(),
            batasPinjam
        );

        return anggotaRepository.save(anggota);
    }
}