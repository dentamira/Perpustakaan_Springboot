
package id.ac.ub.filkom.contoh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import id.ac.ub.filkom.contoh.entity.Peminjaman;

public interface PeminjamanRepository
        extends JpaRepository<Peminjaman, Long> {

    List<Peminjaman> findByStatus(String status);

    List<Peminjaman> findByAnggota_IdAnggota(String idAnggota);

}
