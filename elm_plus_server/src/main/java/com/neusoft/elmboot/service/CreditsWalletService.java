package com.neusoft.elmboot.service;

import java.util.List;

import com.neusoft.elmboot.domain.CreditsRecord;

public interface CreditsWalletService {
	public Integer getCreditsById(String userId);

	public int creditsAdd(Integer amount, String userId);

	public int creditsDeduct(Integer amount, String userId);

	public int updateValidState(String userId);
	
	public List<CreditsRecord> getCreditsRecordById(String userId);
}
