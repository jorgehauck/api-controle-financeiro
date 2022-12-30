package com.controle.financeiro.project.exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControleFinanceiroAdvice {

	@ResponseBody
	@ExceptionHandler(ReceitasDuplicateException.class)
	public ResponseEntity<MessageExceptionHandler> receitaDuplicateException(ReceitasDuplicateException receitaDuplicateException) {
		MessageExceptionHandler error = new MessageExceptionHandler(
				new Date(), HttpStatus.BAD_REQUEST.value(), "Não é permitido receitas duplicadas");
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ExceptionHandler(ReceitasNotFoundException.class)
	public ResponseEntity<MessageExceptionHandler> receitasNotFoundException(ReceitasNotFoundException receitasNotFoundException) {
		MessageExceptionHandler error = new MessageExceptionHandler(
				new Date(), HttpStatus.NOT_FOUND.value(), "Receita não encontrada!");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ResponseBody
	@ExceptionHandler(DespesasNotFoundException.class)
	public ResponseEntity<MessageExceptionHandler> despesasNotFoundException(DespesasNotFoundException despesasNotFoundException) {
		MessageExceptionHandler error = new MessageExceptionHandler(
				new Date(), HttpStatus.NOT_FOUND.value(), "Despesa não encontrada!");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ResponseBody
	@ExceptionHandler(DespesasDuplicateException.class)
	public ResponseEntity<MessageExceptionHandler> despesasDuplicateException(DespesasDuplicateException despesasDuplicateException) {
		MessageExceptionHandler error = new MessageExceptionHandler(
				new Date(), HttpStatus.BAD_REQUEST.value(), "Não é permitido despesas duplicadas!");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseBody
	@ExceptionHandler(ResumoMesNotFoundException.class)
	public ResponseEntity<MessageExceptionHandler> resumoMesNotFoundException(ResumoMesNotFoundException resumoMesNotFoundException) {
		MessageExceptionHandler error = new MessageExceptionHandler(
				new Date(), HttpStatus.NOT_FOUND.value(), "Não existe dados para o período informado!");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
