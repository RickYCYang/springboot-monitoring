package net.javaguides.springboot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Scope;
import co.elastic.apm.api.Span;


@Aspect
@Component
public class ElasticApmSpanAspect {

    /**
     * This aspect intercepts method calls in the service and repository layers and creates a span
     * for each method execution. It captures exceptions and ends the span after the method
     * execution.
     */

    public static final String SCOPES = "execution(* net.javaguides.springboot.service..*(..))"
            + " || execution(* net.javaguides.springboot.repository..*(..))";


    @Around(SCOPES)
    public Object traceMethod(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();

        Span span = ElasticApm.currentTransaction().startSpan("custom", "method", methodName)
                .setName(className + "." + methodName + "()");

        try (Scope scope = span.activate()) {
            return pjp.proceed(); // execute the function
        } catch (Throwable t) {
            span.captureException(t); // record the exception
            throw t;
        } finally {
            span.end(); // end the span
        }
    }
}
