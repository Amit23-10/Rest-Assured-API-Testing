import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserTestRunner extends Setup {

    @Test(priority = 1, description = "Admin can successfully login")

    public void login() throws ConfigurationException, IOException {
        UserController user = new UserController(props.getProperty("baseUrl"));
        String token=user.login("salman@roadtocareer.net","1234");
        System.out.println(token);
    }
    @Test(priority = 2, description = "Admin can create new user")

    public void createNewUser() throws IOException, ConfigurationException {
        UserController user = new UserController(props.getProperty("baseUrl"), props.getProperty("token"));
        Faker faker = new Faker();
        UserModel userModel =new UserModel();
        userModel.setName(faker.name().fullName());
        userModel.setEmail(faker.internet().emailAddress());
        userModel.setPassword("1234");
        userModel.setPhone_number("0141"+Utils.genarateRandom(1000000,9999999));
        userModel.setNid("789456123");
        userModel.setRole("Customer");
        Response res = user.createUser(userModel);
        System.out.println(res.asString());

        JsonPath jsonPath = res.jsonPath();
        String message=jsonPath.get("message").toString();
        Assert.assertTrue(message.contains("User created"));
        String userid = jsonPath.get("user.id").toString();
        Utils.setEnvVariable("userid1",userid);
        String phone_number = jsonPath.get("user.phone_number").toString();
        Utils.setEnvVariable("customer1PhoneNumber",phone_number);
    }
    @Test(priority = 3, description = "Admin can create new customer again")

    public void createNewCustomer() throws IOException, ConfigurationException {
        UserController user = new UserController(props.getProperty("baseUrl"), props.getProperty("token"));
        Faker faker = new Faker();
        UserModel userModel =new UserModel();
        userModel.setName(faker.name().fullName());
        userModel.setEmail(faker.internet().emailAddress());
        userModel.setPassword("1234");
        userModel.setPhone_number("0141"+Utils.genarateRandom(1000000,9999999));
        userModel.setNid("789456123");
        userModel.setRole("customer");
        Response res = user.createUser(userModel);
        System.out.println(res.asString());

        JsonPath jsonPath = res.jsonPath();
        String message=jsonPath.get("message").toString();
        Assert.assertTrue(message.contains("User created"));
        String userid = jsonPath.get("user.id").toString();
        Utils.setEnvVariable("userId2",userid);
        String phone_number = jsonPath.get("user.phone_number").toString();
        Utils.setEnvVariable("customer2PhoneNumber",phone_number);
    }
    @Test(priority = 4, description = "Admin create new agent")
    public void createNewAgent() throws IOException, ConfigurationException {
        UserController user = new UserController(props.getProperty("baseUrl"), props.getProperty("token"));
        Faker faker = new Faker();
        UserModel userModel =new UserModel();
        userModel.setName(faker.name().fullName());
        userModel.setEmail(faker.internet().emailAddress());
        userModel.setPassword("1234");
        userModel.setPhone_number("0141"+Utils.genarateRandom(1000000,9999999));
        userModel.setNid("789456123");
        userModel.setRole("Agent");
        Response res = user.createUser(userModel);
        System.out.println(res.asString());

        JsonPath jsonPath = res.jsonPath();
        String message=jsonPath.get("message").toString();
        Assert.assertTrue(message.contains("User created"));
        String userid = jsonPath.get("user.id").toString();
        Utils.setEnvVariable("agentId",userid);
        String phone_number = jsonPath.get("user.phone_number").toString();
        Utils.setEnvVariable("agentPhoneNumber",phone_number);

    }
    @Test(priority = 5, description = "Admin can search user by their userId")

    public void searchUser(){
        UserController user = new UserController(props.getProperty("baseUrl"), props.getProperty("token"));
        Response res =user.searchUser(props.getProperty("userid1"));
        JsonPath jsonPath = res.jsonPath();
        System.out.println(jsonPath.get().toString());
    }
    @Test(priority = 5, description = "Deposit money from system to the agent")

    public void depositToAgent(){
        UserController user = new UserController(props.getProperty("baseUrl"), props.getProperty("token"));
        Response res =user.depositToAgent(props.getProperty("agentPhoneNumber"));
        JsonPath jsonPath = res.jsonPath();
        System.out.println(jsonPath.get().toString());
    }
    @Test(priority = 6, description = "Deposit money from agent to customer")

    public void depositToCustomer(){
        UserController user = new UserController(props.getProperty("baseUrl"), props.getProperty("token"));
        Response res =user.depositToCustomer(props.getProperty("customer1PhoneNumber"));
        JsonPath jsonPath = res.jsonPath();
        System.out.println(jsonPath.get().toString());
    }
    @Test(priority = 7, description = "User can withdraw money to the agent")

    public void withdrawByCustomer(){
        UserController user = new UserController(props.getProperty("baseUrl"), props.getProperty("token"));
        Response res =user.withdrawByCustomer(props.getProperty("agentPhoneNumber"));
        JsonPath jsonPath = res.jsonPath();
        System.out.println(jsonPath.get().toString());
    }
    @Test(priority = 8,description = "Customer can send money to another customer")

    public void sendMoneyByCustomer(){
        UserController user = new UserController(props.getProperty("baseUrl"), props.getProperty("token"));
        Response res =user.sendMoneyByCustomer(props.getProperty("customer1PhoneNumber"),props.getProperty("customer2PhoneNumber"));
        JsonPath jsonPath = res.jsonPath();
        System.out.println(jsonPath.get().toString());
    }
    @Test(priority = 9, description = "Customer can make payment to merchant")

    public void paymentByCustomer(){
        UserController user = new UserController(props.getProperty("baseUrl"), props.getProperty("token"));
        Response res =user.paymentByCustomer(props.getProperty("customer2PhoneNumber"));
        JsonPath jsonPath = res.jsonPath();
        System.out.println(jsonPath.get().toString());
    }




}
