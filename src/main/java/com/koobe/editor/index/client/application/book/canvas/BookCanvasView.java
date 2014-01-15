package com.koobe.editor.index.client.application.book.canvas;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.drop.FlowPanelDropController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.koobe.editor.index.client.application.upload.UploadBundle;
import org.gwtbootstrap3.client.ui.TextArea;

public class BookCanvasView extends ViewImpl implements BookCanvasPresenter.MyView {

    interface Binder extends UiBinder<Widget, BookCanvasView> {
    }

    final BookCanvasBundle res = GWT.create(BookCanvasBundle.class);

    private final PlaceManager placeManager;

    private PickupDragController dragController;

    @UiField
    AbsolutePanel canvas;

    @UiField
    InlineHTML textWidget;

    @UiField
    InlineHTML imageWidget;

    @Inject
    public BookCanvasView(Binder binder, PlaceManager placeManager) {
        this.placeManager = placeManager;

        res.style().ensureInjected();

        initWidget(binder.createAndBindUi(this));

        initUiField();

        initDragAndDrop();

        GWT.log("loaded");
    }

    private void initUiField() {

        canvas.getElement().getStyle().setOverflow(Style.Overflow.AUTO);



        textWidget.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {


                final FocusPanel widget = new FocusPanel();
                widget.addStyleName(res.style().widget());

                final HorizontalPanel heading = new HorizontalPanel();
                final Label label = new Label("widget-text");
                label.setStyleName(res.style().widgetHeading());
                label.setWidth("200px");

                heading.add(label);

                final Button editButton = new Button("Edit");
                heading.add(editButton);

                final Button removeButton = new Button("Remove");
                heading.add(removeButton);

                final HTML html = new HTML("<p>Gmail developers didn’t invent anything new; they used what was already available. In Gmail the development team made use of what’s now referred to as XHR or XMLHttpRequest. XHR is an API created by Microsoft that allows JavaScript to run in the browser to initiate direct communication with the server. Gmail was designed to use this tool, which was available in all major browsers, to change the paradigm of how we interact with websites in a visual and forceful way.</p>");

                //widget.add(heading);
                widget.add(html);

                heading.setVisible(false);

                final VerticalPanel panel = new VerticalPanel();

                panel.add(heading);
                panel.add(widget);

                editButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        String result = Window.prompt("change to", html.getHTML());
                        html.setHTML(result);
                    }
                });

                removeButton.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        canvas.remove(panel);
                    }
                });

                widget.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent event) {
                        heading.setVisible(!heading.isVisible());
                    }
                });

                widget.addDoubleClickHandler(new DoubleClickHandler() {
                    @Override
                    public void onDoubleClick(DoubleClickEvent event) {

                    }
                });

                widget.addMouseOverHandler(new MouseOverHandler() {
                    @Override
                    public void onMouseOver(MouseOverEvent mouseOverEvent) {
                        widget.addStyleName(res.style().widgetMouseOver());
                    }
                });

                widget.addMouseOutHandler(new MouseOutHandler() {
                    @Override
                    public void onMouseOut(MouseOutEvent mouseOutEvent) {
                        widget.removeStyleName(res.style().widgetMouseOver());
                    }
                });

                widget.addMouseDownHandler(new MouseDownHandler() {
                    @Override
                    public void onMouseDown(MouseDownEvent mouseDownEvent) {
                        //dragController.makeDraggable(widget);
                        //widget.addStyleName(res.style().widgetMouseDown());
                    }
                });
                widget.addMouseUpHandler(new MouseUpHandler() {
                    @Override
                    public void onMouseUp(MouseUpEvent mouseUpEvent) {
                        //dragController.makeNotDraggable(widget);
                        //widget.removeStyleName(res.style().widgetMouseDown());
                    }
                });

                canvas.add(panel);

                dragController.makeDraggable(panel, label);
            }
        });

        imageWidget.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                final Image image = new Image("http://www.team-bhp.com/forum/attachments/shifting-gears/1033178d1356977973-official-non-auto-image-thread-_mg_0143.jpg");
                //image.setWidth("100px");
                image.setHeight("200px");
                final FocusPanel widget = new FocusPanel(image);
                widget.addStyleName(res.style().widget());

                widget.addDoubleClickHandler(new DoubleClickHandler() {
                    @Override
                    public void onDoubleClick(DoubleClickEvent event) {
                        String result = Window.prompt("change to", image.getUrl());
                        image.setUrl(result);
                    }
                });

                canvas.add(widget);
            }
        });
    }

    private void makeTextWidget() {

    }

    private void makeImageWidget() {

    }

    private void initDragAndDrop() {
        dragController = new PickupDragController(canvas, true);
        dragController.setBehaviorMultipleSelection(false);
        //FlowPanelDropController dropController = new FlowPanelDropController(canvas);
    }
}
