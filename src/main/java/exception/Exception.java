package exception;

public class Exception {

    public static class NumberOutOfScopeException extends RuntimeException {
        public NumberOutOfScopeException (String message){
            super(message);
        }
        public NumberOutOfScopeException(){
            super("Your number does not match the menu choice");
        }
    }

}
