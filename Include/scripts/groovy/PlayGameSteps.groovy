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


class PlayGameSteps {

	String bet_amount
	String initial_balance

	@When ("I select Casino menu entry")
	def accessTheCasinoGamesPage() {

		WebUI.click(findTestObject('Object Repository/Homepage/button_casino_menu'))
		WebUI.click(findTestObject('Object Repository/Homepage/menu_entry_Casino'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/GameCatPage/game_frame'))

		initial_balance = WebUI.getText(findTestObject('Object Repository/Homepage/button_balance'))
	}

	@And ("I set the Bet value to (.*)")
	def setBetValue(String value) {

		WebUI.selectOptionByValue(findTestObject('Object Repository/GameCatPage/select_bet_value'), value, true)

		bet_amount = value
	}

	@And ("I click on one of the cats")
	def clickOnCat() {
		WebUI.click(findTestObject('Object Repository/GameCatPage/game_option_cat'))
	}


	@Then ("My balance updates accordingly in case I win or I loose")
	def checkBalance() {

		String actual_balance_txt = WebUI.getText(findTestObject('Object Repository/Homepage/button_balance'))
		println "Actual balance :" + actual_balance_txt

		String win_amount_str =  WebUI.getText(findTestObject('Object Repository/GameCatPage/win_amount'))
		println "Win amount:" + win_amount_str
		Float win_amount = Float.parseFloat(win_amount_str.replace("€", ""))

		Float total_win_amount = win_amount - Float.parseFloat (bet_amount)


		String expected_balance_txt = CustomKeywords.'utils.Financial_functions.calculateBalance'(initial_balance, total_win_amount.toString())

		WebUI.verifyMatch(actual_balance_txt, expected_balance_txt, false, FailureHandling.STOP_ON_FAILURE )
	}
}