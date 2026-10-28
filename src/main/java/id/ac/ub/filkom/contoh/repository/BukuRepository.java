package id.ac.ub.filkom.contoh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import id.ac.ub.filkom.contoh.entity.Buku;

/** Repository untuk mengakses data buku di database. */
public interface BukuRepository extends JpaRepository<Buku, String> {

    // Mencari buku berdasarkan sebagian judul,
    // tanpa membedakan huruf besar dan kecil.
    List<Buku> findByJudulContainingIgnoreCase(String kata);
}
