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

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.api.java.Before
import cucumber.api.java.After



class DepositAmountToUserAccountSteps {

	String deposit_amount_value
	String initial_balance


	@Given ("I am logged into casino")
	def LoginToCasino () {

		WebUI.callTestCase(findTestCase('Common/Login'), [:], FailureHandling.STOP_ON_FAILURE)
		
		//close ALL notifications, if present, as it will conflict with clicking on the casino menu
		CustomKeywords.'utils.Utils.closeAllNotifications'()
				
	}


	@When ("I click on the balance button")
	def clickOnBalanceButton () {

		initial_balance = WebUI.getText(findTestObject('Object Repository/Homepage/button_balance'))

		println "initial balance :" + initial_balance

		WebUI.click(findTestObject('Object Repository/Homepage/button_balance'))
	}


	@And ("I click on (.*) method")
	def clickOnCardButton(String method) {
		WebUI.click(findTestObject('Object Repository/Homepage/DepositForm/button_deposit_method' , [('deposit_method_name') : method]))
	}


	@And ("I select the desired deposit amount (.*)")
	def clickOnTheDesiredAmount(String amount) {

		WebUI.click(findTestObject('Homepage/DepositForm/button_deposit_value', [('value') : amount]))

		deposit_amount_value = amount
	}

	@And ("I click on Deposit Approved button")
	def clickOnDepositApprovedButton() {

		WebUI.click(findTestObject('Object Repository/Homepage/DepositForm/button_Deposit_Approved'))
	}

	@Then ("The confirmation (.*) is displayed")
	def checkDepositApprovedMessage(String message) {

		CustomKeywords.'utils.Utils.verifyMessage'(findTestObject('Object Repository/Homepage/DepositForm/deposit_message'), message)

		WebUI.click(findTestObject('Object Repository/Homepage/DepositForm/button_OK'))
	}

	@And ("The (.*) is showed")
	def checkNotificationMessage(String notification_message) {

		CustomKeywords.'utils.Utils.verifyMessage'(findTestObject('Object Repository/Homepage/NotificationForm/div_notification_message'), notification_message)
	}

	@And ("The amount is added to the account balance")
	def CheckNewBalance() {

		String actual_balance_txt = WebUI.getText(findTestObject('Object Repository/Homepage/button_balance'))
		println "actual balance :"+ actual_balance_txt

		String expected_balance_txt = CustomKeywords.'utils.Financial_functions.calculateBalance'(initial_balance, deposit_amount_value)
		println "expected balance :" + expected_balance_txt

		WebUI.verifyMatch(actual_balance_txt, expected_balance_txt, false, FailureHandling.STOP_ON_FAILURE )
	}
}