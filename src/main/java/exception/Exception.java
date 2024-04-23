package exception;

public class Exception {

    public static class NumberOutOfScopeException extends RuntimeException {
        public NumberOutOfScopeException (String message){
            super(message);
        }
        public NumberOutOfScopeException(){
            super("Your number does not match the menu choice.Write again");
        }
    }
    public static class NonExistentFlightException extends RuntimeException {
        public NonExistentFlightException (String message){
            super(message);
        }
        public NonExistentFlightException(){
            super("There is no flight with that number");
        }
    }
    public static class NonExistentOrderException extends RuntimeException {
        public NonExistentOrderException (String message){
            super(message);
        }
        public NonExistentOrderException(){
            super("You don`t have orders");
        }
    }
}
