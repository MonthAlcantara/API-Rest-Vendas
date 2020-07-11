package io.github.monthalcantara.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(){
        super("Invalid Password");
    }

}
