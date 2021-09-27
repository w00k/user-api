package com.wook.user.service;

import com.wook.user.model.dto.ResponseDto;
import com.wook.user.model.dto.UserDto;

public interface UserService {
	
    public ResponseDto saveUser(UserDto userDto);
    
    public UserDto findById(Long id);
    
    public UserDto delete(Long id);

	public ResponseDto loginUser(UserDto userDto);

	public ResponseDto updateUser(UserDto userDto);
}
