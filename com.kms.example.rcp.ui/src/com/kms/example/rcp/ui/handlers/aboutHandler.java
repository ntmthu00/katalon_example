package com.kms.example.rcp.ui.handlers;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.e4.core.di.annotations.Execute;

public class aboutHandler {
	@Execute
	public void execute(Shell shell) {
		MessageDialog.openInformation(shell, "About", "Sample Eclipse RCP application\nAuthor: Thu Nguyen\nVersion: 1.0.0");
	}
		
}