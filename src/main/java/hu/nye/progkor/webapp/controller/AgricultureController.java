package hu.nye.progkor.webapp.controller;

import java.util.List;

import hu.nye.progkor.webapp.model.Agriculture;
import hu.nye.progkor.webapp.model.exception.NotFoundException;
import hu.nye.progkor.webapp.service.AgriculturalService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/agricultural-services")
public class AgricultureController {

  private static final String AGRICULTURE_SERVICES_LIST_TEMPLATE_NAME = "agriculture/list";
  private static final String AGRICULTURE_SERVICES_EDIT_TEMPLATE_NAME = "agriculture/edit";
  private static final String AGRICULTURE_SERVICES_ATTRIBUTE_NAME = "agriculture";

  private final AgriculturalService agriculturalService;

  public AgricultureController(final AgriculturalService agriculturalService) {
    this.agriculturalService = agriculturalService;
  }

  @GetMapping
  public String getAllAgriculture(final Model model) {
    final List<Agriculture> agriculture = agriculturalService.getAllAgricultural();
    model.addAttribute(AGRICULTURE_SERVICES_ATTRIBUTE_NAME, agriculture);
    return AGRICULTURE_SERVICES_LIST_TEMPLATE_NAME;
  }

  @GetMapping("/{id}")
  public String getAgriculture(final Model model, final @PathVariable Long id) {
    final Agriculture agriculture = agriculturalService.getAgriculture(id);
    model.addAttribute(AGRICULTURE_SERVICES_ATTRIBUTE_NAME, agriculture);
    return AGRICULTURE_SERVICES_EDIT_TEMPLATE_NAME;
  }

  @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String createAgriculture(final Model model,
                                  final @RequestParam(value = "id", required = false) Long id,
                                  final Agriculture agricultureChanges) {
    final Agriculture agriculture = agriculturalService.updateAgriculture(id, agricultureChanges);
    model.addAttribute(AGRICULTURE_SERVICES_ATTRIBUTE_NAME, agriculture);
    return AGRICULTURE_SERVICES_EDIT_TEMPLATE_NAME;
  }

  @PostMapping("/create")
  public String createAgriculture(final Model model, final Agriculture agriculture) {
    final Agriculture savedAgriculture = agriculturalService.createAgriculture(agriculture);
    model.addAttribute(AGRICULTURE_SERVICES_ATTRIBUTE_NAME, savedAgriculture);
    return AGRICULTURE_SERVICES_EDIT_TEMPLATE_NAME;
  }

  @GetMapping("/create")
  public String createAgricultureForm(final Model model) {
    return "agriculture/create";
  }


  @GetMapping("/{id}/delete")
  public String deleteAgriculture(final Model model, final @PathVariable("id") Long id) {
    try {
      agriculturalService.deleteAgriculture(id);
    } catch (NotFoundException e) {
      // Ignored
    }
    final List<Agriculture> agriculture = agriculturalService.getAllAgricultural();
    model.addAttribute(AGRICULTURE_SERVICES_ATTRIBUTE_NAME, agriculture);
    return AGRICULTURE_SERVICES_LIST_TEMPLATE_NAME;
  }
}
