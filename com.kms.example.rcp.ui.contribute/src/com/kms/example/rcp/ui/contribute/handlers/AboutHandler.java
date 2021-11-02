package com.kms.example.rcp.ui.contribute.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.kms.example.rcp.ui.contribute.constants.MessageConstants;

public class AboutHandler {
	@Execute
	public void execute(Shell shell) {
		MessageDialog.openInformation(shell, MessageConstants.AboutHandler_ABOUT_TITLE,
				MessageConstants.AboutHandler_MSG_ABOUT);
	}
}