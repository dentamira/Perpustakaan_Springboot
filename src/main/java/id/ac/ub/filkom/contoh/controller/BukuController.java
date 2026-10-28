
package id.ac.ub.filkom.contoh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.ac.ub.filkom.contoh.service.BukuService;

/** Controller: menerima request, memanggil service, dan memilih template. */
@Controller
public class BukuController {

    private final BukuService bukuService;

    public BukuController(BukuService bukuService) {
        this.bukuService = bukuService;
    }

    // Menampilkan daftar buku dan melakukan pencarian
    @GetMapping("/buku")
    public String daftarBuku(
            @RequestParam(required = false) String kata,
            Model model) {

        model.addAttribute("daftarBuku", bukuService.cariBuku(kata));
        model.addAttribute("kata", kata);

        return "buku";
    }

    // Menambahkan buku baru
    @PostMapping("/buku")
    public String tambahBuku(
            @RequestParam String idBuku,
            @RequestParam String judul,
            @RequestParam int stok,
            RedirectAttributes redirectAttributes) {

        if (idBuku.isBlank() || judul.isBlank() || stok < 0) {
            redirectAttributes.addFlashAttribute(
                "error",
                "Data tidak valid. Periksa kembali ID, judul, dan stok buku."
            );

            return "redirect:/buku";
        }

        bukuService.tambahBuku(idBuku.trim(), judul.trim(), stok);

        redirectAttributes.addFlashAttribute(
            "pesan",
            "Buku berhasil ditambahkan."
        );

        return "redirect:/buku";
    }
}