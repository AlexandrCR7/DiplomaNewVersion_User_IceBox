package ru.gb.userIceBoxCheck.exeptions;

public class IceBoxNotFoundException extends RuntimeException{
    public IceBoxNotFoundException(String message){
        super(message);
    }
}
