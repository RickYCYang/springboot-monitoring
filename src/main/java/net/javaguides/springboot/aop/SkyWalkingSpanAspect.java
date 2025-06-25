package net.javaguides.springboot.aop;

import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SkyWalkingSpanAspect {

    public static final String SCOPES = "within(net.javaguides.springboot.controller..*)"
            + " || within(net.javaguides.springboot.service..*)"
            + " || within(net.javaguides.springboot.repository..*)";

    @Around(SCOPES)
    @Trace // SkyWalking 需要這個註解來啟用追蹤
    public Object traceMethod(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();

        ActiveSpan.tag("method", methodName);
        ActiveSpan.tag("class", className);

        try {
            return pjp.proceed();
        } catch (Throwable t) {
            ActiveSpan.error(t);
            throw t;
        }
    }
}
