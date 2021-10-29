package com.kms.example.rcp.ui.parts;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class UsersPart {
	private TableViewer usersTableViewer;

	@PostConstruct
	public void createControls(Composite parent) {
		parent.setLayout(new GridLayout(1, true));

		usersTableViewer = new TableViewer(parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		Table usersTable = usersTableViewer.getTable();
		usersTable.setHeaderVisible(true);
		usersTable.setLinesVisible(true);
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		usersTableViewer.getControl().setLayoutData(gridData);
		usersTableViewer.setContentProvider(ArrayContentProvider.getInstance());

		TableViewerColumn colID = new TableViewerColumn(usersTableViewer, SWT.NONE);
		colID.getColumn().setWidth(60);
		colID.getColumn().setText("ID");
		colID.getColumn().setResizable(true);
		colID.getColumn().setMoveable(true);

		TableViewerColumn colUsername = new TableViewerColumn(usersTableViewer, SWT.NONE);
		colUsername.getColumn().setWidth(100);
		colUsername.getColumn().setText("Username");
		colUsername.getColumn().setResizable(true);
		colUsername.getColumn().setMoveable(true);

		TableViewerColumn colDoB = new TableViewerColumn(usersTableViewer, SWT.NONE);
		colDoB.getColumn().setWidth(100);
		colDoB.getColumn().setText("Date of Birth");
		colDoB.getColumn().setResizable(true);
		colDoB.getColumn().setMoveable(true);

		TableViewerColumn colGender = new TableViewerColumn(usersTableViewer, SWT.NONE);
		colGender.getColumn().setWidth(100);
		colGender.getColumn().setText("Gender");
		colGender.getColumn().setResizable(true);
		colGender.getColumn().setMoveable(true);

		final Composite btnLayout = new Composite(parent, SWT.NONE);
		btnLayout.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnLayout.setLayout(new RowLayout());

		Button btnAdd = new Button(btnLayout, SWT.CENTER);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.print("Add button pressed\n");
			}
		});
		btnAdd.setText("Add");

		Button btnDelete = new Button(btnLayout, SWT.CENTER);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.print("Delete button pressed\n");
			}
		});
		btnDelete.setText("Delete");

		Button btnUpdate = new Button(btnLayout, SWT.CENTER);
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.print("Update button pressed\n");
			}
		});
		btnUpdate.setText("Update");
	}

	@Focus
	public void setFocus() {
		usersTableViewer.getControl().setFocus();
	}
}
