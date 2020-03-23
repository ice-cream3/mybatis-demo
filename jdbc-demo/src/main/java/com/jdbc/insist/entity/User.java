package com.jdbc.insist.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
	private static final long serialVersionUID = -4016092787829080834L;
	private int id;
	private int age;
	private String username;
}
