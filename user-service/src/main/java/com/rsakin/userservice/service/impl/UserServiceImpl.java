package com.rsakin.userservice.service.impl;

import com.rsakin.userservice.dto.UserDTO;
import com.rsakin.userservice.entity.User;
import com.rsakin.userservice.exception.InvalidRequestException;
import com.rsakin.userservice.exception.UserNotFoundException;
import com.rsakin.userservice.repository.UserRepository;
import com.rsakin.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> all = userRepository.findAll();
        return all.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getOne(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        User user = byId.orElseThrow(() -> new UserNotFoundException(id));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User byEmail = userRepository.findByEmail(email);
        return modelMapper.map(byEmail, UserDTO.class);
    }

    @Override
    public UserDTO addOne(User user) {
        user.setPassword(user.getPassword());
        User save = userRepository.save(user);
        return modelMapper.map(save, UserDTO.class);
    }

    @Override
    public UserDTO updateOne(User user) {
        // we need to check dto id or put another validation
        // to prevent db null field exceptions
        // we can customize the exception to handle on
        // genericExpHandler specifically
        if (user.getId() == null)
            throw new InvalidRequestException(user.toString());
        // check whether there is a such user or not
        getOne(user.getId());
        User save = userRepository.save(user);
        return modelMapper.map(save, UserDTO.class);
    }

    @Override
    public Map<String, String> deleteOne(Integer id) {
        // check whether there is a such user or not
        Optional<User> byId = userRepository.findById(id);
        User user = byId.orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
        Map<String, String> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE.toString());
        return response;
    }

}