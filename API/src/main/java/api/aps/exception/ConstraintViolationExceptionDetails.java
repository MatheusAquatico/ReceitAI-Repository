package api.aps.exception;

import java.time.LocalDateTime;

public class ConstraintViolationExceptionDetails extends ExceptionDetails {

    public ConstraintViolationExceptionDetails(String titulo, int status, String detalhes, String mensagemDev, LocalDateTime timestamp) {
        this.titulo = titulo;
        this.status = status;
        this.detalhes = detalhes;
        this.mensagemDev = mensagemDev;
        this.timestamp = timestamp;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getMensagemDev() {
        return mensagemDev;
    }

    public void setMensagemDev(String mensagemDev) {
        this.mensagemDev = mensagemDev;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
