package com.example.labgics6.controller;

import com.example.labgics6.entity.Location;
import com.example.labgics6.entity.Site;
import com.example.labgics6.repository.LocationRepository;
import com.example.labgics6.repository.SiteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sitios")
public class sitiosController {

    final SiteRepository siteRepository;
    private final LocationRepository locationRepository;

    public sitiosController(SiteRepository siteRepository,
                            LocationRepository locationRepository) {
        this.siteRepository = siteRepository;
        this.locationRepository = locationRepository;
    }

    @GetMapping("/listaSitios")
    public String listarSitios(Model model) {

        List<Site> lista = siteRepository.findAll();
        model.addAttribute("listaSitios", lista);
        return "listaSitios";
    }

    @GetMapping("/new")
    public String nuevoSitio() {
        return "newSite";
    }

    @PostMapping("/save")
    public String guardarSitio(Site site, RedirectAttributes attr) {

        siteRepository.save(site);
        return "redirect:/sitios/listaSitios";
    }

    @GetMapping("/edit")
    public String editarSitio(Model model,
                                      @RequestParam("id") int id) {

        Optional<Site> optShipper = siteRepository.findById(id);
        List<Location> listaLocation = locationRepository.findAll();

        if (optShipper.isPresent()) {
            Site site = optShipper.get();
            model.addAttribute("site", site);
            model.addAttribute("listaLocaciones", listaLocation);
            
            return "editFrm";
        } else {
            return "redirect:/sitios/listaSitios";
        }
    }

    @GetMapping("/delete")
    public String borrarSitio(@RequestParam("id") int id, RedirectAttributes attributes) {

        Optional<Site> optShipper = siteRepository.findById(id);

        if (optShipper.isPresent()) {
            Site site = optShipper.get();
            String shipperName = site.getSiteName();
            siteRepository.deleteById(id);
            attributes.addFlashAttribute("msg", "Transportista " + shipperName + " borrado exitosamente");
        }
        return "redirect:/sitios/listaSitios";

    }



}
