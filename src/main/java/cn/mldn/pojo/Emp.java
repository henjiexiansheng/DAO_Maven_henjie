package cn.mldn.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @Auther: henjie
 * @Date: 2020/12/6 - 12 - 06 - 13:53
 * @Description: cn.mldn.vo
 * @version: 1.0
 * 数据库中的表，根据使用情况，需要转成java类，随用随转
 * 所有的表转成的java类都需要存放在vo下
 */
public class Emp implements Serializable {
    private Integer EMPNO;  //员工编号
    private String ENAME;   //员工名称
    private String JOB;     //工作
    private Double MGR;     //直属领导编号
    private Date HIREDATE;  //入职时间
    private Double SAL;     //工资
    private Double COMM;    //奖金
    private Integer DEPTNO; //部门号

    public Emp() {
    }

    public Emp(Integer EMPNO, String ENAME, String JOB, Double MGR, Date HIREDATE, Double SAL, Double COMM, Integer DEPTNO) {
        this.EMPNO = EMPNO;
        this.ENAME = ENAME;
        this.JOB = JOB;
        this.MGR = MGR;
        this.HIREDATE = HIREDATE;
        this.SAL = SAL;
        this.COMM = COMM;
        this.DEPTNO = DEPTNO;
    }

    public Integer getEMPNO() {
        return EMPNO;
    }

    public void setEMPNO(Integer EMPNO) {
        this.EMPNO = EMPNO;
    }

    public String getENAME() {
        return ENAME;
    }

    public void setENAME(String ENAME) {
        this.ENAME = ENAME;
    }

    public String getJOB() {
        return JOB;
    }

    public void setJOB(String JOB) {
        this.JOB = JOB;
    }

    public Double getMGR() {
        return MGR;
    }

    public void setMGR(Double MGR) {
        this.MGR = MGR;
    }

    public Date getHIREDATE() {
        return HIREDATE;
    }

    public void setHIREDATE(Date HIREDATE) {
        this.HIREDATE = HIREDATE;
    }

    public Double getSAL() {
        return SAL;
    }

    public void setSAL(Double SAL) {
        this.SAL = SAL;
    }

    public Double getCOMM() {
        return COMM;
    }

    public void setCOMM(Double COMM) {
        this.COMM = COMM;
    }

    public Integer getDEPTNO() {
        return DEPTNO;
    }

    public void setDEPTNO(Integer DEPTNO) {
        this.DEPTNO = DEPTNO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp emp = (Emp) o;
        return Objects.equals(EMPNO, emp.EMPNO) &&
                Objects.equals(ENAME, emp.ENAME) &&
                Objects.equals(JOB, emp.JOB) &&
                Objects.equals(MGR, emp.MGR) &&
                Objects.equals(HIREDATE, emp.HIREDATE) &&
                Objects.equals(SAL, emp.SAL) &&
                Objects.equals(COMM, emp.COMM) &&
                Objects.equals(DEPTNO, emp.DEPTNO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO);
    }

    @Override
    public String toString() {
        return "Emp{" +
                "EMPNO=" + EMPNO +
                ", ENAME='" + ENAME + '\'' +
                ", JOB='" + JOB + '\'' +
                ", MGR=" + MGR +
                ", HIREDATE=" + HIREDATE +
                ", SAL=" + SAL +
                ", COMM=" + COMM +
                ", DEPTNO=" + DEPTNO +
                '}';
    }
}
