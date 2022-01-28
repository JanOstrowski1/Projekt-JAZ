package com.example.demo.dto;

import lombok.Data;

@Data
public class QuoteDto {
   private String q ;//= quote text
   private String a ;//= author name
   private String h ;//= pre-formatted HTML quote
   private String i ;//= image-link

   public QuoteDto(String q, String a, String h) {
      this.q = q;
      this.a = a;
      this.h = h;
   }
}
