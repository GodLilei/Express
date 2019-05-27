package com.myproject.demo.config;

//import com.huan.service.*;
//import com.huan.util.DatabaseContextHolder;
//import com.huan.util.DatabaseType;
import com.myproject.demo.entity.Express;
import com.myproject.demo.utils.DatabaseContextHolder;
import com.myproject.demo.utils.DatabaseType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.myproject.demo.services.*;

@Aspect
@Component
public class DataSourceAspect {

    /**
     * 使用空方法定义切点表达式
     */
    @Pointcut("execution(* com.myproject.demo.services..*.*(..))")
    public void declareJointPointExpression() {
    }

    @Before("declareJointPointExpression()")
    public void setDataSourceKey(JoinPoint point) {
        //根据连接点所属的类实例，动态切换数据源
//        System.out.println("--------------------->>"+point.getTarget());
        if (point.getTarget() instanceof ExpressServices) {
            DatabaseContextHolder.setDatabaseType(DatabaseType.FRONTDB);
        }else if (point.getTarget() instanceof ExpressUatServices){
            DatabaseContextHolder.setDatabaseType(DatabaseType.FRONTNEW);
        }else if (point.getTarget() instanceof RookieXiadanServices){
            DatabaseContextHolder.setDatabaseType(DatabaseType.ROOKIE);
        } else if (point.getTarget() instanceof OldUatServices){
            DatabaseContextHolder.setDatabaseType(DatabaseType.OLDUAT);
        } else{
            DatabaseContextHolder.setDatabaseType(DatabaseType.mysql);
        }
    }
}