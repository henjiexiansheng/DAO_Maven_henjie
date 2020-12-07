package cn.mldn.dao.impl;

import cn.mldn.dao.IEmpDAO;
import cn.mldn.pojo.Emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Auther: henjie
 * @Date: 2020/12/6 - 12 - 06 - 15:08
 * @Description: cn.mldn.dao
 * @version: 1.0
 */
public class EmpDAOImpl implements IEmpDAO {
    private Connection conn;    //利用Connection的对象操作。
    private PreparedStatement prtmt;

    /***
     * 如果要想使用数据层进行原子性的功能操作实现，必须要提供有Connection接口对象
     * 另外，由于开发之中业务层要调用数据层，所以数据库的打开和关闭交由业务层处理
     * @param conn 表示数据库的连接对象。
     */
    public EmpDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int doCreate(Emp emp){
        String sql="INSERT INTO emp(EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) values (?,?,?,?,?,?,?,?)";
        try {
            this.prtmt = this.conn.prepareStatement(sql);
            this.prtmt.setInt(1,emp.getEMPNO());
            this.prtmt.setString(2,emp.getENAME());
            this.prtmt.setString(3,emp.getJOB());
            this.prtmt.setDouble(4,emp.getMGR());
            this.prtmt.setDate(5,new java.sql.Date(emp.getHIREDATE().getTime()));
            this.prtmt.setDouble(6,emp.getSAL());
            this.prtmt.setDouble(7,emp.getCOMM());
            this.prtmt.setInt(8,emp.getDEPTNO());
            return this.prtmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public int doUpdate(Emp emp){
        String sql="UPDATE emp SET ENAME=?,JOB=?,MGR=?,HIREDATE=?,SAL=?,COMM=?,DEPTNO=? WHERE EMPNO=?";
        try {
            this.prtmt = this.conn.prepareStatement(sql);
            this.prtmt.setString(1,emp.getENAME());
            this.prtmt.setString(2,emp.getJOB());
            this.prtmt.setDouble(3,emp.getMGR());
            this.prtmt.setDate(4,new java.sql.Date(emp.getHIREDATE().getTime()));
            this.prtmt.setDouble(5,emp.getSAL());
            this.prtmt.setDouble(6,emp.getCOMM());
            this.prtmt.setInt(7,emp.getDEPTNO());
            this.prtmt.setInt(8,emp.getEMPNO());
            return this.prtmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public int doRemoveBatch(Set<Integer> ids){
        if(ids == null || ids.size() == 0){ //没有要删除的数据
            return -1;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("DELETE FROM emp where EMPNO IN(");
        Iterator<Integer> iter = ids.iterator();    //对集合进行迭代的迭代器
        while(iter.hasNext()){  //iter.hasNext()如果仍有元素可以迭代，则返回 true。
            sql.append(iter.next()).append(",");    //返回迭代的下一个元素。
        }
        sql.delete(sql.length()-1,sql.length()).append(")");  //删除最后一个元素,然后追加一个右括号。
        try {
            this.prtmt = this.conn.prepareStatement(sql.toString()); //prepareStatement只能接受string，不能接受string buffer所以用.toString()转换为string。
            return prtmt.executeUpdate(); //如果删除的数据和要删除的数据相等，返回true。
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public Emp findById(Integer id){
        Emp vo = new Emp();
        String sql = "SELECT EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO FROM emp WHERE EMPNO = ?";
        try {
            this.prtmt = conn.prepareStatement(sql);
            this.prtmt.setInt(1,id);
            ResultSet resultSet = this.prtmt.executeQuery();
            while (resultSet.next())
            {
                vo.setEMPNO(resultSet.getInt(1));
                vo.setENAME(resultSet.getString(2));
                vo.setJOB(resultSet.getString(3));
                vo.setMGR(resultSet.getDouble(4));
                vo.setHIREDATE(resultSet.getDate(5));
                vo.setSAL((resultSet.getDouble(6)));
                vo.setCOMM(resultSet.getDouble(7));
                vo.setDEPTNO(resultSet.getInt(8));
                return vo;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Emp> findAll(){
        List<Emp> all = new ArrayList<Emp>();
        String sql = "SELECT EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO FROM emp";
        try {
            this.prtmt = this.conn.prepareStatement(sql);
            ResultSet resultSet = prtmt.executeQuery();
            while(resultSet.next()){
                Emp vo = new Emp();
                vo.setEMPNO(resultSet.getInt(1));
                vo.setENAME(resultSet.getString(2));
                vo.setJOB(resultSet.getString(3));
                vo.setMGR(resultSet.getDouble(4));
                vo.setHIREDATE(resultSet.getDate(5));
                vo.setSAL((resultSet.getDouble(6)));
                vo.setCOMM(resultSet.getDouble(7));
                vo.setDEPTNO(resultSet.getInt(8));
                all.add(vo);
            }
            return all;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Emp> findAllsplit(Integer currentPage, Integer lineSize, String column, String keyWord){
        List<Emp> all = new ArrayList<Emp>();
        String sql="select * from " +
                " (select EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO,ROWNUM rn" +
                " from emp" +
                " where "+column +" like ? and ROWNUM<=?) temp " +
                " where temp.rn>?";
        try {
            this.prtmt = this.conn.prepareStatement(sql);
            this.prtmt.setString(1,"%"+keyWord+"%");
            this.prtmt.setInt(2,currentPage*lineSize);
            this.prtmt.setInt(3,(currentPage-1)*lineSize);
            ResultSet resultSet = prtmt.executeQuery();
            while(resultSet.next()){
                Emp vo = new Emp();
                vo.setEMPNO(resultSet.getInt(1));
                vo.setENAME(resultSet.getString(2));
                vo.setJOB(resultSet.getString(3));
                vo.setMGR(resultSet.getDouble(4));
                vo.setHIREDATE(resultSet.getDate(5));
                vo.setSAL((resultSet.getDouble(6)));
                vo.setCOMM(resultSet.getDouble(7));
                vo.setDEPTNO(resultSet.getInt(8));
                all.add(vo);
            }
            return all;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord){
        String sql = "SELECT COUNT(EMPNO) FROM emp WHERE" + column + "LIKE ?";
        try {
            this.prtmt = conn.prepareStatement(sql);
            prtmt.setString(1,"%"+keyWord+"%");
            ResultSet resultSet = this.prtmt.executeQuery();
            if(resultSet.next())
            {
                return resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
