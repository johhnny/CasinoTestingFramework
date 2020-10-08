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

import cucumber.api.java.After
import internal.GlobalVariable

import utils.Utils

public class Hooks {

	@After ("@Registration or @Login")
	public void afterScenario(){

		println "we are in afterScenario for scenarios: @Registration, @Login"


		WebUI.closeBrowser()
	}


	@After ("@Deposit_To_Account")
	public void afterScenario_Deposit_To_Account(){

		println "we are in afterScenario for tag @Deposit_Functionality"

		closeNotificationAndLogOut()

		WebUI.closeBrowser()
	}

	@After ("@Play_Game")
	public void afterScenario_Play_Game(){

		println "we are in afterScenario for tag @Play_Game"

		closeNotificationAndLogOut()

		WebUI.closeBrowser()
	}

	@After ("@Play_Lottery")
	public void afterScenario_PlayGame(){

		println "we are in afterScenario for tag @Play_Lottery"

		closeNotificationAndLogOut()

		WebUI.closeBrowser()
	}



	public void closeNotificationAndLogOut() {

		//close ALL notifications, if present, as it will conflict with clicking on the casino menu
		utils.Utils.closeAllNotifications()

		WebUI.click(findTestObject('Object Repository/Homepage/button_casino_menu'))

		WebUI.click(findTestObject('Object Repository/Homepage/menu_entry_Log_Out'))


	}
}
