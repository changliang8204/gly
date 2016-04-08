package com.test.workflow;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.base.TestBase;

public class WorkFlowDemo extends TestBase{
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private ProcessEngineFactoryBean facoryBean;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private TaskService taskService;
	
	@Test
	public void testService() throws Exception {
		Assert.assertNotNull(runtimeService);
		ProcessEngine engine = facoryBean.getObject();
		Assert.assertNotNull(engine.getRuntimeService());
	}
	
	@Test
	public void testAutoDeployment(){
		long count = repositoryService.createProcessDefinitionQuery().count();
		Assert.assertEquals(3, count);
	}
	
	@Test
	public void testStartProcess(){
		ProcessInstance instance = runtimeService.startProcessInstanceByKey("myProcess");
		Assert.assertNotNull(instance);
		System.out.println("pid="+instance.getId()+",pdid="+instance.getProcessDefinitionId());
	}
}
