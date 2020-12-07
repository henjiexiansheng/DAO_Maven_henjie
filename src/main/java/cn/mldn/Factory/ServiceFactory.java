package cn.mldn.Factory;

import cn.mldn.service.IEmpService;
import cn.mldn.service.impl.EmpServiceImpl;

/**
 * @Auther: henjie
 * @Date: 2020/12/7 - 12 - 07 - 16:19
 * @Description: cn.mldn.Factory
 * @version: 1.0
 */
public class ServiceFactory {
    public static IEmpService getIEmpServiceInstance(){
        return  new EmpServiceImpl();
    }
}
