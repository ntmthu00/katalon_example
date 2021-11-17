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
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import com.kms.example.rcp.ui.constants.EventConstants;

public class AvatarPart extends ViewPart {
	private Image avatarImage;
	private Canvas canvas;
	private Point origin = new Point(0, 0);

	@Inject
	private IEventBroker eventBroker;

	@PostConstruct
	public void createPartControl(Composite parent) {
		canvas = new Canvas(parent, SWT.BORDER | SWT.MULTI | SWT.NO_REDRAW_RESIZE | SWT.V_SCROLL | SWT.H_SCROLL);
		final ScrollBar hBar = canvas.getHorizontalBar();
		hBar.addListener(SWT.Selection, e -> {
			if (avatarImage != null) {
				int hSelection = hBar.getSelection();
				int destX = -hSelection - origin.x;
				Rectangle imgBounds = avatarImage.getBounds();
				canvas.scroll(destX, 0, 0, 0, imgBounds.width, imgBounds.height, false);
				origin.x = -hSelection;
			}
		});
		final ScrollBar vBar = canvas.getVerticalBar();
		vBar.addListener(SWT.Selection, e -> {
			if (avatarImage != null) {
				int vSelection = vBar.getSelection();
				int destY = -vSelection - origin.y;
				Rectangle imgBounds = avatarImage.getBounds();
				canvas.scroll(0, destY, 0, 0, imgBounds.width, imgBounds.height, false);
				origin.y = -vSelection;
			}
		});

		canvas.addListener(SWT.Resize, e -> {
			if (avatarImage != null) {
				Rectangle imgBounds = avatarImage.getBounds();
				Rectangle client = canvas.getClientArea();
				hBar.setMaximum(imgBounds.width);
				vBar.setMaximum(imgBounds.height);
				hBar.setThumb(Math.min(imgBounds.width, client.width));
				vBar.setThumb(Math.min(imgBounds.height, client.height));
				int hPage = imgBounds.width - client.width;
				int vPage = imgBounds.height - client.height;
				int hSelection = hBar.getSelection();
				int vSelection = vBar.getSelection();
				if (hSelection >= hPage) {
					if (hPage <= 0)
						hSelection = 0;
					origin.x = -hSelection;
				}
				if (vSelection >= vPage) {
					if (vPage <= 0)
						vSelection = 0;
					origin.y = -vSelection;
				}
				canvas.redraw();
			}
		});
		subscribeRowSelectionEvent(parent);
		subscribeZoomAvatarEvent(parent);
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

	private void subscribeZoomAvatarEvent(Composite parent) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event e) {
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					canvas.addPaintListener(new PaintListener() {
						public void paintControl(PaintEvent e) {
							Rectangle canvasBounds = canvas.getBounds();
							e.gc.fillRectangle(canvasBounds);

							e.gc.drawImage(avatarImage, origin.x, origin.y);
							Rectangle imgBounds = avatarImage.getBounds();
							Rectangle client = canvas.getClientArea();
							int marginWidth = client.width - imgBounds.width;
							if (marginWidth > 0) {
								e.gc.fillRectangle(imgBounds.width, 0, marginWidth, client.height);
							}
							int marginHeight = client.height - imgBounds.height;
							if (marginHeight > 0) {
								e.gc.fillRectangle(0, imgBounds.height, client.width, marginHeight);
							}
						}
					});
					canvas.redraw();
				}
			}
		};
		eventBroker.subscribe(EventConstants.TOPIC_AVATAR_ZOOM, handler);
	}

	@Focus
	public void setFocus() {
		canvas.setFocus();
	}
}
