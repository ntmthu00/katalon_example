package com.kms.example.rcp.ui.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import com.kms.example.rcp.ui.constants.EventConstants;
import com.kms.example.rcp.ui.constants.MessageConstants;

public class WelcomePart {
	private String welcomeMsg = MessageConstants.WelcomePart_BASE_MSG;
	private Canvas canvas;

	@Inject
	private IEventBroker eventBroker;

	@PostConstruct
	public void createControls(Composite parent) {
		parent.setLayout(new FillLayout());

		canvas = new Canvas(parent, SWT.NONE);

		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event e) {
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					String username = (String) e.getProperty(IEventBroker.DATA);
					welcomeMsg += username + "!";
					System.out.println(welcomeMsg);
					canvas.addPaintListener(new PaintListener() {
						public void paintControl(PaintEvent e) {
							e.gc.drawString(welcomeMsg, 0, 0);
						}
					});
				}
			}
		};

		eventBroker.subscribe(EventConstants.TOPIC_ROW_SELECTION_WELCOME, handler);
	}
}
