package ru.study.validation;

import java.util.HashSet;
import java.util.Set;

public class ValidationResult {
    private boolean isValid = true;
    private final Set<String> messages = new HashSet<>();


    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Set<String> getMessages() {
        return messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }

    public void merge(ValidationResult vr){
        this.isValid = vr.isValid();
        messages.addAll(vr.getMessages());
    }
}
