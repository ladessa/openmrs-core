/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.validator;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openmrs.ConceptSource;
import org.openmrs.test.BaseContextSensitiveTest;
import org.openmrs.test.Verifies;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

/**
 *Tests methods on the {@link org.openmrs.validator.ConceptSourceValidator} class.
 */
public class ConceptSourceValidatorTest extends BaseContextSensitiveTest {
	
	@Test
	@Verifies(value = "should fail validation if name is null or empty or whitespace", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfNameIsNullOrEmptyOrWhitespace() throws Exception {
		ConceptSource conceptSource = new ConceptSource();
		conceptSource.setName(null);
		conceptSource.setDescription("Some description");
		
		Errors errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		conceptSource.setName("");
		errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		conceptSource.setName("   ");
		errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}

	/**
	 * @verifies fail validation if description is null or empty or whitespace
	 * @see ConceptSourceValidator#validate(Object, Errors)
	 */
	@Test
	public void validate_shouldFailValidationIfDescriptionIsNullOrEmptyOrWhitespace() throws Exception {

		ConceptSource conceptSource = new ConceptSource();
		conceptSource.setName("New name");

		conceptSource.setDescription(null);
		Errors errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertTrue(errors.hasFieldErrors("description"));
		
		conceptSource.setDescription("");
		errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertTrue(errors.hasFieldErrors("description"));
		
		conceptSource.setDescription("   ");
		errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertTrue(errors.hasFieldErrors("description"));
	}
	
	@Test
	@Verifies(value = "should pass validation if HL7 Code is null or empty or whitespace", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfHl7CodeIsNullOrEmptyOrWhitespace() throws Exception {
		ConceptSource conceptSource = new ConceptSource();
		conceptSource.setName("New name");
		conceptSource.setDescription("Some description");
		conceptSource.setHl7Code(null);
		
		Errors errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertFalse(errors.hasFieldErrors("Hl7Code"));
		
		conceptSource.setHl7Code("");
		errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertFalse(errors.hasFieldErrors("Hl7Code"));
		
		conceptSource.setHl7Code("   ");
		errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertFalse(errors.hasFieldErrors("Hl7Code"));
	}
	
	@Test
	@Verifies(value = "should pass validation if all required fields have proper values", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfAllRequiredFieldsHaveProperValues() throws Exception {
		ConceptSource conceptSource = new ConceptSource();
		conceptSource.setName("New name");
		conceptSource.setDescription("Some description");
		
		Errors errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertFalse(errors.hasErrors());
	}
	
	@Test
	@Verifies(value = "should pass validation if field lengths are correct", method = "validate(Object,Errors)")
	public void validate_shouldPassValidationIfFieldLengthsAreCorrect() throws Exception {
		ConceptSource conceptSource = new ConceptSource();
		conceptSource.setName("New name");
		conceptSource.setDescription("Some description");
		conceptSource.setHl7Code("Hl7Code");
		conceptSource.setRetireReason("RetireReason");
		
		Errors errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertFalse(errors.hasErrors());
	}
	
	@Test
	@Verifies(value = "should fail validation if field lengths are not correct", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfFieldLengthsAreNotCorrect() throws Exception {
		ConceptSource conceptSource = new ConceptSource();
		conceptSource.setName(StringUtils.repeat("a", 51));
		conceptSource.setDescription(StringUtils.repeat("a", 1025));
		conceptSource.setHl7Code(StringUtils.repeat("a", 51));
		conceptSource.setUniqueId(StringUtils.repeat("a", 251));
		conceptSource.setRetireReason(StringUtils.repeat("a", 256));
		Errors errors = new BindException(conceptSource, "conceptSource");
		new ConceptSourceValidator().validate(conceptSource, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		Assert.assertTrue(errors.hasFieldErrors("description"));
		Assert.assertTrue(errors.hasFieldErrors("hl7Code"));
		Assert.assertTrue(errors.hasFieldErrors("uniqueId"));
		Assert.assertTrue(errors.hasFieldErrors("retireReason"));
	}
}
