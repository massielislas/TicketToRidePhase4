package clientResult;

public interface Result
{
    public boolean isValid();
    public void setResult(boolean result);
    public void setMessage(String toSet);
    public String getMessage();
}