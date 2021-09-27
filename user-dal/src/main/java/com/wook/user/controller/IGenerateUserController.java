package com.wook.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.wook.user.model.dto.ResponseDto;
import com.wook.user.model.dto.UserDto;

import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@SwaggerDefinition(
        info = @Info(
                description = "Api encargado de administriar y mantener usuarios",
                version = "V1.0.0",
                title = "Generador de Usuarios",
                termsOfService = "https://github.com/w00k",
                contact = @Contact(
                   name = "w00k", 
                   email = "wook82@gmail.com", 
                   url = "https://github.com/w00k"
                ),
                license = @License(
                   name = "Apache 2.0", 
                   url = "http://www.apache.cl/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json"},
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        tags = {
                @Tag(name = "Private", description = "tag descriptivo para pruebas")
        }, 
        externalDocs = @ExternalDocs(value = "Template de ejemplo", url = "http://localhost/site-autogenerado.html")
)
public interface IGenerateUserController {
	
    public ResponseEntity<UserDto> getUser(@PathVariable("id") String id) ;

    public ResponseEntity<ResponseDto> putUser(@RequestBody UserDto userDto);
    
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") String id);
    
    public ResponseEntity<ResponseDto> loginUser(@RequestBody UserDto userDto);

    public ResponseEntity<ResponseDto> updateUser(@RequestBody UserDto userDto);

}
