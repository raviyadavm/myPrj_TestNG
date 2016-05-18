package myPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoanPage {
	WebDriver driver;
	
	@FindBy(name="param[homevalue]")
	WebElement homeValue;
	
	@FindBy(id="loanamt")
	WebElement loanAmt;
	
	@FindBy(id="intrstsrate")
	WebElement intRate;
	
	@FindBy(name="cal")
	WebElement calc;
	
	@FindBy(xpath=".//*[@id='calc']/form/section/section[2]/div/div/div[1]/div/div/div[3]/div[2]/div[2]/div[1]/div[1]/h3")
	WebElement emi;
	
	public LoanPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void EnterLoanDetails(double strHome, double strLoan, double strInt){
		homeValue.clear();
		homeValue.sendKeys(Double.toString(strHome));
		loanAmt.clear();
		loanAmt.sendKeys(Double.toString(strLoan));
		intRate.clear();
		intRate.sendKeys(Double.toString(strInt));
	}
	
	public void clickCalc(){
		calc.click();
	}
	
	public String getEMI(){
		return emi.getText();
	}
}
