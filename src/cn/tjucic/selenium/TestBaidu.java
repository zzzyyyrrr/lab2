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
                             
        //生成CsvReader对象，以，为分隔符，GBK编码方式
        CsvReader r = new CsvReader("C://Users//Administrator//Desktop//大三下//软件工程//软件测试//软件测试名单2.csv", ',',Charset.forName("GBK"));
        //读取表头
        r.readHeaders();
        //逐条读取记录，直至读完
       
     
        
        while (r.readRecord()) {                        
        //读取一条记录
        	
        
        //按列名读取这条记录的值
        String xuhao_csv=r.get("序号");
        String number_csv = r.get("学号");              
        String name_csv = r.get("姓名");
        String address_csv = r.get("git地址");
        System.out.println("xuehao：" +xuhao_csv+number_csv+name_csv+address_csv ); 
        if(number_csv.length()==0)
        	continue;
        
        String pwd_csv = number_csv.substring(number_csv.length()-6,number_csv.length());
        System.out.println(pwd_csv); 
        //打开火狐浏览器
        String driverPath = System.getProperty("user.dir") + "/src/resources/driver/geckodriver.exe";
  	  System.setProperty("webdriver.gecko.driver", driverPath);
  	  WebDriver driver = new FirefoxDriver();
  	  
       //访问给定网址
       driver.get("http://121.193.130.195:8800");               
       driver.manage().window().maximize();
      //输入用户名
       WebElement input_name = driver.findElement(By.name("id"));
       input_name.clear();
       driver.findElement(By.name("id")).sendKeys(number_csv);
      //输入密码
       WebElement input_pwd = driver.findElement(By.name("password"));
       input_pwd.clear();
       driver.findElement(By.name("password")).sendKeys(pwd_csv);
      //点击登录按钮
       WebElement btn=driver.findElement(By.id("btn_login"));
       //btn.click();
       btn.sendKeys(Keys.ENTER);
       thread.sleep(1000);
     //登录成功之后，获得当前页面的用户信息
       String number_web = driver.findElement(By.id("student-id")).getText();
       System.out.println("xuehao：" +xuhao_csv+number_csv+name_csv+address_csv );
       String name_web = driver.findElement(By.id("student-name")).getText();
       String address_web = driver.findElement(By.id("student-git")).getText();
    //比较查询信息            
       if(name_csv.equals(name_web)&&number_csv.equals(number_web)&&address_csv.equals(address_web))
        {
                System.out.println("用户信息一致.");
        }
       else
        {
                System.out.println(name_web+"的信息不一致.");
        }
        driver.close();
         }
       r.close();
              
}
    
}