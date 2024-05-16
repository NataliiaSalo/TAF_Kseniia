package tests.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
import api.utils.DataProviders;


public class DDTests {
	@Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
	public void testPostUser(String userID, String userName, String fname, String lname, String useremail,String pwd,String ph) throws
			JsonProcessingException {
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUserName(userName);
				userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
				userPayload.setEmail(useremail);
				userPayload.setPassword(pwd);
		userPayload.setPhone(ph);


		Response response = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
	public void testDeleteUserByName(String userName){
		Response response=UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
	}
}
