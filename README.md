# CasinoTestingFramework

It is a automation project done in Katalon using Cucumber.
The automation tests are split in 2 types: Smoke test and Regression tests. It test the functionality from demo casino: https://demo.ft-crm.com/ 

Structure of project:
- folder "Include/features/": contains the feature files. The tests for automation were grouped in 5 feature files , corresponding to the casino functionality: Registration, Login, Deposit , Play Game and Play Lottery.
- folder "Include/scripts/groovy/(default package)": contains the implementation of each of the feature 
- folder "Object Repository/": contains the web elements that we will use in the tests. They are organized per webpage that we test. Each element is identified first by CSS but where this is not possible or returns multiple webelements , we use XPath.
- folder "Test Cases/": contains the tests that enable us to call the scenarios from each feature files. The tests are calling the scenarios for each of the functionality , based on the tag: Smoke or Regression.
- folder "Test Suites/": contains the testsuites. There are 2 of testsuites in order to group the tests of all the functionality: Smoke and Regression. In order to run the tests on multiple browsers , there are also 2 testsuites collection that will run the testsuites.The testsuites collection are already set to run in parallel.
- folder "Keywords/utils/": contains functions that helps on the tests. They group common functionality needed in multiple tests.
		* Financial_functions.groovy: has functions to help in the finacial operations, like calculating balance
		* Hooks.groovy: contains the after scenario hook implementation according to each of the scenario.
		* Utils.groovy: contains different functionality, like verifying messages in the webpage, generating unique email address, close notification in webpage
- folder "Profiles/default" : contains global variables that will be used across all tests. We stored here the casino url , a valid username, authentication password (it is encrypted)
		
		
Note: 
1. As you already know, sometimes tests are failing because of some abnormal conditions, due to slow connection or issues on the server, etc. This is a false positive result. To "solve" this, I configured testsuites to automatically re-run once more the tests that fail.
2. The testsuite collection runs now on Chrome and Firefox on 2 parallel threads. You can add more browsers and expand the parallel threads if you want.


Steps to run:

1. Download and unzip the Katalon Studio (the free version is perfect)
2. Open Katalon Studio.
3. Clone the following project from github: https://github.com/johhnny/CasinoTestingFramework.git
4. Make sure you have the latest webdrivers (go to Tools menu -> Update WebDrivers) and latest browser version.
5. To run the tests:
	a. To run either one of the testsuite (Smoke_tests_testsuite or Regression_tests_testsuite) please open it first and then click on the Run button (from the toolbox menu)
	b. To run either one of the testsuite collection (Smoke_tests_all_browsers or Regression_tests_all_browsers) please open it first and then click on the Execute button.
6. You can check the progress of the run by looking at the Log Viewer (see steps passed or fail) or Console (display the console messages) from the bottom of Katalon window
7. For the reporting:
	a. On Katalon, on the testsuite or testsuite collection that you run, please switch to Result tab
	b. Report file can also be found on the HDD, in the Report folder. Here there are the folders containing the reports of the runs. The folder name is in the format <date>_<time> of when the running was triggered.
	


Notes: 
1. The project can run in Katalon IDE , on CI/CD env like Jenkins or Docker. 
2. I have used Katalon Studio for the numerous advantages that it offers: it is POM framework, short developing time, versatility (in the future , you can create also API tests or mobile tests without effort), Already built-in Waiting time for the web elements , option to run multiple testsuites in parallel, a built-in POM framework schelet 
3. If needed (or decided), to "move" the tests to another Selenium+Cucumber framework (like C# or Java), the effort is minimum as Katalon framework is based on Java.
4. Easy to maintain code and files, as the Katalon projects offers a nice structure and organization of files.
5. It can be integrated with Analitycs (so all the team can see the run results) or Jira. 

I have run the tests on both Chrome and Firefox and all them passed  
