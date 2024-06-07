package business.rulesets;

import librarysystem.AddNewMemberWindow;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;



final public class RuleSetFactory {
	private RuleSetFactory(){}
	static HashMap<Class<? extends Component>, RuleSet> map = new HashMap<>();
	static {
		map.put(AddNewMemberWindow.class, new AddMemberRuleSet());
	}

	public static RuleSet getRuleSet(Component c) {
		Class<? extends Component> cl = c.getClass();
		if(!map.containsKey(cl)) {
			throw new IllegalArgumentException(
					"No RuleSet found for this Component");
		}
		return map.get(cl);
	}

	public static boolean isFieldsHasEmptyValue(JTextField field) {
		return field.getText().isEmpty();
	}


}
