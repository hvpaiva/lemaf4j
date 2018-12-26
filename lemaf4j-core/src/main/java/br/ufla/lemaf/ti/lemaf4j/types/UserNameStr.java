package br.ufla.lemaf.ti.lemaf4j.types;

import br.ufla.lemaf.ti.lemaf4j.validators.UserNameStrValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

/**
 * Anotation para o modelo com as regras do
 * VO de UserName.
 *
 * @author Highlander Paiva
 * @since 1.0
 */
@Documented
@Size(min = 3, max = 20)
@ReportAsSingleViolation
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UserNameStrValidator.class })
@Target({
        ElementType.METHOD,
        ElementType.PARAMETER,
        ElementType.FIELD,
        ElementType.ANNOTATION_TYPE
})
public @interface UserNameStr {

    String message() default "{br.ufla.lemaf.ti.lemaf4j.UserNameStr.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

