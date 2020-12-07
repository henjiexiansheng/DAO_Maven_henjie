import cn.mldn.Factory.ServiceFactory;
import cn.mldn.pojo.Emp;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Auther: henjie
 * @Date: 2020/12/7 - 12 - 07 - 16:35
 * @Description: PACKAGE_NAME  测试只需调用业务层方法
 * @version: 1.0
 */
public class TestEmpInsert {
    public static void main(String[] args){
        for(int i=0;i<100;i++)
        {
            int EMPNO = new Random().nextInt(1000);  //创建一个伪随机数，数值介于0到1000之间
            Emp vo = new Emp();
            vo.setEMPNO(EMPNO);
            vo.setENAME("henjie"+EMPNO);
            vo.setJOB("job"+EMPNO);
            vo.setMGR((double)(EMPNO+10));
            vo.setHIREDATE(new Date());
            vo.setSAL((double)(EMPNO+12));
            vo.setCOMM((double)(EMPNO+15));
            vo.setDEPTNO(10);
            try {
                ServiceFactory.getIEmpServiceInstance().insert(vo);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        try {
            List<Emp> list = ServiceFactory.getIEmpServiceInstance().list();  //查询全部雇员信息
            for(Emp emp:list)
            {
                System.out.println(emp.getEMPNO());
                System.out.println(emp.getENAME());
                System.out.println(emp.getJOB());
                System.out.println(emp.getMGR());
                System.out.println(emp.getHIREDATE());
                System.out.println(emp.getSAL());
                System.out.println(emp.getCOMM());
                System.out.println(emp.getDEPTNO());
            }
        } catch (Exception ignored){

        }
    }

}
