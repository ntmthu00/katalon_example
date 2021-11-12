package com.kms.example.rcp.ui.parts;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import com.kms.example.rcp.ui.constants.EventConstants;

public class AvatarPart extends ViewPart {
	private Image avatarImage;
	private Canvas canvas;

	@Inject
	private IEventBroker eventBroker;

	@PostConstruct
	public void createPartControl(Composite parent) {
		canvas = new Canvas(parent, SWT.BORDER);
		subscribeRowSelectionEvent(parent);
		canvas.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				canvas.addPaintListener(new PaintListener() {
					public void paintControl(PaintEvent e) {
						e.gc.drawImage(avatarImage, 0, 0);
					}
				});
				canvas.redraw();
			}

			@Override
			public void focusLost(FocusEvent e) {

			}

		});
	}

	private void subscribeRowSelectionEvent(Composite parent) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event e) {
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					String avatarFilePath = (String) e.getProperty(IEventBroker.DATA);
					if (avatarFilePath.substring(0, 7).equals("avatar/")) {
						Bundle bundle = Platform.getBundle("com.kms.example.rcp.core");
						URL url = FileLocator.find(bundle, new Path(avatarFilePath), null);
						ImageDescriptor imageDesc = ImageDescriptor.createFromURL(url);
						avatarImage = imageDesc.createImage();
					} else {
						try {
							URL url = new File(avatarFilePath).toURI().toURL();
							ImageDescriptor imageDesc = ImageDescriptor.createFromURL(url);
							avatarImage = imageDesc.createImage();
						} catch (MalformedURLException e1) {
							e1.printStackTrace();
						}
					}

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

	@Focus
	public void setFocus() {
		canvas.setFocus();
	}
}
