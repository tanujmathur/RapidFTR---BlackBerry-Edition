package com.rapidftr.controllers;

import com.rapidftr.datastore.FormStore;
import com.rapidftr.model.Child;
import com.rapidftr.screens.ChildCreateUpdateScreen;
import com.rapidftr.screens.SnapshotScreen;
import com.rapidftr.screens.UiStack;
import com.rapidftr.services.ChildStoreService;
import com.rapidftr.utilities.ImageCaptureListener;

public class ChildCreateUpdateController extends Controller {

	private final FormStore formStore;

	private final ChildStoreService childStoreService;
	public ChildCreateUpdateController(ChildCreateUpdateScreen screen, UiStack uiStack,
			FormStore formStore, ChildStoreService childStoreService) {
		super(screen, uiStack);
		this.formStore = formStore;
		this.childStoreService = childStoreService;
	}

	public void synchronizeForms() {
		dispatcher.synchronizeForms();
	}

	public void show() {
		((ChildCreateUpdateScreen) screen).setForms(formStore.getForms());
		super.show();
	}
	

	public void showEditScreenForChild(Child child) {
		((ChildCreateUpdateScreen) screen).setEditForms(formStore.getForms(),child);
		super.show();
	}

	public void takeSnapshotAndUpdateWithNewImage(
			ImageCaptureListener imageCaptureListener) {

		SnapshotController snapshotController = new SnapshotController(
				new SnapshotScreen(), uiStack);
		snapshotController.show();
		snapshotController.setImageListener(imageCaptureListener);
	}

	public void saveChild(Child child) {
		childStoreService.syncChildWithStore(child);
	}

}
