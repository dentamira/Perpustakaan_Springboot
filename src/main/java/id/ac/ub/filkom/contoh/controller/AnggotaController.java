
package id.ac.ub.filkom.contoh.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.ac.ub.filkom.contoh.service.AnggotaService;

@Controller
public class AnggotaController {

    private final AnggotaService anggotaService;

    public AnggotaController(AnggotaService anggotaService) {
        this.anggotaService = anggotaService;
    }

    // Menampilkan daftar anggota
    @GetMapping("/anggota")
    public String lihatAnggota(Model model) {
        model.addAttribute(
            "daftarAnggota",
            anggotaService.lihatSemuaAnggota()
        );
        return "anggota";
    }

    // Menampilkan form tambah anggota
    @GetMapping("/anggota/tambah")
    public String formTambahAnggota() {
        return "tambah-anggota";
    }

    // Menyimpan anggota baru
    @PostMapping("/anggota/tambah")
    public String tambahAnggota(
            @RequestParam String idAnggota,
            @RequestParam String nama,
            @RequestParam int batasPinjam,
            RedirectAttributes redirectAttributes) {

        if (idAnggota.isBlank()
                || nama.isBlank()
                || batasPinjam < 0) {
            redirectAttributes.addFlashAttribute(
                "error",
                "Data tidak valid. Periksa kembali isianmu."
            );
            return "redirect:/anggota/tambah";
        }

        try {
            anggotaService.tambahAnggota(
                idAnggota.trim(),
                nama.trim(),
                batasPinjam
            );

            redirectAttributes.addFlashAttribute(
                "pesan",
                "Anggota berhasil ditambahkan."
            );

            return "redirect:/anggota";

        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute(
                "error",
                "ID anggota sudah digunakan."
            );
            return "redirect:/anggota/tambah";
        }
    }
}
