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

public class MET_908 {

	WebDriver Brow = new FirefoxDriver();
	WebDriverWait wait = new WebDriverWait(Brow, 120);
	Actions act = new Actions(Brow);

	public void Login(String instance) throws InterruptedException {
		Brow.manage().window().maximize();
		// String instance_URL = "https://v3.vitechinc.com/mlqa/app";
		Brow.navigate().to(instance);
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

	public void Annuitant_Search(Integer SSN) throws InterruptedException {
		Brow.findElement(By.cssSelector("input[id=searchBox]")).clear();
		Brow.findElement(By.cssSelector("input[id=searchBox]")).sendKeys(Integer.toString(SSN));
		Brow.findElement(By.cssSelector("button[id=searchButton]")).click();
		wait.until(ExpectedConditions.titleIs("WFlows & Docs"));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=showOpenWf]")));
		Brow.switchTo().defaultContent();
		Thread.sleep(1500);
		Brow.findElement(By.linkText("Benefits")).click();
		wait.until(ExpectedConditions.titleIs("Pension"));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("#tab_PensionPages_DisbursementSchedules>em")));
		WebElement PaymentSetUp_Tab = Brow.findElement(By.cssSelector("#tab_PensionPages_DisbursementSchedules>em"));
		Thread.sleep(1500);
		PaymentSetUp_Tab.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id=factoringViewCheckBox]")));
		Brow.switchTo().defaultContent();
	}

	public void Apply_Cola_Checkbox_Payment_Components() throws InterruptedException {
		WebElement payment_Components = Brow.findElement(By.linkText("Payment Components"));
		payment_Components.click();
		Set<String> AllWindowHandles = Brow.getWindowHandles();
		String paymentStreamWindow = (String) AllWindowHandles.toArray()[0];
		String paymentComponents = (String) AllWindowHandles.toArray()[1];
		Brow.switchTo().window(paymentComponents);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("th[class^=APPLY_COLA_FLAG]")));
		Thread.sleep(2000);
		java.util.List<WebElement> c = Brow.findElements(By.cssSelector("input[class^=boolean]"));
		WebElement ApplyCola = c.get(1);
		ApplyCola.click();
		Thread.sleep(1500);
		WebElement paymentComponents_OK = Brow.findElement(By.cssSelector("input[title=Ok]"));
		paymentComponents_OK.click();
		Brow.switchTo().window(paymentStreamWindow);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("$PicklistEntryField$1")));

	}

	public void Modify_Stream() throws InterruptedException {
		WebElement editAnnuitant = Brow.findElement(By.cssSelector("button[title=Edit]"));
		editAnnuitant.click();
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("definitionsButton")));
		boolean gearVisibility = Brow.findElement(By.cssSelector("input[id^=scheduletype]")).isDisplayed();
		if (gearVisibility == true) {
		} else {
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
		Thread.sleep(1000);
		Brow.findElement(By.cssSelector("textarea[id^=STREAM_CHANGE_DESC]")).sendKeys("Comment1");
		Brow.switchTo().activeElement().sendKeys(Keys.TAB);
		WebElement ok_Modify_Stream = Brow.switchTo().activeElement();
		ok_Modify_Stream.click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id^=benefitaccount]")));
		java.util.List<WebElement> b = Brow.findElements(By.cssSelector("select[id^=status]"));
		WebElement inPay = b.get(0);
		Select sel_InPay = new Select(inPay);
		sel_InPay.selectByVisibleText("In Pay");
		Thread.sleep(1000);
		WebElement status_Reason = Brow.findElement(By.cssSelector("select[id^=statusReasonCode]"));
		Select sel_status_Reason = new Select(status_Reason);
		sel_status_Reason.selectByIndex(0);
		Thread.sleep(1000);
	}

	public void One_Time_Adjustment(String Calc_Type, String date, String Percentage_Type, Integer Percentage,
			Double Dollar_Amount) throws InterruptedException, ParseException {
		String Add_Buttons = "input[id='add']";
		java.util.List<WebElement> a = Brow.findElements(By.cssSelector(Add_Buttons));
		a.get(1).click();
		Set<String> AllWindowHandles = Brow.getWindowHandles();
		String window1 = (String) AllWindowHandles.toArray()[0];
		String window2 = (String) AllWindowHandles.toArray()[1];
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
		if (Calc_Type == "Percentage" || Calc_Type == "percentage") {
			sel_Calc_Type.selectByVisibleText("Percentage");
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			WebElement schAdjPercent = Brow.switchTo().activeElement();
			schAdjPercent.sendKeys(Integer.toString(Percentage));
			schAdjPercent.sendKeys(Keys.TAB);
			WebElement sch_Adj_Percent_Type = Brow.switchTo().activeElement();
			Select sel_sch_Adj_Percent_Type = new Select(sch_Adj_Percent_Type);
			sel_sch_Adj_Percent_Type.selectByVisibleText(Percentage_Type);
			Thread.sleep(1000);
			System.out.println('\n' + "One Time Adjustment with " + Percentage + "% is added successfully");
		} else {
			sel_Calc_Type.selectByVisibleText("Dollar Amount");
			Thread.sleep(1000);
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			WebElement schAdjPercent = Brow.switchTo().activeElement();
			schAdjPercent.sendKeys(Double.toString(Dollar_Amount));
			Thread.sleep(1000);
			System.out.println("One Time Adjustment with " + Dollar_Amount + "$ is added successfully");
		}
		WebElement schAdjOk = Brow.findElement(By.cssSelector("input[title=Ok]"));
		schAdjOk.click();
		Brow.switchTo().window(window1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id^=scheduletype]")));
		Thread.sleep(1500);
	}

	public void Single_Payment_Adjustment(String Calc_Type, String date, String Percentage_Type, Integer Percentage,
			Double Dollar_Amount) throws InterruptedException, ParseException {
		String Add_Buttons = "input[id='add']";
		java.util.List<WebElement> a = Brow.findElements(By.cssSelector(Add_Buttons));
		a.get(1).click();
		Set<String> AllWindowHandles = Brow.getWindowHandles();
		String window1 = (String) AllWindowHandles.toArray()[0];
		String window2 = (String) AllWindowHandles.toArray()[1];
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
		if (Calc_Type == "Percentage" || Calc_Type == "percentage") {
			sel_Calc_Type.selectByVisibleText("Percentage");
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			WebElement schAdjPercent = Brow.switchTo().activeElement();
			schAdjPercent.sendKeys(Integer.toString(Percentage));
			schAdjPercent.sendKeys(Keys.TAB);
			WebElement sch_Adj_Percent_Type = Brow.switchTo().activeElement();
			Select sel_sch_Adj_Percent_Type = new Select(sch_Adj_Percent_Type);
			sel_sch_Adj_Percent_Type.selectByVisibleText(Percentage_Type);
			Thread.sleep(1000);
			System.out.println('\n' + "Single Payment Adjustment with " + Percentage + "% is added successfully");
		} else {
			sel_Calc_Type.selectByVisibleText("Dollar Amount");
			Thread.sleep(1000);
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			WebElement schAdjPercent = Brow.switchTo().activeElement();
			schAdjPercent.sendKeys(Double.toString(Dollar_Amount));
			Thread.sleep(1000);
			System.out.println("Single Payment Adjustment with " + Dollar_Amount + "$ is added successfully");
		}
		WebElement schAdjOk = Brow.findElement(By.cssSelector("input[title=Ok]"));
		schAdjOk.click();
		Brow.switchTo().window(window1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id^=scheduletype]")));
		Thread.sleep(1500);
	}

	public void Recurring_Adjustment(String Pay_Cycle, String Calc_Type, String date, String Percentage_Type,
			Integer Percentage, Integer Dollar_Amount, String Pay_Until) throws InterruptedException, ParseException {
		String Add_Buttons = "input[id='add']";
		java.util.List<WebElement> a = Brow.findElements(By.cssSelector(Add_Buttons));
		a.get(1).click();
		Set<String> AllWindowHandles = Brow.getWindowHandles();
		String window1 = (String) AllWindowHandles.toArray()[0];
		String window2 = (String) AllWindowHandles.toArray()[1];
		Brow.switchTo().window(window2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[id^=frequencytype]")));
		WebElement sch_Adj_Frequency3 = Brow.findElement(By.cssSelector("select[id^=frequencytype]"));
		Select sel_Sch_Adj_Frequency3 = new Select(sch_Adj_Frequency3);
		sel_Sch_Adj_Frequency3.selectByVisibleText("Recurring Benefit Adjustment");
		WebElement sch_Adj_Cycle3 = Brow.findElement(By.cssSelector("select[id^=paycycletype]"));
		Select sel_Sch_Adj_Cycle3 = new Select(sch_Adj_Cycle3);

		if (Pay_Cycle == "Monthly") {
			sel_Sch_Adj_Cycle3.selectByVisibleText("Monthly");
			Thread.sleep(2000);
			WebElement sch_Adj_Cycle_Start_Date3 = Brow
					.findElement(By.cssSelector("input[id^=cyclestartdate][class^=mediumSmallInput]"));
			// SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			// Date dateStr = formatter.parse(date);
			// String formattedDate = formatter.format(dateStr);
			sch_Adj_Cycle_Start_Date3.sendKeys(Keys.BACK_SPACE);
			sch_Adj_Cycle_Start_Date3.sendKeys(date);
			Thread.sleep(1000);
			sch_Adj_Cycle_Start_Date3.sendKeys(Keys.TAB);
			Brow.switchTo().activeElement().sendKeys("1");
			WebElement sch_Day3 = Brow.findElement(By.cssSelector("select[name=DAY_OF_MONTH]"));
			Select sel_Sch_Day3 = new Select(sch_Day3);
			sel_Sch_Day3.selectByVisibleText("1st");
		} else if (Pay_Cycle == "Annual") {
			sel_Sch_Adj_Cycle3.selectByVisibleText("Annual");
			Thread.sleep(2000);
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			WebElement Cycle_Start_Date = Brow.switchTo().activeElement();
			Cycle_Start_Date.sendKeys(date);
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			WebElement Every_X_Year = Brow.switchTo().activeElement();
			Every_X_Year.sendKeys("1");
		} else {
			sel_Sch_Adj_Cycle3.selectByVisibleText("Semi- Annual");
			WebElement sch_Adj_Cycle_Start_Date3 = Brow
					.findElement(By.cssSelector("input[id^=cyclestartdate][class^=mediumSmallInput]"));
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
			Day2.sendKeys("06/01/2013");
		}

		WebElement sch_Adj_Pay_Until3 = Brow.findElement(By.cssSelector("select[id^=payuntil]"));
		Select sel_Sch_Adj_Pay_Until3 = new Select(sch_Adj_Pay_Until3);
		if (Pay_Until == "Death") {
			sel_Sch_Adj_Pay_Until3.selectByVisibleText(Pay_Until);
		} else {
			sel_Sch_Adj_Pay_Until3.selectByVisibleText("Death");
			Thread.sleep(1000);
			sel_Sch_Adj_Pay_Until3.selectByVisibleText(Pay_Until);
			Brow.switchTo().activeElement();
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			Brow.switchTo().activeElement().sendKeys("07252013");
		}

		WebElement calc_Type3 = Brow.findElement(By.cssSelector("select[id^=calctype]"));
		Select sel_Calc_Type3 = new Select(calc_Type3);
		if (Calc_Type == "Percentage" || Calc_Type == "percentage") {
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
			System.out.println('\n' + "Recurring Adjustment with " + Percentage + "% is added successfully");
		} else {
			sel_Calc_Type3.selectByVisibleText("Dollar Amount");
			Thread.sleep(2000);
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			WebElement schAdjPercent3 = Brow.switchTo().activeElement();
			schAdjPercent3.sendKeys(Integer.toString(Dollar_Amount));
			Thread.sleep(1000);
			System.out.println('\n' + "Recurring Adjustment with " + Dollar_Amount + "$ is added successfully");
		}
		WebElement schAdjOk3 = Brow.findElement(By.cssSelector("input[title=Ok]"));
		schAdjOk3.click();
		Brow.switchTo().window(window1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id^=scheduletype]")));
		Thread.sleep(1500);
	}

	public void Cola_Adjustment(String Pay_Cycle, Integer No_Of_Months, String date, String Percentage_Type,
			Integer Percentage) throws InterruptedException, ParseException {
		String Add_Buttons = "input[id='add']";
		java.util.List<WebElement> a = Brow.findElements(By.cssSelector(Add_Buttons));
		a.get(1).click();
		Set<String> AllWindowHandles = Brow.getWindowHandles();
		String window1 = (String) AllWindowHandles.toArray()[0];
		String window2 = (String) AllWindowHandles.toArray()[1];
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

		if (Pay_Cycle == "Monthly") {
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			WebElement Freq_Of_Recurrence = Brow.switchTo().activeElement();
			Select Sel_Freq_Of_Recurrence = new Select(Freq_Of_Recurrence);
			Sel_Freq_Of_Recurrence.selectByVisibleText("Every X Months");
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			WebElement Recurrence_X_Months = Brow.switchTo().activeElement();
			Recurrence_X_Months.sendKeys(Integer.toString(No_Of_Months));
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
		} else if (Pay_Cycle == "Annual") {
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			WebElement Freq_Of_Recurrence = Brow.switchTo().activeElement();
			Select Sel_Freq_Of_Recurrence = new Select(Freq_Of_Recurrence);
			Sel_Freq_Of_Recurrence.selectByVisibleText("Every X Years");
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			WebElement Recurrence_X_Months = Brow.switchTo().activeElement();
			Recurrence_X_Months.sendKeys("1");
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
		}

		else {
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
			Brow.switchTo().activeElement().sendKeys(Keys.TAB);
		}
		WebElement COLA_End = Brow.switchTo().activeElement();
		Select sel_COLA_End = new Select(COLA_End);
		sel_COLA_End.selectByIndex(1);
		WebElement COLA_Method = Brow.findElement(By.cssSelector("select[id=calcMethod]"));
		Select sel_COLA_Method = new Select(COLA_Method);
		sel_COLA_Method.selectByVisibleText(Percentage_Type);
		Thread.sleep(1000);
		WebElement schAdjOk1 = Brow.findElement(By.cssSelector("input[title=Ok]"));
		schAdjOk1.click();
		Brow.switchTo().window(window1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id^=scheduletype]")));
		if (Pay_Cycle == "Monthly") {
			System.out.println(
					'\n' + "Monthly COLA with " + Percentage + "% " + Percentage_Type + " is added successfully");
		} else {
			System.out.println(
					'\n' + "One Time COLA with " + Percentage + "% " + Percentage_Type + " is added successfully");
		}
		Thread.sleep(1500);
	}

	public void Index_Adjustment(String Index_Frequency, String Index_Start_Date, String Percentage_Type,
			String Index_Percentage) throws InterruptedException {
		String Add_Buttons = "input[id='add']";
		java.util.List<WebElement> a = Brow.findElements(By.cssSelector(Add_Buttons));
		a.get(1).click();
		Set<String> AllWindowHandles = Brow.getWindowHandles();
		String window1 = (String) AllWindowHandles.toArray()[0];
		String window2 = (String) AllWindowHandles.toArray()[1];
		Brow.switchTo().window(window2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("select[id^=frequencytype]")));
		WebElement sch_Adj_Reason = Brow.findElement(By.cssSelector("select[id=adjustmentReasonCode]"));
		Select sel_sch_Adj_Reason = new Select(sch_Adj_Reason);
		sel_sch_Adj_Reason.selectByVisibleText("Index Adjustment");
		Thread.sleep(2500);
		Brow.switchTo().activeElement().sendKeys(Keys.TAB);
		Brow.switchTo().activeElement().sendKeys(Keys.TAB);
		Brow.switchTo().activeElement().sendKeys(Keys.TAB);
		Brow.switchTo().activeElement().sendKeys(Index_Percentage);
		Brow.switchTo().activeElement().sendKeys(Keys.TAB);
		WebElement comm_Index = Brow.switchTo().activeElement();
		Select sel_comm_Index = new Select(comm_Index);
		sel_comm_Index.selectByIndex(1);
		Brow.switchTo().activeElement().sendKeys(Keys.TAB);
		Brow.switchTo().activeElement().sendKeys(Index_Start_Date);
		Brow.switchTo().activeElement().sendKeys(Keys.TAB);
		Brow.switchTo().activeElement().sendKeys(Keys.TAB);
		WebElement Freq_Of_Recurrence = Brow.switchTo().activeElement();
		Select Sel_Freq_Of_Recurrence = new Select(Freq_Of_Recurrence);
		Sel_Freq_Of_Recurrence.selectByVisibleText("Every X months");
		Brow.switchTo().activeElement().sendKeys(Keys.TAB);
		WebElement Recurrence_X_Months = Brow.switchTo().activeElement();
		Recurrence_X_Months.sendKeys("1");
		Brow.switchTo().activeElement().sendKeys(Keys.TAB);
		WebElement COLA_End = Brow.switchTo().activeElement();
		Select sel_COLA_End = new Select(COLA_End);
		sel_COLA_End.selectByIndex(1);
		WebElement COLA_Method = Brow.findElement(By.cssSelector("select[id=calcMethod]"));
		Select sel_COLA_Method = new Select(COLA_Method);
		sel_COLA_Method.selectByVisibleText(Percentage_Type);
		Thread.sleep(1000);
		WebElement schAdjOk1 = Brow.findElement(By.cssSelector("input[title=Ok]"));
		schAdjOk1.click();
		Brow.switchTo().window(window1);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[id^=scheduletype]")));
		Thread.sleep(1500);

	}

	public void Save_Annuitant() throws InterruptedException {
		Brow.switchTo().defaultContent();
		WebElement save = Brow.findElement(By.cssSelector("button[id=ecNewButton]"));
		save.click();
		Brow.switchTo().frame("tapestry");
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[class=confirmButton][value=Ok]")));
		WebElement clickOkPopUp = Brow.findElement(By.cssSelector("input[class=confirmButton][value=Ok]"));
		Thread.sleep(1000);
		clickOkPopUp.click();
		Brow.switchTo().defaultContent();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[title=Edit]")));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("tapestry"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("definitionsButton")));
		Brow.switchTo().defaultContent();
		System.out.print('\n' + "Annuitant is saved with the above added Schedule Adjustments");
		Thread.sleep(1500);
	}

	public void Close_Browser() {
		Brow.quit();
	}

	public static void main(String[] args) throws Exception {

		MET_908 t1 = new MET_908();
		t1.Login("https://v3.vitechinc.com/mltrunk/app");

		// TC#1
		t1.Annuitant_Search(523253001);
		System.out.println('\n' + "TC#1 - 523253001");
		t1.Modify_Stream();
		t1.Apply_Cola_Checkbox_Payment_Components();
		t1.One_Time_Adjustment("Dollar", "03/01/2015", "Simple", 5, -50.00);
		t1.Cola_Adjustment("Monthly", 1, "06/01", "Simple", 3);
		t1.One_Time_Adjustment("Dollar", "09/01/2015", "Simple", 5, 500.00);
		t1.Save_Annuitant();

		// TC#1 - Retro
		t1.Annuitant_Search(523253008);
		System.out.println('\n' + "TC#1 - Retro - 523253008");
		t1.Modify_Stream();
		t1.Apply_Cola_Checkbox_Payment_Components();
		t1.One_Time_Adjustment("Dollar", "03/01/2015", "Simple", 5, -50.00);
		t1.Cola_Adjustment("Monthly", 1, "06/01", "Simple", 3);
		t1.One_Time_Adjustment("Dollar", "09/01/2015", "Simple", 5, 500.00);
		t1.Save_Annuitant();

		// TC#2
		t1.Annuitant_Search(523253002);
		System.out.println('\n' + "TC#2 - 523253002");
		t1.Modify_Stream();
		t1.Apply_Cola_Checkbox_Payment_Components();
		t1.One_Time_Adjustment("Dollar", "03/01/2015", "Simple", 5, -200.00);
		t1.Cola_Adjustment("Monthly", 1, "06/01", "Simple", 3);
		t1.One_Time_Adjustment("Dollar", "09/01/2015", "Simple", 5, 500.00);
		t1.Recurring_Adjustment("Monthly", "Percentage", "10/01/2015", "Simple", 5, 100, "Death");
		t1.Save_Annuitant();

		// TC#2 - Retro
		t1.Annuitant_Search(523253009);
		System.out.println('\n' + "TC#2 - Retro - 523253009");
		t1.Modify_Stream();
		t1.Apply_Cola_Checkbox_Payment_Components();
		t1.One_Time_Adjustment("Dollar", "03/01/2015", "Simple", 5, -200.00);
		t1.Cola_Adjustment("Monthly", 1, "06/01", "Simple", 3);
		t1.One_Time_Adjustment("Dollar", "09/01/2015", "Simple", 5, 500.00);
		t1.Recurring_Adjustment("Monthly", "Percentage", "10/01/2015", "Simple", 5, 100, "Death");
		t1.Save_Annuitant();

		// TC#3
		t1.Annuitant_Search(523253003);
		System.out.println('\n' + "TC#3 - 523253003");
		t1.Modify_Stream();
		t1.Apply_Cola_Checkbox_Payment_Components();
		t1.One_Time_Adjustment("Dollar", "03/01/2015", "Simple", 5, -200.00);
		t1.Cola_Adjustment("Monthly", 1, "06/01", "Compound", 3);
		t1.One_Time_Adjustment("Dollar", "09/01/2015", "Simple", 5, 500.00);
		t1.Recurring_Adjustment("Monthly", "Percentage", "10/01/2015", "Simple", 5, 100, "Death");
		t1.Save_Annuitant();

		// TC#3 - Retro
		t1.Annuitant_Search(523253010);
		System.out.println('\n' + "TC#3 - Retro - 523253010");
		t1.Modify_Stream();
		t1.Apply_Cola_Checkbox_Payment_Components();
		t1.One_Time_Adjustment("Dollar", "03/01/2015", "Simple", 5, -200.00);
		t1.Cola_Adjustment("Monthly", 1, "06/01", "Compound", 3);
		t1.One_Time_Adjustment("Dollar", "09/01/2015", "Simple", 5, 500.00);
		t1.Recurring_Adjustment("Monthly", "Percentage", "10/01/2015", "Simple", 5, 100, "Death");
		t1.Save_Annuitant();

		// TC#4
		t1.Annuitant_Search(523253004);
		System.out.println('\n' + "TC#4 - 523253004");
		t1.Modify_Stream();
		t1.Apply_Cola_Checkbox_Payment_Components();
		t1.One_Time_Adjustment("Dollar", "03/01/2015", "Simple", 5, -200.00);
		t1.Index_Adjustment("Monthly", "0601", "Simple", "3");
		t1.One_Time_Adjustment("Dollar", "09/01/2015", "Simple", 5, 500.00);
		t1.Recurring_Adjustment("Monthly", "Percentage", "10/01/2015", "Simple", -5, 100, "Death");
		t1.Single_Payment_Adjustment("Percentage", "11/01/2015", "Simple", 2, 100.00);
		t1.Save_Annuitant();

		// TC#4 - Retro
		t1.Annuitant_Search(523253011);
		System.out.println('\n' + "TC#4 - Retro - 523253011");
		t1.Modify_Stream();
		t1.Apply_Cola_Checkbox_Payment_Components();
		t1.One_Time_Adjustment("Dollar", "03/01/2015", "Simple", 5, -200.00);
		t1.Index_Adjustment("Monthly", "0601", "Simple", "3");
		t1.One_Time_Adjustment("Dollar", "09/01/2015", "Simple", 5, 500.00);
		t1.Recurring_Adjustment("Monthly", "Percentage", "10/01/2015", "Simple", -5, 100, "Death");
		t1.Single_Payment_Adjustment("Percentage", "11/01/2015", "Simple", 2, 100.00);
		t1.Save_Annuitant();

		// TC#5
		t1.Annuitant_Search(523253005);
		System.out.println('\n' + "TC#5 - 523253005");
		t1.Modify_Stream();
		t1.Apply_Cola_Checkbox_Payment_Components();
		t1.One_Time_Adjustment("Dollar", "03/01/2015", "Simple", 5, -400.00);
		t1.Index_Adjustment("Monthly", "0601", "Compound", "2");
		t1.One_Time_Adjustment("Dollar", "09/01/2015", "Simple", 5, 800.00);
		t1.Recurring_Adjustment("Monthly", "Percentage", "10/01/2015", "Simple", 8, 100, "Death");
		t1.Save_Annuitant();

		// TC#5 - Retro
		t1.Annuitant_Search(523253012);
		System.out.println('\n' + "TC#5 - Retro - 523253012");
		t1.Modify_Stream();
		t1.Apply_Cola_Checkbox_Payment_Components();
		t1.One_Time_Adjustment("Dollar", "03/01/2015", "Simple", 5, -400.00);
		t1.Index_Adjustment("Monthly", "0601", "Compound", "2");
		t1.One_Time_Adjustment("Dollar", "09/01/2015", "Simple", 5, 800.00);
		t1.Recurring_Adjustment("Monthly", "Percentage", "10/01/2015", "Simple", 8, 100, "Death");
		t1.Save_Annuitant();

		// TC#6
		t1.Annuitant_Search(523253006);
		System.out.println('\n' + "TC#6 - 523253006");
		t1.Modify_Stream();
		t1.Apply_Cola_Checkbox_Payment_Components();
		t1.One_Time_Adjustment("Percentage", "03/01/2015", "Simple", -5, -400.00);
		t1.Cola_Adjustment("Monthly", 1, "06/01", "Simple", 3);
		t1.Recurring_Adjustment("Annual", "Dollar", "09/01/2015", "Simple", 8, 500, "Death");
		t1.Save_Annuitant();

		// TC#6 - Retro
		t1.Annuitant_Search(523253013);
		System.out.println('\n' + "TC#6 - Retro - 523253013");
		t1.Modify_Stream();
		t1.Apply_Cola_Checkbox_Payment_Components();
		t1.One_Time_Adjustment("Percentage", "03/01/2015", "Simple", -5, -400.00);
		t1.Cola_Adjustment("Monthly", 1, "06/01", "Simple", 3);
		t1.Recurring_Adjustment("Annual", "Dollar", "09/01/2015", "Simple", 8, 500, "Death");
		t1.Save_Annuitant();

		// TC#7
		t1.Annuitant_Search(523253007);
		System.out.println('\n' + "TC#7 - 523253007");
		t1.Modify_Stream();
		t1.Apply_Cola_Checkbox_Payment_Components();
		t1.One_Time_Adjustment("Dollar", "03/01/2015", "Simple", 5, 300.00);
		t1.Cola_Adjustment("Monthly", 1, "06/01", "Compound", 3);
		t1.Save_Annuitant();
		

		// TC#7 - Retro
		t1.Annuitant_Search(523253014);
		System.out.println('\n' + "TC#7 - Retro - 523253014");
		t1.Modify_Stream();
		t1.Apply_Cola_Checkbox_Payment_Components();
		t1.One_Time_Adjustment("Dollar", "03/01/2015", "Simple", 5, 300.00);
		t1.Cola_Adjustment("Monthly", 1, "06/01", "Compound", 3);
		t1.Save_Annuitant();

		t1.Close_Browser();

	}

}
