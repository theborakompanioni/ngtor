package org.tbk.ngtor.conditional;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * {@link Conditional @Conditional} that matches when a given command is executed.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Conditional(OnCommandCondition.class)
public @interface ConditionalOnCommand {

    /**
     * Alias for {@link #name()}.
     *
     * @return the names
     */
    String[] value() default {};

    /**
     * The name of the command to test.
     *
     * @return the names
     */
    String[] name() default {};

    /**
     * Specify if the condition should match if the command is not set. Defaults to
     * {@code false}.
     *
     * @return should match if the command is missing
     */
    boolean matchIfMissing() default false;

}
