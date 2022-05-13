package hu.nye.progkor.webapp.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import hu.nye.progkor.webapp.model.Agriculture;
import hu.nye.progkor.webapp.model.Country;
import hu.nye.progkor.webapp.model.ServicesEnum;
import hu.nye.progkor.webapp.model.exception.NotFoundException;
import hu.nye.progkor.webapp.service.AgriculturalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AgriculturalServiceImplTest {

  private static final Agriculture FIRST_AGRICULTURE_SERVICE = new Agriculture(1L, "Nagy", "Levente", ServicesEnum.BETAKARÍTÁS, Country.HAJDÚ_BIHAR, "nagy.levente@gmail.com");
  private static final Agriculture SECOND_AGRICULTURE_SERVICE = new Agriculture(2L, "Kis", "Imre", ServicesEnum.BÁLÁZÁS, Country.BARANYA, "kis.imre@gmail.com");
  private static final List<Agriculture> AGRICULTURE_SERVICES = List.of(
          FIRST_AGRICULTURE_SERVICE,
          SECOND_AGRICULTURE_SERVICE
  );
  public static final long UNKNOWN_AGRICULTURE_SERVICES_ID = -1L;

  private AgriculturalService underTest;

  @BeforeEach
  void setUp() {
    underTest = new AgriculturalServiceImpl();
  }

  @Test
  void getAllAgriculturalServicesShouldReturnAllAgriculturalServices() {
    //when
    final List<Agriculture> result = underTest.getAllAgricultural();
    //then
    assertEquals(result, AGRICULTURE_SERVICES);
  }

  @Test
  void getAgriculturalServiceShouldReturnAgriculturalServiceWhenGivenIdOfExistingAgriculturalSercise() {
    //when
    final Agriculture result = underTest.getAgriculture(SECOND_AGRICULTURE_SERVICE.getId());
    //then
    assertEquals(result, SECOND_AGRICULTURE_SERVICE);
  }

  @Test
  void getAgriculturalServiceShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingAgriculturalService() {
    //when-then
    assertThrows(NotFoundException.class, () -> underTest.getAgriculture(UNKNOWN_AGRICULTURE_SERVICES_ID));
  }

  @Test
  void createAgriculturalServiceShouldReturnAgriculturalServiceWhenDelegateIt() {
    //given
    final Agriculture thirdAgriculture = new Agriculture(null, "Erős", "Ferenc", ServicesEnum.SZÁLLÍTÁS, Country.SZABOLCS_SZATMÁR_BEREG, "eros.ferenc@gmail.com");
    final Agriculture expectedThirdAgriculture = new Agriculture(3L, "Erős", "Ferenc", ServicesEnum.SZÁLLÍTÁS, Country.SZABOLCS_SZATMÁR_BEREG, "eros.ferenc@gmail.com");
    //when
    final Agriculture result = underTest.createAgriculture(thirdAgriculture);
    //then
    assertEquals(result, expectedThirdAgriculture);
    underTest.deleteAgriculture(thirdAgriculture.getId());
  }

  @Test
  void updateAgriculturalServiceShouldReturnUpdatedAgriculturalServiceWhenGivenIdOfExistingAgriculturalService() {
    //given
    final Agriculture thirdAgriculture = new Agriculture(null, "Erős", "Ferenc", ServicesEnum.SZÁLLÍTÁS, Country.SZABOLCS_SZATMÁR_BEREG, "eros.ferenc@gmail.com");
    final Agriculture expectedThirdAgriculture = new Agriculture(SECOND_AGRICULTURE_SERVICE.getId(), "Erős", "Ferenc", ServicesEnum.SZÁLLÍTÁS, Country.SZABOLCS_SZATMÁR_BEREG, "eros.ferenc@gmail.com");
    //when
    final Agriculture result = underTest.updateAgriculture(SECOND_AGRICULTURE_SERVICE.getId(), thirdAgriculture);
    //then
    assertEquals(result, expectedThirdAgriculture);
  }

  @Test
  void updateAgriculturalServiceShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingAgriculturalService() {
    //given
    final Agriculture thirdAgriculture = new Agriculture(null, "Erős", "Ferenc", ServicesEnum.SZÁLLÍTÁS, Country.SZABOLCS_SZATMÁR_BEREG, "eros.ferenc@gmail.com");
    //when-then
    assertThrows(NotFoundException.class, () -> underTest.updateAgriculture(UNKNOWN_AGRICULTURE_SERVICES_ID, thirdAgriculture));
  }

  @Test
  void deleteAgriculturalServiceShouldDeleteAgriculturalServiceWhenGivenIdOfAgriculturalService() {
    //given
    final List<Agriculture> expectedAgriculturalServices = List.of(SECOND_AGRICULTURE_SERVICE);
    //when
    underTest.deleteAgriculture(FIRST_AGRICULTURE_SERVICE.getId());
    final List<Agriculture> result = underTest.getAllAgricultural();
    //then
    assertEquals(result, expectedAgriculturalServices);
  }
}
