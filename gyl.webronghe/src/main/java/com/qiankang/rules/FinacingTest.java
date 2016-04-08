package com.qiankang.rules;

import java.math.BigDecimal;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class FinacingTest {

	public static void main(String[] args) {
		KieServices ks = KieServices.Factory.get();  
		KieContainer kContainer = ks.getKieClasspathContainer();  
		KieSession kSession = kContainer.newKieSession("session-financing");  
		FinancingRequest fr = new FinancingRequest();
		fr.setFinancingAmount(new BigDecimal(200000000));
		fr.setMonths(3);
		kSession.insert(fr);  
		kSession.fireAllRules();  
		kSession.dispose();

	}

}
