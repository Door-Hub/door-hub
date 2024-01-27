package org.example.doorhub.attachment;

public class AttachmentNotFound extends RuntimeException {
    public AttachmentNotFound(String message) {
        super(message);
    }
}
