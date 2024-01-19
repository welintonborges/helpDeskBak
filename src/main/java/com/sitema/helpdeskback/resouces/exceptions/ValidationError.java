package com.sitema.helpdeskback.resouces.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandarError{

   private static final long serialVersionUID = 1L;
   private List<FieldMessage> erros = new ArrayList<>();

   public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
      super(timestamp, status, error, message, path);
      this.erros = erros;
   }

   public List<FieldMessage> getErros() {
      return erros;
   }

   public void addError(String fielName, String message) {
      this.erros.add(new FieldMessage(fielName, message));
   }
}
