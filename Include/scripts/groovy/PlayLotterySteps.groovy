import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import org.openqa.selenium.support.ui.Select

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When

import com.kms.katalon.core.webui.common.WebUiCommonHelper


class PlayLotterySteps {

	String lottery_amount
	String initial_balance

	@When ("I select Lottery menu entry")
	def accessTheLotteryPage() {

		WebUI.click(findTestObject('Object Repository/Homepage/button_casino_menu'))
		WebUI.click(findTestObject('Object Repository/Homepage/menu_entry_Lottery'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/LotteryPage/lottery_frame'))

		initial_balance = WebUI.getText(findTestObject('Object Repository/Homepage/button_balance'))
		println "Initial balance :" + initial_balance
	}

	@And ("I select to participate in (.*) draws")
	def selectNumberOfDraws(String number_of_draws) {

		TestObject select_draws_element = findTestObject('Object Repository/LotteryPage/select_number_of_draws')
		WebUI.selectOptionByValue(select_draws_element, number_of_draws, true)

		//get the lottery amount
		Select select = new Select(WebUiCommonHelper.findWebElement(select_draws_element, 30))
		String optionLabel = select.getFirstSelectedOption().getText()
		lottery_amount = optionLabel.substring(optionLabel.indexOf("€")+1, optionLabel.indexOf(")"))
		println "Lottery amount:" + lottery_amount
		
	}

	@And ("I click on BUY TICKETS button")
	def clickOnBuyTicketsButton() {

		WebUI.click(findTestObject('Object Repository/LotteryPage/button_Buy_Tickets'))
	}

	@Then ("The bet amount is deducted from the balance")
	def checkBalance() {

		String actual_balance_txt = WebUI.getText(findTestObject('Object Repository/Homepage/button_balance'))
		println "Actual balance :" + actual_balance_txt

		String expected_balance_txt = CustomKeywords.'utils.Financial_functions.calculateBalance'(initial_balance, "-"+lottery_amount)
		println "expected balance :" + expected_balance_txt

		WebUI.verifyMatch(actual_balance_txt, expected_balance_txt, false, FailureHandling.STOP_ON_FAILURE )
	}
}