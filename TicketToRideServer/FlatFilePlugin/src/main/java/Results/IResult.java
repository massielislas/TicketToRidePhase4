package Results;

/**
 * Created by madeleineaydelotte on 5/17/18.
 */

public interface IResult {
    public boolean isSuccess();

    public void setSuccess(boolean success);

    public void setMessage(String message);
}
