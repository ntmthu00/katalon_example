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
import org.eclipse.swt.widgets.TableColumn;

public class UsersPart {
	private TableViewer usersTableViewer;
	private Button btnAdd;
	private Button btnDelete;
	private Button btnUpdate;

	@PostConstruct
	public void createControls(Composite parent) {
		parent.setLayout(new GridLayout(1, true));

//		Composite tableComposite = new Composite(parent, SWT.NONE);
//		TableColumnLayout tableLayout = new TableColumnLayout();
//		tableComposite.setLayout(tableLayout);

		createUsersTableViewer(parent);

		Composite btnComposite = new Composite(parent, SWT.NONE);
		btnComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		btnComposite.setLayout(new RowLayout());

		btnAdd = new Button(btnComposite, SWT.CENTER);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.print("Add button pressed\n");
			}
		});
		btnAdd.setText("Add");

		btnDelete = new Button(btnComposite, SWT.CENTER);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.print("Delete button pressed\n");
			}
		});
		btnDelete.setText("Delete");

		btnUpdate = new Button(btnComposite, SWT.CENTER);
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.print("Update button pressed\n");
			}
		});
		btnUpdate.setText("Update");
	}

	private void createUsersTableViewer(Composite parent) {
		usersTableViewer = new TableViewer(parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		createColumns(parent, usersTableViewer);
		final Table usersTable = usersTableViewer.getTable();
		usersTable.setHeaderVisible(true);
		usersTable.setLinesVisible(true);

		usersTableViewer.setContentProvider(new ArrayContentProvider());

		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		usersTableViewer.getControl().setLayoutData(gridData);
	}

	private void createColumns(final Composite parent, final TableViewer usersTableViewer) {
		String[] titles = { "ID", "Username", "DoB", "Gender" };
		int[] bounds = { 100, 100, 100, 100 };

		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col = createTableViewerColumn(titles[3], bounds[3], 3);
	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(usersTableViewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}

	@Focus
	public void setFocus() {
		usersTableViewer.getControl().setFocus();
	}
}
