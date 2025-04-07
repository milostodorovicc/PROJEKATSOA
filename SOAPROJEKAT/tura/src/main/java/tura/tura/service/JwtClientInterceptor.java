package tura.tura.service;

import io.grpc.*;

public class JwtClientInterceptor implements ClientInterceptor {

    private final String jwtToken;

    public JwtClientInterceptor(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {

        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
                next.newCall(method, callOptions)) {

            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                headers.put(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER), "Bearer " + jwtToken);
                super.start(responseListener, headers);
            }
        };
    }
}
