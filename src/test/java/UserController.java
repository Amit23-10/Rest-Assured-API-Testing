import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.configuration.ConfigurationException;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UserController {
    private String baseUrl;
    private String token;

    public UserController(String baseUrl, String token){
        this.baseUrl = baseUrl;
        this.token = token;

    }
    public UserController(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public String login(String email, String password) throws ConfigurationException, IOException {


        RestAssured.baseURI=baseUrl;
        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setPassword(password);
        Response res=given().contentType("application/json")
                .body(userModel)
                .when()
                .post("/user/login");
//        System.out.println(res.asString());

        JsonPath jsonPath = res.jsonPath();
        String token=jsonPath.get("token").toString();
        Utils.setEnvVariable("token",token);
        return token;

    }

    public Response createUser(UserModel userModel) throws IOException {

        RestAssured.baseURI=baseUrl;
//        UserModel userModel = new UserModel();

        Response res=given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                .body(userModel)
                .when()
                .post("/user/create");
        return res ;

    }

    public Response createCustomer(UserModel userModel) throws IOException {

        RestAssured.baseURI=baseUrl;
//        UserModel userModel = new UserModel();

        Response res=given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                .body(userModel)
                .when()
                .post("/user/create");
        return res ;

    }

    public Response createAgent(UserModel userModel) throws IOException {

        RestAssured.baseURI=baseUrl;
//        UserModel userModel = new UserModel();

        Response res=given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                .body(userModel)
                .when()
                .post("/user/create");
        return res ;

    }


    public Response searchUser(String userid1){
        RestAssured.baseURI=baseUrl;
//        UserModel userModel = new UserModel();

        Response res=given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                .when()
                .get("/user/search/id/"+userid1);

        return res ;
    }

    public Response depositToAgent(String agentPhoneNumber){
        RestAssured.baseURI=baseUrl;
//        UserModel userModel = new UserModel();

        Response res=given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                .body("{\n" +
                        "    \"from_account\":\"SYSTEM\",\n" +
                        "    \"to_account\":\"01417933818\",\n" +
                        "    \"amount\":5000\n" +
                        "}")
                .when()
                .post("/transaction/deposit");

        return res ;
    }

    public Response depositToCustomer(String customer1PhoneNumber){
        RestAssured.baseURI=baseUrl;
//        UserModel userModel = new UserModel();

        Response res=given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                .body("{\n" +
                        "    \"from_account\":\"01417933818\",\n" +
                        "    \"to_account\":\"01414638458\",\n" +
                        "    \"amount\":2000\n" +
                        "}")
                .when()
                .post("/transaction/deposit");

        return res ;
    }

    public Response withdrawByCustomer(String customer1PhoneNumber){
        RestAssured.baseURI=baseUrl;
//        UserModel userModel = new UserModel();

        Response res=given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                .body("{\n" +
                        "    \"from_account\":\"01414638458\",\n" +
                        "    \"to_account\":\"01417933818\",\n" +
                        "    \"amount\":300\n" +
                        "}")
                .when()
                .post("/transaction/withdraw");

        return res ;
    }

    public Response sendMoneyByCustomer(String customer1PhoneNumber , String customer2PhoneNumber){
        RestAssured.baseURI=baseUrl;
//        UserModel userModel = new UserModel();

        Response res=given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                .body("{\n" +
                        "    \"from_account\":\"01503824459\",\n" +
                        "    \"to_account\":\"01507519716\",\n" +
                        "    \"amount\":200\n" +
                        "}")
                .when()
                .post("/transaction/sendmoney");

        return res ;
    }

    public Response paymentByCustomer(String customer2PhoneNumber){
        RestAssured.baseURI=baseUrl;
//        UserModel userModel = new UserModel();

        Response res=given().contentType("application/json")
                .header("Authorization",token)
                .header("X-AUTH-SECRET-KEY","ROADTOSDET")
                .body("{\n" +
                        "    \"from_account\":\"01503824459\",\n" +
                        "    \"to_account\":\"01686606905\",\n" +
                        "    \"amount\":100\n" +
                        "}")
                .when()
                .post("/transaction/payment");

        return res ;
    }




}
