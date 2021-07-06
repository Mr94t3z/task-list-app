package com.example;

import com.vaadin.data.Binder;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;


public class TaskLayout extends HorizontalLayout {
    private final CheckBox done;
    private final TextField text;

    public TaskLayout(Task task, TaskChangeListener changeListener) {
        setWidth("100%");
        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);

        done = new CheckBox();
        text = new TextField();
        text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        text.setValueChangeMode(ValueChangeMode.BLUR);

        Binder<Task> binder = new Binder<>(Task.class);
        //Binds fields in this class to those in Task based on their names
        binder.bindInstanceFields(this);
        // The following does the same more explicitly
        // binder.bind(text, Task::getText, Task::setText);
        // binder.bind(done, Task::isDone, Task::setDone);

        binder.setBean(task);

        addComponent(done);
        addComponentsAndExpand(text);

        binder.addValueChangeListener(event -> changeListener.todoChanged(task));
    }
}
