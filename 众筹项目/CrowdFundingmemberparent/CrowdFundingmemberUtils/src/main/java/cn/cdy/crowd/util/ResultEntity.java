package cn.cdy.crowd.util;

public class ResultEntity<T> {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    private String result;
    private String message;
    private T data;

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public ResultEntity() {
    }
    //成功无消息返回
    public static <E> ResultEntity<E> successWithoutData(){
        return new ResultEntity<E>(SUCCESS,null,null);
    }
    //成功有消息返回
    public static <E> ResultEntity<E> successWithData(E data){
        return new ResultEntity<E>(SUCCESS,null,data);
    }
    //失败
    public static <E> ResultEntity<E> fail(String message){
        return new ResultEntity<E>(FAILED,message,null);
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
