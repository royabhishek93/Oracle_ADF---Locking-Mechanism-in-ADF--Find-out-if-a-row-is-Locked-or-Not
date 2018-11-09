package com.abhishek.view;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import oracle.adf.model.BindingContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class RowLockCheckBean {
    public RowLockCheckBean() {
    }

    public String editRecordACTION() {
        // Add event code here...
        OperationBinding binding = this.getBindings().getOperationBinding("isRowLocked");
        binding.execute();
        System.out.println("Locked: "+binding.getResult());
        if(binding.getResult().equals((Object)true)){
            FacesMessage msg= new FacesMessage("Sorry, You cannot edit the current record as it is being edited by other user.");
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return null;
        }
        return "edit";
    }
    
    /**Method to get Binding Container*/
    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
}
