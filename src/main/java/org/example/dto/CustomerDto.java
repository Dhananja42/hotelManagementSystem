package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDto {
    private String name;
    private String Nic;
    private String sdate;
    private String edate;
    private String roomId;



}
