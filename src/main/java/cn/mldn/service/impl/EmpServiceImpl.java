package cn.mldn.service.impl;

import cn.mldn.Factory.DAOFactory;
import cn.mldn.dbc.DatabaseConnection;
import cn.mldn.service.IEmpService;
import cn.mldn.pojo.Emp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: henjie
 * @Date: 2020/12/7 - 12 - 07 - 14:34
 * @Description: cn.mldn.service.impl
 * @version: 1.0
 */
public class EmpServiceImpl implements IEmpService {
    // 在这个类的对象内部就提供一个数据库连接类的实例化对象
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public int insert(Emp emp){
        try {
            // 要增加的雇员编号如果不存在，则findBuId返回的结果就是null，null表示可以进行新雇员数据的保存
            if (DAOFactory.getIEMPDAOInstance(this.dbc.getConnection()).findById(emp.getEMPNO()) == null){
                return DAOFactory.getIEMPDAOInstance(this.dbc.getConnection()).doCreate(emp);
            }
            return -1;
        } catch (Exception e){
            throw e ;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public int update(Emp emp){
        try {
            return  DAOFactory.getIEMPDAOInstance(this.dbc.getConnection()).doUpdate(emp);
        } catch (Exception e){
            throw e ;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public int delete(Set<Integer> ids){
        try {
            return  DAOFactory.getIEMPDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
        } catch (Exception e){
            throw e ;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Emp get(Integer ids){
        try {
            return  DAOFactory.getIEMPDAOInstance(this.dbc.getConnection()).findById(ids);
        } catch (Exception e){
            throw e ;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public List<Emp> list(){
        try {
            return DAOFactory.getIEMPDAOInstance(this.dbc.getConnection()).findAll();
        } catch (Exception e){
            throw e ;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord){
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("allEmps",DAOFactory.getIEMPDAOInstance(this.dbc.getConnection()).findAllsplit(currentPage,lineSize,column,keyWord));
            map.put("empCount",DAOFactory.getIEMPDAOInstance(this.dbc.getConnection()).getAllCount(column,keyWord));
            return  map;
        } catch (Exception e){
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
