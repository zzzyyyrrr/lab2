package cn.tjucic.selenium;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.*;
import com.csvreader.CsvReader;

public class TestBaidu {

	Thread thread = new Thread();
	 @Test
	  public void testBaidu() throws Exception {
                             
        //����CsvReader�����ԣ�Ϊ�ָ�����GBK���뷽ʽ
        CsvReader r = new CsvReader("C://Users//Administrator//Desktop//������//�������//�������//�����������2.csv", ',',Charset.forName("GBK"));
        //��ȡ��ͷ
        r.readHeaders();
        //������ȡ��¼��ֱ������
       
     
        
        while (r.readRecord()) {                        
        //��ȡһ����¼
        	
        
        //��������ȡ������¼��ֵ
        String xuhao_csv=r.get("���");
        String number_csv = r.get("ѧ��");              
        String name_csv = r.get("����");
        String address_csv = r.get("git��ַ");
        System.out.println("xuehao��" +xuhao_csv+number_csv+name_csv+address_csv ); 
        if(number_csv.length()==0)
        	continue;
        
        String pwd_csv = number_csv.substring(number_csv.length()-6,number_csv.length());
        System.out.println(pwd_csv); 
        //�򿪻�������
        String driverPath = System.getProperty("user.dir") + "/src/resources/driver/geckodriver.exe";
  	  System.setProperty("webdriver.gecko.driver", driverPath);
  	  WebDriver driver = new FirefoxDriver();
  	  
       //���ʸ�����ַ
       driver.get("http://121.193.130.195:8800");               
       driver.manage().window().maximize();
      //�����û���
       WebElement input_name = driver.findElement(By.name("id"));
       input_name.clear();
       driver.findElement(By.name("id")).sendKeys(number_csv);
      //��������
       WebElement input_pwd = driver.findElement(By.name("password"));
       input_pwd.clear();
       driver.findElement(By.name("password")).sendKeys(pwd_csv);
      //�����¼��ť
       WebElement btn=driver.findElement(By.id("btn_login"));
       //btn.click();
       btn.sendKeys(Keys.ENTER);
       thread.sleep(1000);
     //��¼�ɹ�֮�󣬻�õ�ǰҳ����û���Ϣ
       String number_web = driver.findElement(By.id("student-id")).getText();
       System.out.println("xuehao��" +xuhao_csv+number_csv+name_csv+address_csv );
       String name_web = driver.findElement(By.id("student-name")).getText();
       String address_web = driver.findElement(By.id("student-git")).getText();
    //�Ƚϲ�ѯ��Ϣ            
       if(name_csv.equals(name_web)&&number_csv.equals(number_web)&&address_csv.equals(address_web))
        {
                System.out.println("�û���Ϣһ��.");
        }
       else
        {
                System.out.println(name_web+"����Ϣ��һ��.");
        }
        driver.close();
         }
       r.close();
              
}
    
}