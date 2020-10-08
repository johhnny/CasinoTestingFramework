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
import cucumber.api.java.After

import io.cucumber.datatable.DataTable
import java.util.List




class RegisterNewUserSteps {

	@Given ("I navigate to casino url")
	def navigateToCasinoUrl() {

		WebUI.openBrowser('')

		WebUI.navigateToUrl(GlobalVariable.base_url)
		WebUI.maximizeWindow(FailureHandling.CONTINUE_ON_FAILURE)
	}

	@And ("I authenticate")
	def casinoUrlAuthentication() {

		WebUI.setEncryptedText(findTestObject('Object Repository/Authentication_Page/input_passw'), GlobalVariable.url_passw)

		WebUI.click(findTestObject('Object Repository/Authentication_Page/button_Authorize'))
	}

	@When ("I click on NEW USER button")
	def clickOnNewUserButton () {

		WebUI.click(findTestObject('Object Repository/LandingPage/button_New_User'))
	}

	@And ("I click on I GET IT, CONTINUE button")
	def clickOnContinueButton () {

		WebUI.click(findTestObject('Object Repository/LandingPage/button_Continue'))
	}

	@And ("I enter valid data")
	def enterData(DataTable table) {

		//Initialize data table
		List<Map<String, String>> data = table.asMaps(String.class, String.class);

		System.out.println(data);

		//generate an unique email if needed
		if (data[0].get("Value").equalsIgnoreCase("generate_an_unique_email"))
			WebUI.setText(findTestObject('Object Repository/LandingPage/RegisterForm/input_email_register'), CustomKeywords.'utils.Utils.generateUniqueEmail'() )
		else
			WebUI.setText(findTestObject('Object Repository/LandingPage/RegisterForm/input_email_register'), data[0].get("Value") )

		WebUI.click(findTestObject('Object Repository/LandingPage/RegisterForm/button_Next'))


		WebUI.setText(findTestObject('Object Repository/LandingPage/RegisterForm/input_country_phone_code'), data[1].get("Value"))

		WebUI.setText(findTestObject('Object Repository/LandingPage/RegisterForm/input_phone_number'), data[2].get("Value"))

		WebUI.click(findTestObject('Object Repository/LandingPage/RegisterForm/button_Next'))

		WebUI.setText(findTestObject('Object Repository/LandingPage/RegisterForm/input_full_name'), data[3].get("Value"))

		WebUI.click(findTestObject('Object Repository/LandingPage/RegisterForm/button_Next'))

		WebUI.setText(findTestObject('Object Repository/LandingPage/RegisterForm/input_password'), data[4].get("Value"))

		WebUI.click(findTestObject('Object Repository/LandingPage/RegisterForm/button_Next'))
	}

	@Then ("I get the message (.*) that the new user is registered")
	def userIsRegistered (String message) {

		String text = WebUI.getText(findTestObject('Object Repository/LandingPage/RegisterForm/registration_message'))
		message  = ".*" + message.replace('"', '') + ".*"
		WebUI.verifyMatch(text, message, true, FailureHandling.STOP_ON_FAILURE)
	}
}