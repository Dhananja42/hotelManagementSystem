package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String UserName;
    private String Email;
    private String Contact;
    private String Passowrd;




}
