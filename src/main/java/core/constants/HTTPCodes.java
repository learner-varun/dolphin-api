package core.constants;

public class HTTPCodes {
    //todo - Need to add more HTTP response codes

    // 1XX - Informational
    public static final int CONTINUE = 100;
    public static final int SWITCHING_PROTOCOLS = 101;
    public static final int PROCESSING = 102;

    //2XX - Success
    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int ACCEPTED = 202;
    public static final int NO_CONTENT = 204;

    //3XX - Redirection
    public static final int MULTIPLE_CHOICES = 300;
    public static final int MOVED_PERMANENTLY = 301;
    public static final int FOUND = 302;

    //4XX - Client Error
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int PAYMENT_REQUIRED = 402;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;

    //5XX - Server Error
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int NOT_IMPLEMENTED = 501;
    public static final int BAD_GATEWAY = 502;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int GATEWAY_TIMEOUT = 504;
    public static final int HTTP_VERSION_NOT_SUPPORTED = 505;

    //Add custom response codes here
    public static final int CUSTOM_HTTP_CODE = 702;


}
