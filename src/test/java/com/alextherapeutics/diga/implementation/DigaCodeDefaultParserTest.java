/*
 * Copyright 2021-2021 Alex Therapeutics AB and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

package com.alextherapeutics.diga.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.alextherapeutics.diga.DigaCodeValidationException;
import com.alextherapeutics.diga.DigaHealthInsuranceDirectory;
import com.alextherapeutics.diga.model.DigaBillingInformation;
import com.alextherapeutics.diga.model.generatedxml.codevalidation.KrankenkasseninformationCtp;
import java.math.BigInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DigaCodeDefaultParserTest {
  private DigaHealthInsuranceDirectory healthInsuranceDirectory;
  private DigaCodeDefaultParser parser;

  @BeforeEach
  void init() {
    healthInsuranceDirectory = Mockito.mock(DigaHealthInsuranceDirectory.class);
    parser = new DigaCodeDefaultParser(healthInsuranceDirectory);
  }

  @Test
  void testParseCodeForvalidationThrowsOnInvalidCodeLength() {
    assertThrows(DigaCodeValidationException.class, () -> parser.parseCodeForValidation(null));
    assertThrows(DigaCodeValidationException.class, () -> parser.parseCodeForValidation(""));
    assertThrows(DigaCodeValidationException.class, () -> parser.parseCodeForValidation("AAAX"));
    assertThrows(
        DigaCodeValidationException.class,
        () -> parser.parseCodeForValidation("AAAAAAAAAAAAAAAAAX"));
  }

  @Test
  void testParseCodeForValidation() throws DigaCodeValidationException {
    var krank = createKrankenKasseInfo();
    Mockito.when(healthInsuranceDirectory.getInformation(Mockito.anyString())).thenReturn(krank);
    var code = "BH1234567890000X";
    parser.parseCodeForValidation(code);
    Mockito.verify(healthInsuranceDirectory, Mockito.times(1)).getInformation("BH");
  }

  @Test
  void testParseCodeForBilling() throws DigaCodeValidationException {
    var krank = createKrankenKasseInfo();
    Mockito.when(healthInsuranceDirectory.getInformation(Mockito.anyString())).thenReturn(krank);
    var code = "BH1234567890000X";
    parser.parseCodeForBilling(code);
    Mockito.verify(healthInsuranceDirectory, Mockito.times(1)).getInformation("BH");
  }

  @Test
  void testParseCodeForBillingForCompanyWithoutPostalInfo() throws DigaCodeValidationException {
    var krank = createKrankenKasseInfo();
    krank.setOrt(null);
    krank.setPLZ(null);
    krank.setStrassePostfach(null);
    krank.setHausnummerPostfachnummer(null);
    Mockito.when(healthInsuranceDirectory.getInformation(Mockito.anyString())).thenReturn(krank);
    var resp = parser.parseCodeForBilling("BH1234567890000X");
    assertEquals(DigaBillingInformation.INFORMATION_MISSING, resp.getBuyerCompanyAddressLine());
    assertEquals(DigaBillingInformation.INFORMATION_MISSING, resp.getBuyerCompanyPostalCode());
    assertEquals(DigaBillingInformation.INFORMATION_MISSING, resp.getBuyerCompanyCity());
  }

  private KrankenkasseninformationCtp createKrankenKasseInfo() {
    var krank = new KrankenkasseninformationCtp();
    krank.setEndpunktKommunikationsstelle("dum");
    krank.setIKAbrechnungsstelle("dum");
    krank.setIKDesRechnungsempfaengers("dum");
    krank.setKostentraegerkennung("dum");
    krank.setNameDesKostentraegers("dum");
    krank.setPLZ("dum");
    krank.setStrassePostfach("dum");
    krank.setHausnummerPostfachnummer("dum");
    krank.setOrt("dum");
    krank.setVersandart(BigInteger.ONE);
    return krank;
  }
}
