package api.aps.exception;

import java.time.LocalDateTime;

public class ValidationExceptionDetails extends ExceptionDetails {

    private final String fields;
    private final String fieldsMessage;

    public ValidationExceptionDetails(String titulo, int status, String detalhes, String mensagemDev, LocalDateTime timestamp, String fields, String fieldsMessage) {
        super(titulo, status, detalhes, mensagemDev, timestamp);
        this.fields = fields;
        this.fieldsMessage = fieldsMessage;
    }

    public String getFields() {
        return fields;
    }

    public String getFieldsMessage() {
        return fieldsMessage;
    }
}
