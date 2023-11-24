package com.vamsi.restfullwebservices.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.el.stream.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.vamsi.restfullwebservices.UserDto.UserDto;
import com.vamsi.restfullwebservices.entity.User;
import com.vamsi.restfullwebservices.mapper.UserMapper;
import com.vamsi.restfullwebservices.repository.UserRepository;
import com.vamsi.restfullwebservices.service.UserService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceimpl implements UserService {

	private UserRepository userRepository;
	
	private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		//convert userdto inot jpa entity
		//User user=UserMapper.mapTopuser(userDto);
		User user=modelMapper.map(userDto,User.class);

		User savedUser= userRepository.save(user);
		
		//UserDto savedUserDto =UserMapper.mapTopuserDto(savedUser);

		UserDto savedUserDto =modelMapper.map(savedUser,UserDto.class);

		return savedUserDto;
		
	}
	
	
	@Override
	public UserDto getUserById(Long userId) {
		java.util.Optional<User> optional=userRepository.findById(userId);
		User user= optional.get();
		//return UserMapper.mapTopuserDto(user);

	
		return modelMapper.map(user,UserDto.class);

	}


	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=userRepository.findAll();
	
		//return users.stream().map(UserMapper::mapTopuserDto)
	//.collect(Collectors.toList());
	return users.stream().map((user) -> modelMapper.map(user,UserDto.class))
			.collect(Collectors.toList());
		
		
	}
	


	@Override
	public UserDto updateUser(UserDto user) {
		User existingUser=userRepository.findById(user.getId()).get();
		existingUser.setFirstname(user.getFirstname());
		existingUser.setFirstname(user.getLastname());
		existingUser.setFirstname(user.getEmail());
			User updateUser=userRepository.save(existingUser);
			//return  UserMapper.mapTopuserDto(updateUser);
			return  modelMapper.map(updateUser,UserDto.class); 

	}


	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);// TODO Auto-generated method stub
		
	}
	
	
	
	
}
