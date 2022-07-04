package com.example.myapp.Aop;

import com.example.myapp.post.Post;
import com.example.myapp.user.User;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.aspectj.lang.JoinPoint; 
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private static Log LOGGER = LogFactory.getLog("Method_Logger");

    @Pointcut("execution(public * com.example.myapp.*.*.*(..))")
    public void allMethods(){}
    /* public haye package user shamel beshe va jahai ke @Log estefade mikonim. */
    
    @Around("allMethods()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        LOGGER.info(String.format(
                "Method %s is going call from class %s",
                joinPoint.getSignature().toString(),
                joinPoint.getTarget().getClass().getSimpleName()));

        Object object = joinPoint.proceed();

        LOGGER.info(String.format(
                "Method %s has called from class %s with return value type %s",
                joinPoint.getSignature().toString(),
                joinPoint.getTarget().getClass().getSimpleName(),
                object.getClass().getName() ));

        if (object instanceof User) LOGGER.info("Make User:" +
                "\nname:" + ((User) object).getName() +
                "\nEmail:"+((User) object).getEmail() +
                "\nbirthday:"+((User) object).getBirthday());

        if (object instanceof Post) LOGGER.info("Make Post:" +
                "\ntext:" + ((Post) object).getText() +
                "\nfrom id:"+((Post) object).getU_id().getEmail());

        return object;
    }

//    @AfterReturning(value = "allMethods() && @annotation(com.example.myapp.Aop.Log)",returning = "user")
//    public void returning(JoinPoint joinPoint,User user){
//        LOGGER.info("user save by Email: "+ user.getEmail() +
//                ", name: "+ user.getName()+
//                ", birthday: "+user.getBirthday());
//
//    }
//
//    @AfterThrowing(value = "allMethods() && @annotation(com.example.myapp.Aop.Log)",throwing = "e")
//    public void returning(JoinPoint joinPoint,Exception e){
//        LOGGER.info("throwing error:" + e.getMessage());
//    }
//
    //    @Around("allMethods()")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
//        LOGGER.info(String.format(
//                "Method %s is going call from class %s",
//                joinPoint.getSignature().toString(),
//                joinPoint.getTarget().getClass().getSimpleName() ));
//
//        Object returnObj = joinPoint.proceed(joinPoint.getArgs());
//
//        LOGGER.info(String.format(
//                "Method %s has called from class %s with return value type %s",
//                joinPoint.getSignature().toString(),
//                joinPoint.getTarget().getClass().getSimpleName(),
//                returnObj.getClass().getName() ));
//
//        return returnObj;
//    }
}
