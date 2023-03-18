package com.bit.spring.service;

import com.bit.spring.model.UserCustomDetails;
import com.bit.spring.model.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final String NAMESPACE = "mapper.UserMapper";
    private SqlSession session;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(SqlSession session, BCryptPasswordEncoder passwordEncoder) {
        this.session = session;
        this.passwordEncoder = passwordEncoder;
    }

//    public UserDTO selectOne(int id){
//        String query = "SELECT * FROM user WHERE id = ?";
//        UserDTO userDTO = null;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1,id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if(resultSet.next()){
//                userDTO = new UserDTO();
//                userDTO.setId(resultSet.getInt("id"));
//                userDTO.setUsername(resultSet.getString("username"));
//                userDTO.setPassword(resultSet.getString("password"));
//                userDTO.setNickname(resultSet.getString("nickname"));
//            }
//
//            resultSet.close();
//            preparedStatement.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return userDTO;
//    }

    public UserDTO auth(UserDTO attempt) {
        return session.selectOne(NAMESPACE + ".auth", attempt);
    }

    public boolean validate(String username) {
        return session.selectOne(NAMESPACE + ".validate", username) == null;
    }

    public boolean register(UserDTO attempt) {
        if (validate(attempt.getUsername())) {
            session.insert(NAMESPACE + ".register", attempt);
            return true;
        } else {
            return false;
        }
    }

    public void delete(int id) {
        session.delete(NAMESPACE + ".delete", id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDTO user = session.selectOne(NAMESPACE + ".validate", s);
        if (user != null) {
            return new UserCustomDetails(user);
        }
        return null;
    }

    public List<UserDTO> selectAll() {
        return session.selectList(NAMESPACE + ".selectAll");
    }

    public void update(UserDTO attempt) {
        session.update(NAMESPACE + ".update", attempt);
    }

}