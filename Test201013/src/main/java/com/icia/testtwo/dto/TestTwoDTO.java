package com.icia.testtwo.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Getter
@Setter
@ToString
public class TestTwoDTO {
	private String tid;
	private String tpassword;
	private String tname;
	private String tbirth;
	private String temail;
	private String postcode;
	private String address;
	private String taddress;
	private String extaaddress;
	private String tphone;
	private MultipartFile tfile;
	private String tprofile;
	
	private String kakaoId;
	private String naverId;
}
