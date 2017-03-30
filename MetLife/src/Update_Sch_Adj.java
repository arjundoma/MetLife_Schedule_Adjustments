import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Update_Sch_Adj {

WebDriver Brow = new FirefoxDriver();
WebDriverWait wait= new WebDriverWait(Brow, 120);
Actions act = new Actions(Brow);
    
public void Login() throws InterruptedException{      
    Brow.manage().window().maximize();
    String instance_URL = "https://v3.vitechinc.com/mlqa/app";
    Brow.navigate().to(instance_URL);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id=username]")));
    WebElement uName = Brow.findElement(By.cssSelector("input[id=username]"));
    WebElement uPwd = Brow.findElement(By.cssSelector("input[id=password]"));
    WebElement logIn = Brow.findElement(By.cssSelector("button[title^=Log]"));
    uName.sendKeys("admin_user");
    uPwd.sendKeys("123456");
    logIn.click();
    wait.until(ExpectedConditions.titleIs("Home"));  
    Thread.sleep(2000);
}

public void Annuitant_Search(Integer SSN) throws InterruptedException{
Brow.findElement(By.cssSelector("input[id=searchBox]")).clear();
Brow.findElement(By.cssSelector("input[id=searchBox]")).sendKeys(Integer.toString(SSN));
Brow.findElement(By.cssSelector("button[id=searchButton]")).click();
wait.until(ExpectedConditions.titleIs("WFlows & Docs"));
wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=showOpenWf]"))); 
Brow.switchTo().defaultContent();
Brow.findElement(By.linkText("Benefits")).click();
wait.until(ExpectedConditions.titleIs("Pension"));
wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
WebElement PaymentSetUp_Tab = Brow.findElement(By.cssSelector("#tab_PensionPages_DisbursementSchedules>em"));
PaymentSetUp_Tab.click();
wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id=factoringViewCheckBox]")));
Brow.switchTo().defaultContent();
}

public void Apply_Cola_Checkbox_Payment_Components() throws InterruptedException{ 
WebElement payment_Components = Brow.findElement(By.linkText("Payment Components"));
    payment_Components.click();        
    Set<String>AllWindowHandles = Brow.getWindowHandles();
    String paymentStreamWindow = (String)AllWindowHandles.toArray()[0];
    String paymentComponents = (String)AllWindowHandles.toArray()[1];        
    Brow.switchTo().window(paymentComponents);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='PAYMENT_COMPONENTS_GRID']/tbody/tr[1]/td[30]/div/input")));
    Thread.sleep(2000);
    WebElement ApplyCola = Brow.findElement(By.xpath(".//*[@id='PAYMENT_COMPONENTS_GRID']/tbody/tr[1]/td[30]/div/input"));
    ApplyCola.click();
    Thread.sleep(1500);
    WebElement paymentComponents_OK = Brow.findElement(By.cssSelector("input[title=Ok]"));
    paymentComponents_OK.click();        
    Brow.switchTo().window(paymentStreamWindow);
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("$PicklistEntryField$1")));
    
}

public void Modify_Stream() throws InterruptedException{
		WebElement editAnnuitant = Brow.findElement(By.cssSelector("button[title=Edit]"));
        editAnnuitant.click();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("definitionsButton")));
        boolean gearVisibility = Brow.findElement(By.cssSelector("input[id^=scheduletype]")).isDisplayed();
              if(gearVisibility == true){}               
              else{
               WebElement payment_Form_Gear = Brow.findElement(By.cssSelector("img[id^=menu_]"));
               payment_Form_Gear.click();
               }                      
        Thread.sleep(2000);
        WebElement modify_Stream_Gear = Brow.findElement(By.cssSelector("img[id^=functionimg]"));
        act.moveToElement(modify_Stream_Gear).perform();
        act.click().perform();
        Thread.sleep(2000);
        WebElement modify_Stream = Brow.findElement(By.linkText("Modify Stream"));
        modify_Stream.click();
        WebElement ok_Modify_Stream = Brow.findElement(By.xpath("html/body/div[3]/form/div/div/div/div[2]/div[2]/div[5]/div[2]/div/div[1]/table/tbody/tr/td/div/div[2]/div[3]/div[1]/div[3]/table/tbody/tr[2]/td[1]/div/div[2]/table/tbody/tr[2]/td/input[1]"));
        ok_Modify_Stream.click();        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("$PicklistEntryField$1")));        
        WebElement inPay = Brow.findElement(By.name("$PicklistEntryField$1"));
        Select sel_InPay = new Select(inPay);
        sel_InPay.selectByVisibleText("In Pay");
        Thread.sleep(1000);
        WebElement status_Reason = Brow.findElement(By.cssSelector("select[id^=statusReasonCode]"));
        Select sel_status_Reason = new Select(status_Reason);
        sel_status_Reason.selectByIndex(0);
        Thread.sleep(1000);
}

public void Sch_Add_One_Time(String Calc_Type, String date, String Percentage_Type, Integer Percentage, Integer Dollar_Amount) throws InterruptedException, ParseException{  
String Add_Buttons = "input[id='add']";
java.util.List<WebElement> a  = Brow.findElements(By.cssSelector(Add_Buttons));
a.get(1).click();
        Set<String>AllWindowHandles = Brow.getWindowHandles();
        String window1 = (String)AllWindowHandles.toArray()[0];
        String window2 = (String)AllWindowHandles.toArray()[1];       
        Brow.switchTo().window(window2);        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[id^=frequencytype]")));                              
        WebElement sch_Adj_Frequency = Brow.findElement(By.cssSelector("select[id^=frequencytype]"));
        Select sel_Sch_Adj_Frequency = new Select(sch_Adj_Frequency);
        sel_Sch_Adj_Frequency.selectByIndex(2);        
        Brow.switchTo().activeElement().sendKeys(Keys.TAB);        
        WebElement sch_Adj_Pay_Date = Brow.switchTo().activeElement();
        sch_Adj_Pay_Date.sendKeys(Keys.BACK_SPACE);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date dateStr = formatter.parse(date);
        String formattedDate = formatter.format(dateStr);
        sch_Adj_Pay_Date.sendKeys(formattedDate);             
        sch_Adj_Pay_Date.sendKeys(Keys.TAB);
        WebElement calc_Type1 = Brow.switchTo().activeElement();
        Select sel_Calc_Type = new Select(calc_Type1);        
        Thread.sleep(1000);    
          if(Calc_Type == "Percentage" ||Calc_Type == "percentage" ){
        sel_Calc_Type.selectByVisibleText("Percentage");
        Brow.switchTo().activeElement().sendKeys(Keys.TAB);        
            WebElement schAdjPercent = Brow.switchTo().activeElement();
            schAdjPercent.sendKeys(Integer.toString(Percentage));        
            schAdjPercent.sendKeys(Keys.TAB);        
            WebElement sch_Adj_Percent_Type = Brow.switchTo().activeElement();
            Select sel_sch_Adj_Percent_Type = new Select(sch_Adj_Percent_Type);
            sel_sch_Adj_Percent_Type.selectByVisibleText(Percentage_Type);        
            Thread.sleep(1000); 
            System.out.println("One Time Adjustment with "+Percentage+"% is added successfully");
            }
          else{
         sel_Calc_Type.selectByVisibleText("Dollar Amount");
              Thread.sleep(1000);              
              Brow.switchTo().activeElement().sendKeys(Keys.TAB);              
              WebElement schAdjPercent = Brow.switchTo().activeElement();
              schAdjPercent.sendKeys(Integer.toString(Dollar_Amount));              
              Thread.sleep(1000);
              System.out.println("One Time Adjustment with "+Dollar_Amount+"$ is added successfully");
          }
        WebElement schAdjOk = Brow.findElement(By.cssSelector("input[title=Ok]"));
        schAdjOk.click();
        Brow.switchTo().window(window1);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id^=scheduletype]")));
}

public void Sch_Add_Single_Payment(String Calc_Type, String date, String Percentage_Type, Integer Percentage, Integer Dollar_Amount)throws InterruptedException, ParseException{
String Add_Buttons = "input[id='add']";
java.util.List<WebElement> a  = Brow.findElements(By.cssSelector(Add_Buttons));
a.get(1).click();
        Set<String>AllWindowHandles = Brow.getWindowHandles();
        String window1 = (String)AllWindowHandles.toArray()[0];
        String window2 = (String)AllWindowHandles.toArray()[1];
        Brow.switchTo().window(window2);        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[id^=frequencytype]")));                      
        WebElement sch_Adj_Frequency = Brow.findElement(By.cssSelector("select[id^=frequencytype]"));
        Select sel_Sch_Adj_Frequency = new Select(sch_Adj_Frequency);
        sel_Sch_Adj_Frequency.selectByVisibleText("Single Payment Adjustment");        
        Brow.switchTo().activeElement().sendKeys(Keys.TAB);        
        WebElement sch_Adj_Pay_Date = Brow.switchTo().activeElement();
        sch_Adj_Pay_Date.sendKeys(Keys.BACK_SPACE);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date dateStr = formatter.parse(date);
        String formattedDate = formatter.format(dateStr);
        sch_Adj_Pay_Date.sendKeys(formattedDate);              
        sch_Adj_Pay_Date.sendKeys(Keys.TAB);
        WebElement calc_Type = Brow.switchTo().activeElement();
        Select sel_Calc_Type = new Select(calc_Type);
        Thread.sleep(1000);    
        if(Calc_Type == "Percentage" ||Calc_Type == "percentage" ){
      sel_Calc_Type.selectByVisibleText("Percentage");
      Brow.switchTo().activeElement().sendKeys(Keys.TAB);        
          WebElement schAdjPercent = Brow.switchTo().activeElement();
          schAdjPercent.sendKeys(Integer.toString(Percentage));        
          schAdjPercent.sendKeys(Keys.TAB);        
          WebElement sch_Adj_Percent_Type = Brow.switchTo().activeElement();
          Select sel_sch_Adj_Percent_Type = new Select(sch_Adj_Percent_Type);
          sel_sch_Adj_Percent_Type.selectByVisibleText(Percentage_Type);        
          Thread.sleep(1000); 
          System.out.println("Single Payment Adjustment with "+Percentage+"% is added successfully");
          }
        else{
       sel_Calc_Type.selectByVisibleText("Dollar Amount");
            Thread.sleep(1000);              
            Brow.switchTo().activeElement().sendKeys(Keys.TAB);              
            WebElement schAdjPercent = Brow.switchTo().activeElement();
            schAdjPercent.sendKeys(Integer.toString(Dollar_Amount));              
            Thread.sleep(1000);
            System.out.println("Single Payment Adjustment with "+Dollar_Amount+"$ is added successfully");
        }
      WebElement schAdjOk = Brow.findElement(By.cssSelector("input[title=Ok]"));
      schAdjOk.click();
      Brow.switchTo().window(window1);
      wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id^=scheduletype]")));
		}

		public void Sch_Add_Recurring(String Pay_Cycle, String Calc_Type, String date, String Percentage_Type, Integer Percentage, Integer Dollar_Amount) throws InterruptedException, ParseException{
		String Add_Buttons = "input[id='add']";
		java.util.List<WebElement> a  = Brow.findElements(By.cssSelector(Add_Buttons));
		a.get(1).click();
        Set<String>AllWindowHandles = Brow.getWindowHandles();
        String window1 = (String)AllWindowHandles.toArray()[0];
        String window2 = (String)AllWindowHandles.toArray()[1];
        Brow.switchTo().window(window2);        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[id^=frequencytype]")));                      
        WebElement sch_Adj_Frequency3 = Brow.findElement(By.cssSelector("select[id^=frequencytype]"));
        Select sel_Sch_Adj_Frequency3 = new Select(sch_Adj_Frequency3);
        sel_Sch_Adj_Frequency3.selectByVisibleText("Recurring Benefit Adjustment");
        WebElement sch_Adj_Cycle3 = Brow.findElement(By.cssSelector("select[id^=paycycletype]"));
        Select sel_Sch_Adj_Cycle3 = new Select(sch_Adj_Cycle3);
        
        if(Pay_Cycle=="Monthly"){        
        sel_Sch_Adj_Cycle3.selectByVisibleText("Monthly");
        Thread.sleep(2000);
        WebElement sch_Adj_Cycle_Start_Date3 = Brow.findElement(By.cssSelector("input[id^=cyclestartdate][class^=mediumSmallInput]"));
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date dateStr = formatter.parse(date);
        String formattedDate = formatter.format(dateStr);        
        sch_Adj_Cycle_Start_Date3.sendKeys(Keys.BACK_SPACE);
        sch_Adj_Cycle_Start_Date3.sendKeys(formattedDate);
        Thread.sleep(1000);
        sch_Adj_Cycle_Start_Date3.sendKeys(Keys.TAB);
        Brow.switchTo().activeElement().sendKeys("1");     
        WebElement sch_Day3 = Brow.findElement(By.cssSelector("select[name=DAY_OF_MONTH]"));
        Select sel_Sch_Day3 = new Select(sch_Day3);
        sel_Sch_Day3.selectByVisibleText("1st");
        }
        else{
        sel_Sch_Adj_Cycle3.selectByVisibleText("Semi- Annual");
 
        WebElement sch_Adj_Cycle_Start_Date3 = Brow.findElement(By.cssSelector("input[id^=cyclestartdate][class^=mediumSmallInput]"));
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date dateStr = formatter.parse(date);
        String formattedDate = formatter.format(dateStr);        
        sch_Adj_Cycle_Start_Date3.sendKeys(Keys.BACK_SPACE);
        sch_Adj_Cycle_Start_Date3.sendKeys(formattedDate);
        Thread.sleep(1000);
        Brow.switchTo().activeElement().sendKeys(Keys.TAB);
        WebElement Day1 = Brow.switchTo().activeElement();
        Day1.sendKeys(Keys.BACK_SPACE);
        Day1.sendKeys("01/01/2013");
        Brow.switchTo().activeElement().sendKeys(Keys.TAB);
        WebElement Day2 = Brow.switchTo().activeElement();
        Day2.sendKeys(Keys.BACK_SPACE);
        Day2.sendKeys("07/01/2013");        
 
        }  
        WebElement sch_Adj_Pay_Until3 = Brow.findElement(By.cssSelector("select[id^=payuntil]"));
        Select sel_Sch_Adj_Pay_Until3 = new Select(sch_Adj_Pay_Until3);
        sel_Sch_Adj_Pay_Until3.selectByVisibleText("Death");
        WebElement calc_Type3 = Brow.findElement(By.cssSelector("select[id^=calctype]"));
        Select sel_Calc_Type3 = new Select(calc_Type3);
          if(Calc_Type == "Percentage" ||Calc_Type == "percentage" ){
           sel_Calc_Type3.selectByVisibleText("Percentage");
           Thread.sleep(2000);
           Brow.switchTo().activeElement().sendKeys(Keys.TAB);
           WebElement schAdjPercent3 = Brow.switchTo().activeElement();
           schAdjPercent3.sendKeys(Integer.toString(Percentage));
           schAdjPercent3.sendKeys(Keys.TAB);
           WebElement sch_Adj_Percent_Type3 = Brow.switchTo().activeElement();
           Select sel_sch_Adj_Percent_Type3 = new Select(sch_Adj_Percent_Type3);
           sel_sch_Adj_Percent_Type3.selectByVisibleText(Percentage_Type);
           Thread.sleep(1000);
           System.out.println('\n'+"Recurring Adjustment with "+Percentage+"% is added successfully");
            }
          else{
           sel_Calc_Type3.selectByVisibleText("Dollar Amount");
           Thread.sleep(2000);
           Brow.switchTo().activeElement().sendKeys(Keys.TAB);
           WebElement schAdjPercent3 = Brow.switchTo().activeElement();  
           schAdjPercent3.sendKeys(Integer.toString(Dollar_Amount));
           Thread.sleep(1000);
           System.out.println('\n'+"Recurring Adjustment with "+Dollar_Amount+"$ is added successfully");
          }
        WebElement schAdjOk3 = Brow.findElement(By.cssSelector("input[title=Ok]"));
        schAdjOk3.click();
        Brow.switchTo().window(window1);        
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id^=scheduletype]")));
}

		public void Sch_Add_Cola_Percentage(String Pay_Cycle, String date, String Percentage_Type, Integer Percentage) throws InterruptedException, ParseException{
		String Add_Buttons = "input[id='add']";
   		java.util.List<WebElement> a  = Brow.findElements(By.cssSelector(Add_Buttons));
		a.get(1).click();
        Set<String>AllWindowHandles = Brow.getWindowHandles();
        String window1 = (String)AllWindowHandles.toArray()[0];
        String window2 = (String)AllWindowHandles.toArray()[1];
        Brow.switchTo().window(window2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[id^=frequencytype]")));     
        WebElement sch_Adj_Reason = Brow.findElement(By.cssSelector("select[id=adjustmentReasonCode]"));
        Select sel_sch_Adj_Reason = new Select(sch_Adj_Reason);
        sel_sch_Adj_Reason.selectByVisibleText("COLA Increase");
        Thread.sleep(2500);        
        WebElement COLA_Flat_Percent = Brow.findElement(By.xpath(".//*[@id='flatPercent']/td[2]/div/input"));
        COLA_Flat_Percent.sendKeys(Integer.toString(Percentage));        
        COLA_Flat_Percent.sendKeys(Keys.TAB);
        WebElement comm_COLA = Brow.switchTo().activeElement();
        Select sel_comm_COLA = new Select(comm_COLA);
        sel_comm_COLA.selectByIndex(1);
        Thread.sleep(1000);        
        comm_COLA.sendKeys(Keys.TAB);
        WebElement based_Month_Or_Day = Brow.switchTo().activeElement();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");
        Date dateStr = formatter.parse(date);
        String formattedDate = formatter.format(dateStr);        
        based_Month_Or_Day.sendKeys(Keys.BACK_SPACE);
        based_Month_Or_Day.sendKeys(formattedDate);
        Thread.sleep(1000);
        
        if(Pay_Cycle=="Monthly"){
        Brow.switchTo().activeElement().sendKeys(Keys.TAB);
        Brow.switchTo().activeElement().sendKeys(Keys.TAB);
        WebElement Freq_Of_Recurrence = Brow.switchTo().activeElement();
        Select Sel_Freq_Of_Recurrence = new Select(Freq_Of_Recurrence);
        Sel_Freq_Of_Recurrence.selectByVisibleText("Every X Months");
        Brow.switchTo().activeElement().sendKeys(Keys.TAB);
        WebElement Recurrence_X_Months = Brow.switchTo().activeElement();
        Recurrence_X_Months.sendKeys("6");
        Brow.switchTo().activeElement().sendKeys(Keys.TAB);
        }else{
        Brow.switchTo().activeElement().sendKeys(Keys.TAB);
        Brow.switchTo().activeElement().sendKeys(Keys.TAB);
        Brow.switchTo().activeElement().sendKeys(Keys.TAB);
        }
        WebElement COLA_End = Brow.switchTo().activeElement();
        Select sel_COLA_End = new Select(COLA_End);
        sel_COLA_End.selectByIndex(1);        
        WebElement COLA_Method = Brow.findElement(By.cssSelector("select[id=calcMethod]"));
        Select sel_COLA_Method = new Select(COLA_Method);
        sel_COLA_Method.selectByVisibleText(Percentage_Type);;        
        Thread.sleep(1000);
        WebElement schAdjOk1 = Brow.findElement(By.cssSelector("input[title=Ok]"));
        schAdjOk1.click();
        Brow.switchTo().window(window1);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id^=scheduletype]")));
        if(Pay_Cycle=="Monthly"){
        System.out.println('\n'+"Monthly COLA with "+Percentage+"% "+Percentage_Type+ " is added successfully");
        }else{
        System.out.println('\n'+"One Time COLA with "+Percentage+"% "+Percentage_Type+ " is added successfully");
        }
        
		}

	    public void Save_Annuitant() throws InterruptedException{
        Brow.switchTo().defaultContent();
        WebElement save = Brow.findElement(By.cssSelector("button[id=ecNewButton]"));
        save.click();        
        Brow.switchTo().frame("tapestry");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[class=confirmButton][value=Ok]")));
        WebElement clickOkPopUp = Brow.findElement(By.cssSelector("input[class=confirmButton][value=Ok]"));
        Thread.sleep(1000);
        clickOkPopUp.click();        
        Brow.switchTo().defaultContent();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[title=Edit]")));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("definitionsButton")));
        Brow.switchTo().defaultContent();
        System.out.print('\n'+"Annuitant is saved with the above added Schedule Adjustments");
}

public void Close_Browser(){
Brow.quit();
}

public static void main(String[] args) throws Exception {
Update_Sch_Adj t1 = new Update_Sch_Adj();
    t1.Login();
    
    
    t1.Annuitant_Search(574203018);
    t1.Modify_Stream();
    t1.Apply_Cola_Checkbox_Payment_Components(); 
    t1.Sch_Add_One_Time("Percentage", "05/01/2013", "Simple", 5, 100);
    t1.Sch_Add_Single_Payment("Dollar", "02/01/2013", "Simple", 10, 50);
    t1.Sch_Add_Recurring("Monthly", "Dollar", "01/01/2013","Compound", 10, 20);
    t1.Sch_Add_Cola_Percentage("Monthly", "01/01", "Simple", 2);
    t1.Save_Annuitant();
    
    
    /*
    //TC#110
    
    t1.Annuitant_Search(574203013);
    t1.Modify_Stream();
    t1.Sch_Add_Recurring("Semi-Annual", "Dollar", "01/01/2013", "", 5, 100);
    t1.Save_Annuitant();
    
    //TC#111
    t1.Annuitant_Search(574203014);
    t1.Modify_Stream();
    t1.Apply_Cola_Checkbox_Payment_Components();
    t1.Sch_Add_Cola_Percentage("Monthly", "01/01", "Compound", 9);
    t1.Save_Annuitant();
    
    t1.Annuitant_Search(574203015);
    t1.Modify_Stream();
    t1.Apply_Cola_Checkbox_Payment_Components();
    t1.Sch_Add_Cola_Percentage("One Time", "02/01", "Simple", 6);
    t1.Save_Annuitant();
    
    
    
    //TC#121
    
    t1.Annuitant_Search(574203016);
    t1.Modify_Stream();
    t1.Apply_Cola_Checkbox_Payment_Components(); 
    t1.Sch_Add_One_Time("Percentage", "05/01/2013", "Simple", 5, 100);
    t1.Sch_Add_Single_Payment("Dollar", "02/01/2013", "Simple", 10, 50);
    t1.Sch_Add_Recurring("Monthly", "Dollar", "01/01/2013","Compound", 10, 20);
    t1.Sch_Add_Cola_Percentage("Monthly", "01/01", "Simple", 2);
    t1.Save_Annuitant();
    
    
    //TC#122
    
    t1.Annuitant_Search(574203017);
    t1.Modify_Stream();
    t1.Apply_Cola_Checkbox_Payment_Components(); 
    t1.Sch_Add_Cola_Percentage("Monthly", "02/01", "Compound", 5);
    t1.Save_Annuitant();    
    */
    t1.Close_Browser();

}

}
