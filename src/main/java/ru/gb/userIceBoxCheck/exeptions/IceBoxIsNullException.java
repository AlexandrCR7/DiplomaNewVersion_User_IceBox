package ru.gb.userIceBoxCheck.exeptions;

public class IceBoxIsNullException extends RuntimeException{
    public IceBoxIsNullException(String message) {
        super(message);
    }
}
