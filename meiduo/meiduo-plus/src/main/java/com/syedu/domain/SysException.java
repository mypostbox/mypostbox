package com.syedu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * author:Administrator
 * createTime:2023/6/1710:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysException extends Exception{
    private String Message;
    private HttpStatus status;
}
