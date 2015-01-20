package edu.cmu.lti.bic.sbs.ui;

import com.googlecode.lanterna.gui.Interactable;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;

public class DescriptionTextBox extends TextBox {
	public DescriptionTextBox(String description) {
		super(description);
	}

	@Override
	public Interactable.Result keyboardInteraction(Key key) {
		Kind k = key.getKind();
		System.out.println(key.getCharacter());
		Interactable.Result result = Interactable.Result.EVENT_HANDLED;
		/*
		 * switch (k) { case Enter: result =
		 * Interactable.Result.EVENT_NOT_HANDLED; }
		 */
		return result;
	}
}
