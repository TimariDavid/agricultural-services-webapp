package hu.nye.progkor.webapp.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hu.nye.progkor.webapp.model.Agriculture;
import hu.nye.progkor.webapp.model.Country;
import hu.nye.progkor.webapp.model.ServicesEnum;
import hu.nye.progkor.webapp.model.exception.NotFoundException;
import hu.nye.progkor.webapp.service.AgriculturalService;
import org.springframework.stereotype.Service;

@Service
public class AgriculturalServiceImpl implements AgriculturalService {

  private static final List<Agriculture> DATA_BASE = new ArrayList<>();

  static {
    DATA_BASE.add(new Agriculture(1L, "Nagy", "Levente",
            ServicesEnum.BETAKARÍTÁS, Country.HAJDÚ_BIHAR, "nagy.levente@gmail.com"));
    DATA_BASE.add(new Agriculture(2L, "Kis", "Imre",
            ServicesEnum.BÁLÁZÁS, Country.BARANYA, "kis.imre@gmail.com"));
  }

  @Override
  public List<Agriculture> getAllAgricultural() {
    return Collections.unmodifiableList(DATA_BASE);
  }

  @Override
  public Agriculture getAgriculture(final Long id) {
    return DATA_BASE.stream()
            .filter(agriculture -> agriculture.getId().equals(id))
            .findFirst()
            .orElseThrow(NotFoundException::new);
  }

  @Override
  public Agriculture createAgriculture(final Agriculture agriculture) {
    agriculture.setId(getNextId());
    DATA_BASE.add(agriculture);
    return agriculture;
  }

  @Override
  public Agriculture updateAgriculture(final Long id, final Agriculture agricultureChange) {
    final Agriculture agriculture = getAgriculture(id);
    agriculture.setFirstName(agricultureChange.getFirstName());
    agriculture.setLastName(agricultureChange.getLastName());
    agriculture.setServicesEnum(agricultureChange.getServicesEnum());
    agriculture.setCountry(agricultureChange.getCountry());
    agriculture.setEmail(agricultureChange.getEmail());
    return agriculture;
  }

  @Override
  public void deleteAgriculture(final Long id) {
    final Agriculture agriculture = getAgriculture(id);
    DATA_BASE.remove(agriculture);
  }

  private long getNextId() {
    return getLastId() + 1L;
  }

  private long getLastId() {
    return DATA_BASE.stream()
            .mapToLong(Agriculture::getId)
            .max()
            .orElse(0);
  }
}
