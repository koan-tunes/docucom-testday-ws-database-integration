package com.docu.person;

import com.docu.main.DEFS;

public class PersonsHandlerResult {
	int _action = -1;

	public void setAction(int action) {
		_action = action;
	}

	public boolean isNew() {
		return _action == DEFS.ACTION_NEW;
	}

	public boolean isUpdated() {
		return _action == DEFS.ACTION_UPDATED;
	}
}
