package com.kms.example.rcp.ui.parts;

import java.net.URL;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.Bundle;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import com.kms.example.rcp.ui.constants.EventConstants;

public class AvatarPart {
	private Image avatarImage;
	private Canvas canvas;

	@Inject
	private IEventBroker eventBroker;

	@PostConstruct
	public void createControls(Composite parent) {
		canvas = new Canvas(parent, SWT.BORDER);

		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event e) {
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					String avatarFilePath = (String) e.getProperty(IEventBroker.DATA);
					Bundle bundle = Platform.getBundle("com.kms.example.rcp.core");
					URL url = FileLocator.find(bundle, new Path(avatarFilePath), null);
					ImageDescriptor imageDesc = ImageDescriptor.createFromURL(url);
					avatarImage = imageDesc.createImage();

					canvas.addPaintListener(new PaintListener() {
						public void paintControl(PaintEvent e) {
							Rectangle imgBounds = avatarImage.getBounds();
							Rectangle canvasBounds = canvas.getBounds();
							e.gc.drawImage(avatarImage, 0, 0, imgBounds.width, imgBounds.height, 0, 0,
									canvasBounds.width, canvasBounds.height);
						}
					});
					canvas.redraw();
				}
			}
		};

		eventBroker.subscribe(EventConstants.TOPIC_ROW_SELECTION_AVATAR, handler);
	}
}
