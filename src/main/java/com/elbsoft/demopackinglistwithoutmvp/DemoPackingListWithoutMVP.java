package com.elbsoft.demopackinglistwithoutmvp;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;

@CDIUI
@Theme("valo")
public class DemoPackingListWithoutMVP extends UI {

    private VerticalLayout mainLayout = new VerticalLayout();
    private HorizontalLayout insertLayout = new HorizontalLayout();

    private Label labelHeader = new Label("<H1>Packliste</H1>", 
            ContentMode.HTML);

    private TextField fieldItemName = new TextField();
    private Button buttonAddItem = new Button("+");
    private Grid gridItemList = new Grid();

    private ArrayList<Item> items = new ArrayList();

    @Override
    protected void init(VaadinRequest request) {
        fieldItemName.setInputPrompt("Das muss mit...");
        gridItemList.addColumn("", String.class);

        insertLayout.addComponent(fieldItemName);
        insertLayout.addComponent(buttonAddItem);

        mainLayout.addComponent(labelHeader);
        mainLayout.addComponent(insertLayout);
        mainLayout.addComponent(gridItemList);

        buttonAddItem.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {

                String itemName = fieldItemName.getValue();
                boolean itemFoundInList = false;
                if (itemName != null && !itemName.isEmpty()) {
                    for (Item item : items) {
                        if (item.getName().equalsIgnoreCase(itemName)) {
                            itemFoundInList = true;
                        }
                    }
                    if (!itemFoundInList) {
                        items.add(new Item(itemName));
                        gridItemList.addRow(itemName);
                        fieldItemName.clear();
                    }
                }
            }
        });

        this.setContent(mainLayout);
    }
}

