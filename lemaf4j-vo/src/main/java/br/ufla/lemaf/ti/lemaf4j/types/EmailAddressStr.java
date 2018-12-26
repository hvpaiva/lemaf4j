package br.ufla.lemaf.ti.lemaf4j.types;

import br.ufla.lemaf.ti.lemaf4j.validators.EmailAddressStrValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Anotation para o modelo com as regras do
 * VO de EmailAddress.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { EmailAddressStrValidator.class })
@Target({ ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
public @interface EmailAddressStr {

    String message() default "{br.ufla.lemaf.ti.lemaf4j.EmailAddressStr.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
