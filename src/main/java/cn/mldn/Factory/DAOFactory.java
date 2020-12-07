package cn.mldn.Factory;

import cn.mldn.dao.impl.EmpDAOImpl;
import cn.mldn.dao.IEmpDAO;

import java.sql.Connection;

/**
 * @Auther: henjie
 * @Date: 2020/12/7 - 12 - 07 - 13:41
 * @Description: cn.mldn.Factory  提供IEmpDAO的接口对象
 * @version: 1.0
 */
public class DAOFactory {
    public static IEmpDAO getIEMPDAOInstance(Connection conn){
        return new EmpDAOImpl(conn);
    }

}
