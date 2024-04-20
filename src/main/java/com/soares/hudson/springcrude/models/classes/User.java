package com.soares.hudson.springcrude.models.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private Integer id;
  private String name;
  private String username;
  private String email;
  private Address address;
  private String phone;
  private String website;
  private Companny companny;
}
