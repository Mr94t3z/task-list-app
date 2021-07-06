package com.example;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@UIScope
@SpringComponent
class TaskList extends VerticalLayout implements TaskChangeListener {
    @Autowired
    TaskRepository repository;
    private List<Task> tasks;

    @PostConstruct
    void init() {
        setWidth("80%");

        update();
    }

    private void update() {
        setTasks(repository.findAll());
    }

    private void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        removeAllComponents();
        tasks.forEach(todo -> addComponent(new TaskLayout(todo, this)));
    }

     void addTodo(Task task) {
        repository.save(task);
        update();
    }

    @Override
    public void todoChanged(Task task) {
        addTodo(task);
    }


    public void deleteCompleted() {
        repository.deleteByDone(true);
        update();
    }
}
