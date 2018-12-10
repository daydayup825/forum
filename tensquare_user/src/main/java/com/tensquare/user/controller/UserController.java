package com.tensquare.user.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tensquare.user.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import util.JwtUtil;

/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;


	/**
	 *
	 * 功能描述:
	 * 更新好友粉丝数和自己关注数
	 * @param:
	 * @return:
	 * @auther: fanbopeng
	 * @date: 2018/11/22 20:27
	 */
	@PutMapping(value = "/{userid}/{friendid}/{x}")
	public  void uodateFanscountAndfloowcount(@PathVariable String userid,@PathVariable String friendid,@PathVariable int x){
        userService.uodateFanscountAndfloowcount(x,userid,friendid);


	}








	/**
	 *
	 * 功能描述:
	 * 注册
	 * @param:
	 * @return:
	 * @auther: fanbopeng
	 * @date: 2018/11/20 20:46
	 */


	@PostMapping("/login")
	public  Result login(@RequestBody User user){

		User getUser= userService.login(user.getMobile(),user.getPassword());

		if (getUser ==null){

			return new Result(false,StatusCode.LOGINERROR,"登录失败");
		}

		//使得前后端可以通话的操作,使用jwt来实现.
		String token = jwtUtil.createJWT(user.getId(), user.getMobile(), "user");
		Map<String,Object> map = new HashMap<>();
		map.put("token", map);
		map.put("roles", "user");
		return new Result(true,StatusCode.OK,"登录成功",map);
	}





	@PostMapping("/register/{code}")
    public Result register(@PathVariable String code,@RequestBody User user){

          userService.register(code,user);

        return new Result(true,StatusCode.OK,"注册成功");

    }



	/**
	 *
	 * 功能描述:
	 *发送短信验证码
	 * @param:
	 * @return:
	 * @auther: fanbopeng
	 * @date: 2018/11/20 19:08
	 */
	@PutMapping("/sendsms/{mobile}")
	 public Result sendsms(@PathVariable String mobile){

		userService.sendSms(mobile);
		System.out.println("123");
		return new Result(true,StatusCode.OK,"发送成功");



	 }
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",userService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<User> pageList = userService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param user
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody User user  ){
		userService.add(user);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param user
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody User user, @PathVariable String id ){
		user.setId(id);
		userService.update(user);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除	必须拥有admin角色才可以删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		userService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
