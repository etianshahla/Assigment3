package common;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;


public class NumberTextField extends TextField{

	   public NumberTextField() {
	      super();

	      addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
	         @Override
	         public void handle(KeyEvent event) {
	            if (!isValid(getText())) {
	               event.consume();
	            }
	         }
	      });

	      textProperty().addListener(new ChangeListener<String>() {
	         @Override
	         public void changed(ObservableValue<? extends String> observableValue,
	                             String oldValue, String newValue) {
	            if(!isValid(newValue)) {
	               setText(oldValue);
	            }
	         }
	      });
	   }

	   private boolean isValid(final String value) {
	      if (value.length() == 0 || value.equals("-")) {
	         return true;
	      }

	      try {
	         Integer.parseInt(value);
	         return true;
	      } catch (NumberFormatException ex) {
	         return false;
	      }
	   }

	   public int getInt() {
	      try {
	         return Integer.parseInt(getText());
	      }
	      catch (NumberFormatException e) {
	    	  Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText("please set only numbers");
				alert.show();	      }
		return 0;
	   }}
