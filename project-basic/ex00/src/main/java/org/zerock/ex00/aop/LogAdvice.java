package org.zerock.ex00.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LogAdvice {

    //메서드의 앞 뒤에 동작하도록 하는 어노테이션 @Around
    @Around("execution(* org.zerock.ex00.service.*Service.*(..))")
    public Object logTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        //프로그램의 실행 전,후, 프로그램의 실행 자체를 제어한다.

        long start = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();//예외 던짐
        log.info("Result: {} ", result);

        long end = System.currentTimeMillis();

        long gap = end - start;

        log.info("--------------");
        log.info(proceedingJoinPoint.getTarget());
        log.info(proceedingJoinPoint.getSignature());
        log.info("TIME:" + gap);

        if (gap > 100) {
            log.warn("-----------WARN-----------");
        }
        log.info("--------------");
        return result;
    }
}
