package com.crud.tasks.taskMapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto= new TaskDto(1L,"test","test content");
        //When
        Task task=taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(Long.valueOf(1L),task.getId());
        assertEquals("test",task.getTitle());
        assertEquals("test content",task.getContent());
    } @Test
    public void mapToTaskDto() {
        //Given
        Task task1= new Task(1L,"test","test content");
        //When
        TaskDto task=taskMapper.mapToTaskDto(task1);
        //Then
        assertEquals(Long.valueOf(1L),task.getId());
        assertEquals("test",task.getTitle());
        assertEquals("test content",task.getContent());
    }
    @Test
    public void mapToEmptyTaskDtoListTest(){
        //Given
        List<Task> taskList=new ArrayList<>();
        //When
        List<TaskDto> taskDtos=taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(0,taskDtos.size());

    } @Test
    public void mapToTaskDtoListTest(){
        //Given
        Task task1= new Task(1L,"test","test content");
        List<Task> taskList=new ArrayList<>();
        taskList.add(task1);
        //When
        List<TaskDto> taskDtos=taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(1,taskDtos.size());

    }
}
