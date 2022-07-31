package dataprovider;

import core.base.TestEngine;
import core.utils.PojoUtilities;
import examples.pojo.CreateUser;
import org.testng.annotations.DataProvider;

import static core.faker.FakeDataFactory.fakeData;

public class TestDataProvider extends TestEngine {

@DataProvider(name = "userProfileData")
public Object[][] getUserProfileData() {

    CreateUser createUser = new CreateUser();
    createUser.setName( fakeData.name().fullName());
    createUser.setEmail(fakeData.internet().safeEmailAddress());
    boolean gender = fakeData.random().nextBoolean();
    if (gender) {
        createUser.setGender("male");
    } else {
        createUser.setGender("female");
    }
    createUser.setStatus("active");

    return new Object[][]{{PojoUtilities.generateJSONFromObject(createUser)}};
}

}
