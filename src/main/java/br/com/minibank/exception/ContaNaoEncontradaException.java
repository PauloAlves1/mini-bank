package br.com.minibank.exception;

public class ContaNaoEncontradaException extends Exception{

    private static final long serialVersionUID = -7139304880555402679L;

    public ContaNaoEncontradaException(){
        super();
    }

    public ContaNaoEncontradaException(String msg){
        super(msg);
    }

    public ContaNaoEncontradaException(String msg, Throwable cause){
        super(msg, cause);
    }
}
