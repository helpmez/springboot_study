package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@AllArgsConstructor	// 모든 필드값을 갖는 생성자 자동 생성 	
@NoArgsConstructor	// 파라미터가 없는 생성자 자동 생성
@Data
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	

	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
