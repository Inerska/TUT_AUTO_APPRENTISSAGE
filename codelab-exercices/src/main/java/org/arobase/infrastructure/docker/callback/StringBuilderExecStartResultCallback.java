package org.arobase.infrastructure.docker.callback;

import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.async.ResultCallbackTemplate;
import com.github.dockerjava.api.model.Frame;

import java.io.Closeable;

public class StringBuilderExecStartResultCallback extends ResultCallbackTemplate<StringBuilderExecStartResultCallback, Frame> {

    private final StringBuilder stringBuilder;

    public StringBuilderExecStartResultCallback(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public void onStart(Closeable closeable) {
    }

    @Override
    public void onNext(Frame frame) {
        stringBuilder.append(new String(frame.getPayload()));
    }

    @Override
    public void onError(Throwable throwable) {
        stringBuilder.append(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        // Handle completion
    }

    @Override
    public void close() {
    }
}

