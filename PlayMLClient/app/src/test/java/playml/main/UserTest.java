package playml.main;

import org.junit.Test;

import playml.model.User;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void userset() throws Exception {

        User user = new User();
        user.setUserName("asds");

        assertEquals(user.getUserName(), "asds");
    }
}