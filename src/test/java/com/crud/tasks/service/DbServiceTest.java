package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    DbService dbService;
    @Mock
    TaskRepository taskRepository;
    ;

    @Test
    public void getAllTasks() {
        //GIVEN
        when(taskRepository.findAll()).thenReturn(new ArrayList<>());
        //WHEN
        //THEN
        assertEquals(0, dbService.getAllTasks().size());
    }

    @Test
    public void getTask() {
        //Given
        Task task = new Task(1L, "test task", "test content");
        when(taskRepository.findById(1L)).thenReturn(java.util.Optional.of(new Task(1L, "test", "tes")));
        //WHEN
        Optional<Task> foundTask=dbService.getTask(1L);
        //THEN

        assertEquals(Optional.of(1L), Optional.ofNullable(foundTask.get().getId()));
    }

    @Test
    public void saveTask(){
        //GIVEN
        Task task = new Task(1L, "test task", "test content");
        when(taskRepository.save(task)).thenReturn(task);
        //WHEN
        Task savedTask=dbService.saveTask(task);
        //THEN
        assertEquals(Optional.of(1L), Optional.ofNullable(savedTask.getId()));


    }
}