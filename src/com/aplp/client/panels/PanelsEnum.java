package com.aplp.client.panels;

import com.aplp.client.panels.bodyPanels.Panel_CategoryList;
import com.aplp.client.panels.bodyPanels.Panel_Login;
import com.aplp.client.panels.bodyPanels.Panel_Topic;
import com.aplp.client.panels.bodyPanels.Panel_TopicCreation;
import com.aplp.client.panels.bodyPanels.Panel_TopicList;

/**
 * Panel enum
 * Contains all the panels and a binding to theirs classes (Habile ! Et tu ne diras pas le contraire, ni "pas le contraire")
 */
public enum PanelsEnum {
	PANEL_LOGIN {
		@Override
		public Panel getPanel() {
			return Panel_Login.getInstance();
		}
	},
	PANEL_CATEGORY_LIST {
		@Override
		public Panel getPanel() {
			return Panel_CategoryList.getInstance();
		}
	},
	PANEL_TOPIC_LIST {
		@Override
		public Panel getPanel() {
			return Panel_TopicList.getInstance();
		}
	},
	PANEL_TOPIC {
		@Override
		public Panel getPanel() {
			return Panel_Topic.getInstance();
		}
	},
	PANEL_TOPIC_CREATION {
		@Override
		public Panel getPanel() {
			return Panel_TopicCreation.getInstance();
		}
	};

	/**
	 * Overrided by the panel enum object
	 * @return
	 */
	public Panel getPanel() {
		return null;
	}
}
