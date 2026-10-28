
package id.ac.ub.filkom.contoh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import id.ac.ub.filkom.contoh.entity.Anggota;

/** Akses tabel anggota. */
public interface AnggotaRepository
        extends JpaRepository<Anggota, String> {
}