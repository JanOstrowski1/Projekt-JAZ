package com.example.demo.dto;

import lombok.Data;

@Data
public class QuoteDto {
   private String q ;//= quote text
   private String a ;//= author name
   private String i ;//= author image (key required)
   private String c ;//= character count
   private String h ;//= pre-formatted HTML quote

   public QuoteDto( String h) {

      this.h = h;
   }
}
