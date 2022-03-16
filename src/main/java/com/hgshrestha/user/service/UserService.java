package com.hgshrestha.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hgshrestha.user.VO.Department;
import com.hgshrestha.user.VO.ResponseTemplateVO;
import com.hgshrestha.user.entity.User;
import com.hgshrestha.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public ResponseTemplateVO getUserWithDepartment(Long userId) {
		ResponseTemplateVO vo = new ResponseTemplateVO();
		User user = userRepository.findByUserId(userId);
		Department department = restTemplate.getForObject("http://localhost:8081/departments/" + user.getDepartmentId(), 
				Department.class);
		vo.setUser(user);
		vo.setDepartment(department);
		return vo;
	}

}
