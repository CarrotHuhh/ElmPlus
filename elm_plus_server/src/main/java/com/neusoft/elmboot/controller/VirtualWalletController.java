package com.neusoft.elmboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.domain.VirtualWallet;
import com.neusoft.elmboot.domain.VirtualWalletTransaction;
import com.neusoft.elmboot.service.VirtualWalletService;

@RestController
@RequestMapping("/VirtualWalletController")
public class VirtualWalletController {
	@Autowired
	private VirtualWalletService virtualWalletService;

	@RequestMapping("/getVirtualWalletById")
	public VirtualWallet getVirtualWalletById(VirtualWallet virtualWallet) {
		return virtualWalletService.getVirtualWalletById(virtualWallet.getuserId());
	}

	@RequestMapping("/virtualWalletWithdraw")
	public int virtualWalletWithdraw(Double amount, String userId, String walletInId) throws Exception {
		return virtualWalletService.withdraw(amount, userId, walletInId);
	}

	@RequestMapping("/virtualWalletTopUp")
	public int topUp(Double amount, String userId, String walletOutId) throws Exception {
		return virtualWalletService.topUp(amount, userId, walletOutId);
	}

	@RequestMapping("/getVirtualWalletBalance")
	public Double getVirtualWalletBalance(String userId) {
		return virtualWalletService.getVirtualWalletBalance(userId);
	}

	@RequestMapping("/listTransByUserId")
	public List<VirtualWalletTransaction> listTransByUserId(String userId) {
		return virtualWalletService.listTransByUserId(userId);
	}

	@RequestMapping("/payByWallet")
	public int payByWallet(Integer orderId, String userId) throws Exception {
		return virtualWalletService.payByWallet(orderId, userId);
	}
}
