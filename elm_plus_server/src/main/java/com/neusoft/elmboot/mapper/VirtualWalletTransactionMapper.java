package com.neusoft.elmboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.neusoft.elmboot.domain.VirtualWalletTransaction;

@Mapper
public interface VirtualWalletTransactionMapper {
	public List<VirtualWalletTransaction> listTransByUserId(String userId);

	@Insert("insert into virtualwallettransaction(transactionId,transactionDate,amount,walletInId,walletOutId,transactionType,businessId) values(null,#{transactionDate},#{amount},#{walletInId},#{walletOutId},#{transactionType},#{businessId})")
	public int saveVirtualWalletTransaction(VirtualWalletTransaction virtualWalletTransaction);
}
