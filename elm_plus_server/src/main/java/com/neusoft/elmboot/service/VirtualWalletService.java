package com.neusoft.elmboot.service;

import java.util.List;

import com.neusoft.elmboot.domain.VirtualWallet;
import com.neusoft.elmboot.domain.VirtualWalletTransaction;

public interface VirtualWalletService {
	public VirtualWallet getVirtualWalletById(String userId);

	public int deduct(Double amount, String userId) throws Exception;

	public int withdraw(Double amount, String userId, String walletInId) throws Exception;

	public int topUp(Double amount, String userId, String walletOutId) throws Exception;

	public Double getVirtualWalletBalance(String userId);

	public List<VirtualWalletTransaction> listTransByUserId(String userId);

	public int payByWallet(Integer orderId, String userId) throws Exception;
}
