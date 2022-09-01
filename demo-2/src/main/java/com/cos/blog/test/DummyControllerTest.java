package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
		
	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	//http://localhost:8000/blog/dummy/join(요청)
	//http의 body에 username, password, email 데이터를 가지고 (요청)
//	@PostMapping("/dummy/join")
//	public String join(String username, String password, String email) {
//		System.out.println("username:"+username);
//		System.out.println("password:"+password);
//		System.out.println("email:"+email);
//		return "회원가입이 완료되었습니다.";
//	}
	
	@PostMapping("/dummy/join")
	public String join(User user) { //key=value (약속된 규칙)
		System.out.println("id:"+user.getId());
		System.out.println("username:"+user.getUsername());
		System.out.println("password:"+user.getPassword());
		System.out.println("email:"+user.getEmail());
		System.out.println("role:"+user.getRole());
	        System.out.println("createDate:"+user.getCreateDate());
	    
	    user.setRole(RoleType.USER);
	    userRepository.save(user);
		return "회원가입이 완료되었습니다.";
		
		
	}
	
	//{id} 주소로 파라미터를전달 받을수 있음.
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id){
		
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
			// TODO Auto-generated method stub
		 	     return new IllegalArgumentException("해당 유저는 없습니다. id" + id);
			}
		});
		// 요청 : 웹브라우저
		// user 객체 = 자바 오브젝트  
		// 변환(웹브라우저가 이해할 수 있는 데이터) -> json
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동	
		// 만약에 자바 오브젝트를 리턴하게 되면 MessgeConverter가 Jackson라이브러리르 호출해서
		// user오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
			return user;
		}
	
	//http://localhost:8000/blog/dummy/users
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한 페이지당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public Page<User> pagelist(@PageableDefault(size=1, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> user = userRepository.findAll(pageable);
		return user;
	}	
	
//	public List<User> pagelist(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
//	    List<User> user = userRepository.findAll(pageable).getContent();
//	    return user;
//	}
	
	//email, password
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {//json 데이터요청=>Java Object(MessageConverter의 Jackson라이브러리가 변횐해서 받아 줌) 
		System.out.println("id:"+id);
		System.out.println("password:"+requestUser.getPassword());
		System.out.println("email:"+requestUser.getEmail());
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		user.setUsername(requestUser.getUsername());
//		requestUser.setId(id);
//		requestUser.setUsername("ssar");
//		userRepository.save(requestUser);
		return null;
	}	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);	
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		
		return "삭제되었습니다.id:"+id;
	}
	}

