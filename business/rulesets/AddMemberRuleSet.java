package business.rulesets;

import librarysystem.AddNewMemberWindow;

import javax.swing.*;
import java.awt.*;


/**
 * Rules:
 * 1. All fields must be nonempty
 * 2. Isbn must be numeric and consist of either 10 or 13 characters
 * 3. If Isbn has length 10, the first digit must be 0 or 1
 * 4. If Isbn has length 13, the first 3 digits must be either 978 or 979
 * 5. Price must be a floating point number with two decimal places
 * 6. Price must be a number greater than 0.49.
 */
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

//    private boolean isIsbnValid(String isbn) {
//        String isbn10Pattern = "^[01]\\d{9}$";
//        String isbn13Pattern = "^(978|979)\\d{10}$";
//        return isbn.matches(isbn10Pattern) || isbn.matches(isbn13Pattern);
//    }





}
