package com.koobe.editor.widget.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.*;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.constants.ButtonSize;
import org.gwtbootstrap3.client.ui.constants.IconType;

/**
 * Created by lyhcode on 2014/1/28.
 */
public class BookChapterSlider extends FlowPanel {

    ScrollPanel scrollPanel = new ScrollPanel();

    public BookChapterSlider() {
        //setAlwaysShowScrollBars(true);

        scrollPanel.setAlwaysShowScrollBars(true);


        HorizontalPanel panel = new HorizontalPanel();

        panel.add(new Label("章節 Chapter"));
        panel.add(new Button("垃圾桶"));
        panel.add(new Button("確認刪除"));

        add(panel);


        addChapters();




        FlowPanel panel1 = new FlowPanel();

        panel1.add(makeScrollLeftButton());
        panel1.add(makeScrollRightButton());
        panel1.add(scrollPanel);

        add(panel1);


    }

    private void addChapters() {
        FlowPanel chapters = new FlowPanel();

        DOM.setStyleAttribute(chapters.getElement(), "width", "3000px");

        chapters.add(new BookChapter("Chapter 1"));
        chapters.add(new BookChapter("Chapter 2"));
        chapters.add(new BookChapter("Chapter 3"));
        chapters.add(new BookChapter("Chapter 4"));
        chapters.add(new BookChapter("Chapter 5"));
        chapters.add(new BookChapter("Chapter 6"));
        chapters.add(new BookChapter("Chapter 7"));
        chapters.add(new BookChapter("Chapter 8"));
        chapters.add(new BookChapter("Chapter 9"));
        chapters.add(new BookChapter("Chapter 10"));
        chapters.add(new BookChapter("Chapter 11"));
        chapters.add(new BookChapter("Chapter 12"));
        chapters.add(new BookChapter("Chapter 13"));

        chapters.add(new BookChapter("+<br/>增加"));

        scrollPanel.add(chapters);
    }

    private Widget makeScrollLeftButton() {
        Button button = new Button();

        button.setIcon(IconType.CHEVRON_LEFT);
        button.setSize(ButtonSize.EXTRA_SMALL);

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                scrollPanel.scrollToLeft();
                //scrollPanel.setHorizontalScrollPosition(scrollPanel.getHorizontalScrollPosition()-100);
            }
        });

        DOM.setStyleAttribute(button.getElement(), "float", "left");
        DOM.setStyleAttribute(button.getElement(), "height", "100px");

        return button;
    }

    private Widget makeScrollRightButton() {
        Button button = new Button();

        button.setIcon(IconType.CHEVRON_RIGHT);
        button.setSize(ButtonSize.EXTRA_SMALL);

        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                scrollPanel.scrollToRight();
                //scrollPanel.setHorizontalScrollPosition(scrollPanel.getHorizontalScrollPosition()+100);
            }
        });

        DOM.setStyleAttribute(button.getElement(), "float", "right");
        DOM.setStyleAttribute(button.getElement(), "height", "100px");

        return button;
    }
}
