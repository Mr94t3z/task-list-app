package com.example;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class TaskUI extends UI {

    private VerticalLayout layout;

    @Autowired
    TaskList taskList;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setupLayout();
        addHeader();
        addForm();
        addTaskList();
        addActionButtons();
    }

    private void setupLayout() {

        layout = new VerticalLayout();
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        setContent(layout);

    }

    private void addHeader() {
        Label header = new Label("List Tugas App");
        header.addStyleName(ValoTheme.LABEL_H1);
        layout.addComponent(header);

    }

    private void addForm() {
        HorizontalLayout formLayout = new HorizontalLayout();
        formLayout.setWidth("80%");

        TextField taskField = new TextField();
        taskField.focus();
        Button addButton = new Button("");

        formLayout.addComponentsAndExpand(taskField);
        formLayout.addComponent(addButton);
        layout.addComponent(formLayout);

        addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        addButton.setIcon(VaadinIcons.PLUS);

        addButton.addClickListener(click -> {
            taskList.addTodo(new Task(taskField.getValue()));
            taskField.setValue("");
            taskField.focus();
        });
        addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
    }

    private void addTaskList() {
        layout.addComponent(taskList);
    }

    private void addActionButtons() {
        Button deleteButton = new Button("Hapus List");

        deleteButton.addClickListener(click->taskList.deleteCompleted());
        deleteButton.addStyleName(ValoTheme.BUTTON_DANGER);

        layout.addComponent(deleteButton);

    }
}
