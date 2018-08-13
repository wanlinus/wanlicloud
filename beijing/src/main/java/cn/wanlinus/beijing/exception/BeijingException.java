package cn.wanlinus.beijing.exception;

/**
 * @author wanli
 * @date 2018-08-10 13:58
 */
public class BeijingException extends RuntimeException {
    private String msg;

    public BeijingException( String msg) {
        super(msg);
        this.msg = msg;
    }
}
