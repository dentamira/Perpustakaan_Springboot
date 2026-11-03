
package id.ac.ub.filkom.contoh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.ac.ub.filkom.contoh.service.AnggotaService;
import id.ac.ub.filkom.contoh.service.BukuService;
import id.ac.ub.filkom.contoh.service.PeminjamanService;

@Controller
public class PeminjamanController {

    private final PeminjamanService peminjamanService;
    private final BukuService bukuService;
    private final AnggotaService anggotaService;

    public PeminjamanController(
            PeminjamanService peminjamanService,
            BukuService bukuService,
            AnggotaService anggotaService) {

        this.peminjamanService = peminjamanService;
        this.bukuService = bukuService;
        this.anggotaService = anggotaService;
    }

    // Menampilkan daftar peminjaman
    @GetMapping("/peminjaman")
    public String daftarPeminjaman(Model model) {

        model.addAttribute(
            "daftarPeminjaman",
            peminjamanService.lihatSemuaPeminjaman()
        );

        return "peminjaman";
    }

    // Membuka formulir peminjaman
    @GetMapping("/peminjaman/tambah")
    public String formPeminjaman(Model model) {

        model.addAttribute(
            "daftarAnggota",
            anggotaService.lihatSemuaAnggota()
        );

        model.addAttribute(
            "daftarBuku",
            bukuService.cariBuku(null)
        );

        return "tambah-peminjaman";
    }

    // Menyimpan transaksi peminjaman
    @PostMapping("/peminjaman/tambah")
    public String tambahPeminjaman(
            @RequestParam String idAnggota,
            @RequestParam String idBuku,
            RedirectAttributes redirectAttributes) {

        try {
            peminjamanService.tambahPeminjaman(idAnggota, idBuku);

            redirectAttributes.addFlashAttribute(
                "pesan",
                "Peminjaman buku berhasil."
            );

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute(
                "error",
                e.getMessage()
            );

            return "redirect:/peminjaman/tambah";
        }

        return "redirect:/peminjaman";
    }

    // Memproses pengembalian buku
    @PostMapping("/peminjaman/{id}/kembalikan")
    public String kembalikanBuku(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        try {
            peminjamanService.kembalikanBuku(id);

            redirectAttributes.addFlashAttribute(
                "pesan",
                "Buku berhasil dikembalikan."
            );

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute(
                "error",
                e.getMessage()
            );
        }

        return "redirect:/peminjaman";
    }
}
