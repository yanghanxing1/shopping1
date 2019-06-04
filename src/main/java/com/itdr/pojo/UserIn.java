package com.itdr.pojo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/*用户实体类*/
@Data
public class UserIn {
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private Date create_Time;
    private Date update_Time;
}
