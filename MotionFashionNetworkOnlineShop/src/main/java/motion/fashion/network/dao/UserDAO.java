package motion.fashion.network.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import motion.fashion.network.formbean.UserForm;
import motion.fashion.network.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
 
@Repository
public class UserDAO {
 
    // Config in WebSecurityConfig
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    private static final Map<Long, User> USERS_MAP = new HashMap<>();
 
    static {
        initDATA();
    }
 
    private static void initDATA() {
        String encrytedPassword = "";
 
        User tom = new User(1L, "tom", "Tom",//
                true,"tom@waltdisney.com", encrytedPassword, "US");
 
        User jerry = new User(2L, "jerry", "Jerry",//
                true,"jerry@waltdisney.com", encrytedPassword, "US");
 
        USERS_MAP.put(tom.getUserId(), tom);
        USERS_MAP.put(jerry.getUserId(), jerry);
    }
 
    public Long getMaxUserId() {
        long max = 0;
        for (Long id : USERS_MAP.keySet()) {
            if (id > max) {
                max = id;
            }
        }
        return max;
    }
 
    //
 
    public User findUserByUserName(String email) {
        Collection<User> Users = USERS_MAP.values();
        for (User u : Users) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
 
    public User findUserByEmail(String email) {
        Collection<User> Users = USERS_MAP.values();
        for (User u : Users) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
 
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
 
        list.addAll(USERS_MAP.values());
        return list;
    }
 
    public User createUser(UserForm form) {
        Long userId = this.getMaxUserId() + 1;
        String encrytedPassword = this.passwordEncoder.encode(form.getPassword());
 
        User user = new User(userId,form.getFirstName(),//
                 form.getLastName(), false, //
                form.getCompany(), form.getEmail(),// 
                encrytedPassword);
 
        USERS_MAP.put(userId, user);
        return user;
    }
 
}
