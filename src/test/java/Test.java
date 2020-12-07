import cn.mldn.Factory.ServiceFactory;
import cn.mldn.pojo.Emp;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Auther: henjie
 * @Date: 2020/12/7 - 12 - 07 - 19:36
 * @Description: PACKAGE_NAME
 * @version: 1.0
 */
public class Test {
    public static void main(String[] args) {
        String []first_name={"赵","钱","孙","李","周","吴","郑","王"};
        String []last_name={"憨憨","珏珏","亮亮","鑫鑫","gaygay"};

        for(int i=0;i<100;i++)
        {
            int EMPNO = new Random().nextInt(1000);
            Emp emp = new Emp();
            emp.setEMPNO(EMPNO);
            emp.setENAME(first_name[new Random().nextInt(8)]+last_name[new Random().nextInt(5)]);
            emp.setJOB("job"+EMPNO);
            emp.setMGR((double)(EMPNO+10));
            emp.setHIREDATE(new Date());
            emp.setSAL((double)(EMPNO+12));
            emp.setCOMM((double)(EMPNO+15));
            emp.setDEPTNO(10);
            try {
                ServiceFactory.getIEmpServiceInstance().insert(emp);
            } catch (Exception e){
                e.printStackTrace();
            }

        }

        try {
            List<Emp>list = ServiceFactory.getIEmpServiceInstance().list();
            for(Emp emp:list){
                System.out.println(emp.toString()+"\n");
            }
        } catch (Exception ignored){

        }
    }

}
