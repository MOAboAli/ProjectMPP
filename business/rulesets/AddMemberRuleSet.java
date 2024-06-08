package business.rulesets;

import librarysystem.AddNewMemberWindow;

import javax.swing.*;
import java.awt.*;



public class AddMemberRuleSet implements RuleSet {

    @Override
    public void applyRules(Component ob) throws RuleException {
        AddNewMemberWindow addNewMemberWindow = (AddNewMemberWindow) ob;
        for (JTextField field: addNewMemberWindow.getAllFields()){
            if (RuleSetFactory.isFieldsHasEmptyValue(field))
                throw new RuleException("All Field Can't be Empty");
        }
        if (!isMemberIDValid(addNewMemberWindow.getMemberIDField().getText()))
            throw new RuleException("Member ID Not Valid");
    }



    private boolean isMemberIDValid(String memberID){
        return memberID.matches("-?\\d+");
    }







}
