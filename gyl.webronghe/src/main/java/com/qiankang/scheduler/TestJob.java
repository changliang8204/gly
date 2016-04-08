package com.qiankang.scheduler;

import org.springframework.stereotype.Component;

@Component("testJob")
public class TestJob{

	public void process(){
		System.out.println("spring quart work");
	}
	
}
