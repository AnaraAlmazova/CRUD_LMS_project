package peaksoft.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dao.UserDao;
import peaksoft.entities.Role;
import peaksoft.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    private final PasswordEncoder encoder;
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    public UserDaoImpl(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users= manager.createQuery("from User", User.class).getResultList();
        return users;
    }

    @Override
    public void addUser(User user, String roleName) {
        Role role = getRoleByName(roleName);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(roles);
        manager.persist(user);

    }

    @Override
    public void updateUser(Long userId, User user, String roleName) {
        User user1 = manager.find(User.class,userId);
        user1.setUsername(user.getUsername());
        user1.setLastName(user.getLastName());
        user1.setAge(user.getAge());
        user1.setEmail(user.getEmail());
        user1.setPassword(encoder.encode(user.getPassword()));
        Role role = getRoleByName(roleName);
        Set<Role> roles= new HashSet<>();
        roles.add(role);
        user1.setRoles(roles);
        manager.merge(user1);

    }

    @Override
    public User getUserByUsername(String username) {
        User user = manager.createQuery("select u from User u where u.username =: username", User.class)
                .setParameter("username", username).getSingleResult();
        return user;
    }

    @Override
    public void deleteUser(User user) {
        manager.remove(manager.contains(user)?user:manager.merge(user));

    }
    public Role getRoleByName(String roleName){
        Role role = manager.createQuery("select r from Role r where r.roleName =:rolename", Role.class)
                .setParameter("rolename", roleName).getSingleResult();
        return role;
    }
}
