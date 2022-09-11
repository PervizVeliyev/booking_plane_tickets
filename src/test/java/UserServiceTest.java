import entity.User;
import org.junit.Assert;
import org.junit.Test;
import service.UserService;

import java.util.List;

public class UserServiceTest {
    UserService userService = new UserService("C:\\Users\\Perviz Veliyev\\IdeaProjects\\booking_plane_tickets\\src\\test-database\\test-users.txt");
    User user1 = new User("Perviz", "perviz123A");
    User user2 = new User("Ferid", "ferid123A");

    @Test
    public void getAllUsers() {
        userService.save(user1);
        userService.save(user2);
        Assert.assertEquals(List.of(user1, user2), userService.getAllUsers());
    }

    @Test
    public void savePositiveCase() {
        Assert.assertTrue(userService.save(user1));
    }

    @Test
    public void saveNegativeCase() {
        Assert.assertFalse(userService.save(null));
    }

    @Test
    public void get() {
        userService.save(user1);
        userService.save(user2);
        Assert.assertEquals(user2, userService.get(user2.getId()).orElseThrow());
    }
}