let index = {
	init:function(){
		$("#btn-save").on("click",()=>{
			this.save();
		});
	},
	
	save:function(){
	//alert('user의 save함수 호출됨');
		let data = {
	username: $("#username").val(),
	password: $("#password").val(),
	email: $("#email").val()
	}
	$.ajax().done().fail();
	$.ajax({
		type:"POST",
		url:"/blog/api/user",
		data:JSON.stringify(data),
		contentType:"application/JSON; charset=utf-8",
		dataType:"json"
		}).done(function(resp){
			alert('회원가입완료');
			alert(resp);
			location.href="/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	
	}
}

index.init();