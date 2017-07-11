package net.jiniguru.roshan.ui.view.admin.user;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import net.jiniguru.roshan.backend.data.entity.User;
import net.jiniguru.roshan.backend.service.UserService;
import net.jiniguru.roshan.ui.navigation.NavigationManager;
import net.jiniguru.roshan.ui.view.admin.AbstractCrudPresenter;

@SpringComponent
@ViewScope
public class UserAdminPresenter extends AbstractCrudPresenter<User, UserService, UserAdminView>
		implements Serializable {

	@Autowired
	public UserAdminPresenter(UserAdminDataProvider userAdminDataProvider, NavigationManager navigationManager,
			UserService service) {
		super(navigationManager, service, userAdminDataProvider);
	}

	public String encodePassword(String value) {
		return getService().encodePassword(value);
	}

	@Override
	protected void editItem(User item) {
		super.editItem(item);
		getView().setPasswordRequired(item.isNew());
	}
}