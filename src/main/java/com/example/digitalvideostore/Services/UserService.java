package com.example.digitalvideostore.Services;

import com.example.digitalvideostore.Models.MediaItem;
import com.example.digitalvideostore.Models.UserModel;
import com.example.digitalvideostore.Models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserModel addUser(UserModel user)
    {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        UserModel insertedUser = userRepository.insert(user);

        return insertedUser;
    }

    public List<UserModel> getUsers()
    {
        return userRepository.findAll();
    }

    public Optional<UserModel> getAUser(String id) throws Exception
    {
        //return userRepository.findById(id);

        Optional<UserModel> user = userRepository.findById(id);
        if (!user.isPresent())
        {
            throw new Exception (" User with id " + id + " is not found ");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel foundUser = userRepository.findByUsername(username);

        String userN = foundUser.getUsername();
        String password = foundUser.getPassword();

        return new User(userN, password, new ArrayList<>());
    }
}
