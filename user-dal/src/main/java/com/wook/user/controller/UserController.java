package com.wook.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wook.user.config.LoggerConfiguration;
import com.wook.user.model.dto.ResponseDto;
import com.wook.user.model.dto.UserDto;
import com.wook.user.service.UserService;
import com.wook.user.service.validation.UserValidate;

@RestController
@RequestMapping("/api/v1/db")
public class UserController {

    private LoggerConfiguration logger = LoggerConfiguration.getLogger("UserController");
    
    @Autowired
    private UserService userService;
    
    //find user by id
    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") String id) {
        logger.info("getUser - start, id=" + id);
        return new ResponseEntity<UserDto>(userService.findById(Long.parseLong(id)), HttpStatus.OK);
    }
    
    //add user 
    @PutMapping("/put")
    public ResponseEntity<ResponseDto> putUser(@RequestBody UserDto userDto) {
    	UserValidate userValidate = new UserValidate();
    	logger.info("putUser - start");
    	userValidate.validateUser(userDto);
        return new ResponseEntity<ResponseDto>(userService.saveUser(userDto), HttpStatus.CREATED);
    }
    
    //delete user by id 
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") String id) {
    	logger.info("deleteUser - start, id=" + id);
    	return new ResponseEntity<UserDto>(userService.delete(Long.parseLong(id)), HttpStatus.OK);
    }
    
    //login
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> loginUser(@RequestBody UserDto userDto) {
    	logger.info("loginUser - start");
    	return new ResponseEntity<ResponseDto>(userService.loginUser(userDto), HttpStatus.OK); 
    }
    
    //update user
    @PostMapping("/update")
    public ResponseEntity<ResponseDto> updateUser(@RequestBody UserDto userDto) {
    	logger.info("loginUser - start");
    	return new ResponseEntity<ResponseDto>(userService.updateUser(userDto), HttpStatus.OK); 
    } 
    
}
