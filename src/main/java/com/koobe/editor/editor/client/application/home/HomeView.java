package com.koobe.editor.editor.client.application.home;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.query.client.Function;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.koobe.editor.editor.client.helper.RandomText;
import com.koobe.editor.widget.client.ui.*;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.extras.bootbox.client.Bootbox;

import static com.google.gwt.query.client.GQuery.$;

public class HomeView extends ViewImpl implements HomePresenter.MyView {

    interface Binder extends UiBinder<Widget, HomeView> {
    }

    final HomeBundle res = GWT.create(HomeBundle.class);

    private final PlaceManager placeManager;

    private PickupDragController dragController;

    @UiField
    BookCanvas canvas;

    @Inject
    public HomeView(Binder binder, PlaceManager placeManager) {
        this.placeManager = placeManager;

        initWidget(binder.createAndBindUi(this));

        initUiField();

        initDragAndDrop();

        //new org.gwtbootstrap3.client.ui.Button().setEnabled();
    }

    private void initUiField() {

        canvas.getElement().getStyle().setOverflow(Style.Overflow.AUTO);

        /*
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
        */
    }

    @UiHandler("showHTMLButton")
    void showHTML(ClickEvent event) {
        Bootbox.alert("<pre>" + SafeHtmlUtils.htmlEscape(canvas.getWidgetsAsString()) + "</pre>");
    }

    @UiHandler("titleWidget")
    void createTitleWidget(ClickEvent event) {
        TitleWidget.SIZE size = TitleWidget.SIZE.H1;

        int widgetCount = canvas.getWidgetCount();

        for (int i = 0; i < widgetCount; i++) {
            Widget widget = canvas.getWidget(i);

            if (widget instanceof TitleWidget) {
                TitleWidget titleWidget;
                titleWidget = (TitleWidget)widget;

                if (titleWidget.getSize().equals(TitleWidget.SIZE.H1)) {
                    size = TitleWidget.SIZE.H2;
                }
                else {
                    size = titleWidget.getSize();
                }
            }
        }

        addWidgetToCanvas(new TitleWidget("A Big Title", size));
    }

    @UiHandler("textWidget")
    void createTextWidget(ClickEvent event) {
        addWidgetToCanvas(new TextWidget(RandomText.getContent()));
    }

    @UiHandler("codeWidget")
    void createCodeWidget(ClickEvent event) {
        addWidgetToCanvas(new CodeWidget("public class Main {\n  String name;\n}\n"));
    }

    @UiHandler("imageWidget")
    void createImageWidget(ClickEvent event) {
        String src = "http://www.team-bhp.com/forum/attachments/shifting-gears/1033178d1356977973-official-non-auto-image-thread-_mg_0143.jpg";

        addWidgetToCanvas(new ImageWidget(src, ImageWidget.ALIGN.CENTER));
    }

    @UiHandler("quoteWidget")
    void createQuoteWidget(ClickEvent event) {
        addWidgetToCanvas(new QuoteWidget(RandomText.getContent(2)));
    }

    @UiHandler("youtubeWidget")
    void createYoutubeWidget(ClickEvent event) {
        addWidgetToCanvas(new YoutubeWidget("ZKEaypYJbb4"));
        /**
         * <iframe width="560" height="315" src="//www.youtube.com/embed/ZKEaypYJbb4" frameborder="0" allowfullscreen></iframe>
         */
    }

    @UiHandler("audioWidget")
    void createAudioWidget(ClickEvent event) {
        addWidgetToCanvas(new AudioWidget("http://hpr.dogphilosophy.net/test/mp3.mp3"));
    }

    private void addWidgetToCanvas(final Widget widget) {
        widget.setVisible(false);

        $(widget).fadeIn(new Function() {
            @Override
            public void f() {
                //widget.setVisible(true);
            }
        });

        canvas.add(widget);

        Window.scrollTo(0, widget.getElement().getAbsoluteTop() - 150);
    }

    @UiHandler("mainPanel")
    void cancelAllEditableWidget(ClickEvent event) {
        GWT.log("cancelAllEditableWidget()");

        AbstractWidget widget = AbstractWidget.getActiveEditableWidget();

        if (widget != null) {
            widget.setEditable(false);
        }
    }

    private void initDragAndDrop() {
        dragController = new PickupDragController(canvas, true);
        dragController.setBehaviorMultipleSelection(false);
        //FlowPanelDropController dropController = new FlowPanelDropController(canvas);
    }

    public native void scrollIntoView(Element elem) /*-{
        var left = elem.offsetLeft, top = elem.offsetTop;
        var width = elem.offsetWidth, height = elem.offsetHeight;

        if (elem.parentNode != elem.offsetParent) {
            left -= elem.parentNode.offsetLeft;
            top -= elem.parentNode.offsetTop;
        }

        var cur = elem.parentNode;
        while (cur && (cur.nodeType == 1)) {
            if (left < cur.scrollLeft) {
                cur.scrollLeft = left;
            }
            if (left + width > cur.scrollLeft + cur.clientWidth) {
                cur.scrollLeft = (left + width) - cur.clientWidth;
            }
            if (top < cur.scrollTop) {
                cur.scrollTop = top;
            }
            if (top + height > cur.scrollTop + cur.clientHeight) {
                cur.scrollTop = (top + height) - cur.clientHeight;
            }

            var offsetLeft = cur.offsetLeft, offsetTop = cur.offsetTop;
            if (cur.parentNode != cur.offsetParent) {
                offsetLeft -= cur.parentNode.offsetLeft;
                offsetTop -= cur.parentNode.offsetTop;
            }

            left += offsetLeft - cur.scrollLeft;
            top += offsetTop - cur.scrollTop;
            cur = cur.parentNode;
        }
    }-*/;
}
