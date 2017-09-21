package net.spark.ui.view.admin;

import net.spark.backend.data.Role;
import com.vaadin.ui.ComboBox;

public class RoleSelect extends ComboBox<String> {

	public RoleSelect() {
		setCaption("Role");
		setEmptySelectionAllowed(false);
		setItems(Role.getAllRoles());
		setTextInputAllowed(false);
	}
}
