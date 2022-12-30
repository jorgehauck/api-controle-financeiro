package com.controle.financeiro.project.exceptionhandler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageExceptionHandler {

	private Date timestamp;
	private Integer status;
	private String message;
}
