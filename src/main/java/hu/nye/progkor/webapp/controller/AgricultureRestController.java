package hu.nye.progkor.webapp.controller;

import java.util.List;

import hu.nye.progkor.webapp.model.Agriculture;
import hu.nye.progkor.webapp.service.AgriculturalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/agricultural-services")
public class AgricultureRestController {
    //CRUD

    private final AgriculturalService agriculturalService;

    public AgricultureRestController(final AgriculturalService agriculturalService) {
        this.agriculturalService = agriculturalService;
    }

    @GetMapping
    public List<Agriculture> getAllAgricultural() {
        return agriculturalService.getAllAgricultural();
    }

    @GetMapping("/{id}")
    Agriculture getAgriculture(final @PathVariable("id") Long id) {
        return agriculturalService.getAgriculture(id);
    }

    @PostMapping
    Agriculture createAgriculture(final @RequestBody Agriculture agriculture) {
        return agriculturalService.createAgriculture(agriculture);
    }

    @PutMapping("/{id}")
    Agriculture updateAgriculture(final @PathVariable Long id, final @RequestBody Agriculture agricultureChange) {
        return agriculturalService.updateAgriculture(id, agricultureChange);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAgriculture(final @PathVariable Long id) {
        agriculturalService.deleteAgriculture(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
