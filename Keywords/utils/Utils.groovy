package utils

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class Utils {

	@Keyword
	def verifyMessage(TestObject to, String message) {
		//create a RegEx so we can compare with the message
		String text = WebUI.getText(to)
		message  = ".*" + message.replace('"', '') + ".*"
		WebUI.verifyMatch(text, message, true, FailureHandling.STOP_ON_FAILURE)

	}

	@Keyword
	def generateUniqueEmail() {
		String myEmailAddress = "x_" + System.nanoTime() + "@x.com";
		return myEmailAddress;
	}

	@Keyword
	static def closeAllNotifications () {
		while (WebUI.waitForElementClickable(findTestObject('Object Repository/Homepage/NotificationForm/button_close_notification'), 5) == true) {
			WebUI.click(findTestObject('Object Repository/Homepage/NotificationForm/button_close_notification'))
		}
	}
}
