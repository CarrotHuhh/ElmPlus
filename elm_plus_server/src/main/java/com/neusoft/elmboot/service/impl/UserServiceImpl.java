package com.neusoft.elmboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.domain.CreditsWallet;
import com.neusoft.elmboot.mapper.CreditsWalletMapper;
import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.mapper.VirtualWalletMapper;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CreditsWalletMapper creditsWalletMapper;
	
	@Autowired
	private VirtualWalletMapper virtualWalletMapper;

	@Override
	public User getUserByIdByPass(User user) {
		return userMapper.getUserByIdByPass(user);
	}

	@Override
	public int getUserById(String userId) {
		return userMapper.getUserById(userId);
	}

	@Override
	public int saveUser(User user) {
		System.out.println(user.getUserId());
		//创建用户
		userMapper.saveUser(user);
		//同时创建用户钱包
		virtualWalletMapper.saveVirtualWallet(user.getUserId());
		//创建用户积分钱包
		CreditsWallet creditsWallet = new CreditsWallet(user.getUserId(), 0);
		return creditsWalletMapper.saveCreditsWallet(creditsWallet);
	}
}