package com.kms.example.rcp.ui.contribute.handlers;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;

import com.kms.example.rcp.ui.constants.EventConstants;

public class ZoomHandler {
	@Inject
	private IEventBroker eventBroker;

	@Execute
	public void execute() {
		eventBroker.send(EventConstants.TOPIC_AVATAR_ZOOM, "");
	}
}
