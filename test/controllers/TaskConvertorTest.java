package controllers;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import models.po.AndroidPackTask;
import models.po.AndroidSubTask;

import org.junit.Test;

import controllers.convertor.TaskConvertor;
import enums.TaskStatus;

public class TaskConvertorTest {
	TaskConvertor convertor = new TaskConvertor();
	
	@Test
	public void testOnlyHaveWaitingPackTask(){
		AndroidPackTask p = new AndroidPackTask();
		p.setStatus(TaskStatus.WAITING.getName());
		Assert.assertEquals(TaskStatus.WAITING,convertor.getExpectedStatus(p, null));
	}
	
	@Test
	public void testOnlyHaveSuccessPackTask(){
		AndroidPackTask p = new AndroidPackTask();
		p.setStatus(TaskStatus.SUCCESS.getName());
		Assert.assertEquals(TaskStatus.SUCCESS,convertor.getExpectedStatus(p, null));
	}
	
	@Test
	public void testOnlyHaveFailedPackTask(){
		AndroidPackTask p = new AndroidPackTask();
		p.setStatus(TaskStatus.FAILED.getName());
		Assert.assertEquals(TaskStatus.FAILED,convertor.getExpectedStatus(p, null));
	}
	
	@Test
	public void testOnlyHaveProcessingPackTask(){
		AndroidPackTask p = new AndroidPackTask();
		p.setStatus(TaskStatus.PROCESSING.getName());
		Assert.assertEquals(TaskStatus.PROCESSING,convertor.getExpectedStatus(p, null));
	}
	
	@Test
	public void testHaveMoreTasks(){
		AndroidPackTask p = new AndroidPackTask();
		p.setStatus(TaskStatus.PROCESSING.getName());
		List<AndroidSubTask> subTasks = new ArrayList<AndroidSubTask>();
		AndroidSubTask cpuTask = new AndroidSubTask();
		cpuTask.setStatus(TaskStatus.WAITING.getName());
		subTasks.add(cpuTask);
		Assert.assertEquals(TaskStatus.PROCESSING,convertor.getExpectedStatus(p, subTasks));
	}
	
	@Test
	public void testHaveMoreTasksAndBothInWaiting(){
		AndroidPackTask p = new AndroidPackTask();
		p.setStatus(TaskStatus.WAITING.getName());
		List<AndroidSubTask> subTasks = new ArrayList<AndroidSubTask>();
		AndroidSubTask cpuTask = new AndroidSubTask();
		cpuTask.setStatus(TaskStatus.WAITING.getName());
		subTasks.add(cpuTask);
		Assert.assertEquals(TaskStatus.WAITING,convertor.getExpectedStatus(p, subTasks));
	}
	
	@Test
	public void testHaveMoreTasks3(){
		AndroidPackTask p = new AndroidPackTask();
		p.setStatus(TaskStatus.SUCCESS.getName());
		List<AndroidSubTask> subTasks = new ArrayList<AndroidSubTask>();
		AndroidSubTask cpuTask = new AndroidSubTask();
		cpuTask.setStatus(TaskStatus.FAILED.getName());
		subTasks.add(cpuTask);
		Assert.assertEquals(TaskStatus.FAILED,convertor.getExpectedStatus(p, subTasks));
	}
	
	@Test
	public void testHaveMoreTasks4(){
		AndroidPackTask p = new AndroidPackTask();
		p.setStatus(TaskStatus.SUCCESS.getName());
		List<AndroidSubTask> subTasks = new ArrayList<AndroidSubTask>();
		AndroidSubTask cpuTask = new AndroidSubTask();
		cpuTask.setStatus(TaskStatus.SUCCESS.getName());
		subTasks.add(cpuTask);
		Assert.assertEquals(TaskStatus.SUCCESS,convertor.getExpectedStatus(p, subTasks));
	}
	
	@Test
	public void testHaveMoreTasks5(){
		AndroidPackTask p = new AndroidPackTask();
		p.setStatus(TaskStatus.SUCCESS.getName());
		List<AndroidSubTask> subTasks = new ArrayList<AndroidSubTask>();
		AndroidSubTask cpuTask = new AndroidSubTask();
		cpuTask.setStatus(TaskStatus.PROCESSING.getName());
		subTasks.add(cpuTask);
		Assert.assertEquals(TaskStatus.PROCESSING,convertor.getExpectedStatus(p, subTasks));
	}
	
	@Test
	public void testHaveMoreTasks6(){
		AndroidPackTask p = new AndroidPackTask();
		p.setStatus(TaskStatus.SUCCESS.getName());
		List<AndroidSubTask> subTasks = new ArrayList<AndroidSubTask>();
		AndroidSubTask cpuTask = new AndroidSubTask();
		cpuTask.setStatus(TaskStatus.PROCESSING.getName());
		subTasks.add(cpuTask);
		AndroidSubTask memoryTask = new AndroidSubTask();
		memoryTask.setStatus(TaskStatus.WAITING.getName());
		subTasks.add(memoryTask);
		Assert.assertEquals(TaskStatus.PROCESSING,convertor.getExpectedStatus(p, subTasks));
	}

}
