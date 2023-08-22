package communication;
import java.io.Serializable;

/**
*
* @author Saki
*/

public class Response implements Serializable{

	private Object result;
    private Exception exception;
    private StatusOdgovora status;

    public Response() {
    }

    public Response(Object result, Exception exception, StatusOdgovora status) {
        this.result = result;
        this.exception = exception;
        this.status = status;
    }

    

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public StatusOdgovora getStatus() {
        return status;
    }

    public void setStatus(StatusOdgovora status) {
        this.status = status;
    }
    
}
