package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML파일)
//@Controller

@RestController
public class HttpControllerTest {
	
	private static final String TAG="HttpControllerTest:";
	
//	@GetMapping("/http/lombok")
//	public String lombokTest() {
//	  Member m = new Member(1, "ssar", "1234", "email");
//	  System.out.println(TAG+"getter:"+m.getId());
//	  m.setId(5000);
//	  System.out.println(TAG+"setter:"+m.getId());
//	  return "lombok test 완료";
//	}
	
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
	  Member m = Member.builder().username("abc").password("1234").email("abc@abc.com").build();
	  System.out.println(TAG+"getter:"+m.getId());
	  m.setId(5000);
	  System.out.println(TAG+"setter:"+m.getId());
	  return "lombok test 완료";
	}  //처음 id 값은 자동으로 0이 된다.

	//http://localhost:8000/http/get(select)
	@GetMapping("http/get")
	public String getTest(@RequestParam int id, @RequestParam String username) {
		return "get 요청:" +id+","+username;
	}
	
//	//http://localhost:8000/http/post(insert)
//	@PostMapping("http/post")
//	public String postTest(Member m) {
//		return "post 요청:"+m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
//	}
	
//	@PostMapping("http/post")
//	public String postTest(@RequestBody String text) {
//		return "post 요청:" + text;
//	}

	//http://localhost:8000/http/post(insert)
	@PostMapping("http/post") //text/plain, application/json
	public String postTest(@RequestBody Member m) { //MessageConverter(스프링부트)
	return "post 요청:" +m.getId()+","+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	//http://localhost:8000/http/put(update)
	@PutMapping("http/put")
	public String putTest() {
		return "put 요청";
	}
	
	//http://localhost:8000/http/delete(delete)
	@DeleteMapping("http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
}