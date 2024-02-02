# Rest-Assured-API-Testing
### An API is tested by using REST Assured framework integrated with TestNG as testing framework for validation purpose. Here, the status codes, validation messages and the flow of API is tested using a Dmoney API where there is login,searching,creating,deposit,withdraw,send money, payment features.

Here the following tasks are done:
- Do Login by admin
- Create 2 new customers and a agent
- give 2000 tk from System account to the newly created agent
- Deposit 1500 tk to a customer from the agent account
- Withdraw 500 tk by the customer to the agent
- Send money 500 tk to another customer
- Payment 100 tk to a merchant (01686606905) by the recipient customer

## Technology:
- Tool: REST Assured
- IDE: Intellij
- Build tool: Gradle
- Language: Java
- Test_Runner: TestNG

## Prerequisites
- Install jdk 11 or any LTS versio
- Configure JAVA_HOME and GRADLE_HOME
- Download Allure 2.26.0 and configure environment path
- Stable internet connection

## Project Run
- Clone the repo.
- Open cmd in the root folder.

### Run the Automation Script by the following command:
``` gradle clean test ```
- After automation to view allure report , give the following commands:
  
``` allure generate allure-results ```

``` allure serve allure-results ```
### Here is the Allure report overview:
![allure 1](https://github.com/Amit23-10/Rest-Assured-API-Testing/assets/74063361/a59de835-e6d6-484b-8bc0-2742ad432974)

### Below the suites run are shown via Allure :
![allure 2](https://github.com/Amit23-10/Rest-Assured-API-Testing/assets/74063361/f6588c65-791b-4fa9-a1cb-da3a926115f9)


