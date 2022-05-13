package hu.nye.progkor.webapp.service;

import java.util.List;

import hu.nye.progkor.webapp.model.Agriculture;

public interface AgriculturalService {

    List<Agriculture> getAllAgricultural();

    Agriculture getAgriculture(Long id);

    Agriculture createAgriculture(Agriculture agriculture);

    Agriculture updateAgriculture(Long id, Agriculture agricultureChange);

    void deleteAgriculture(Long id);
}
