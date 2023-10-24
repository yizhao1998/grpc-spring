/*
 * Copyright (c) 2016-2023 The gRPC-Spring Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.devh.boot.grpc.test.codec;

import java.util.Arrays;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import net.devh.boot.grpc.common.codec.GrpcCodecDefinition;
import net.devh.boot.grpc.common.codec.GrpcCodecDiscoverer;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import net.devh.boot.grpc.test.config.BaseAutoConfiguration;
import net.devh.boot.grpc.test.config.ServiceConfiguration;

/**
 * A test checking that the server and client can start and connect to each other with minimal config and no/the
 * identity codec.
 *
 * @author Daniel Theuke (daniel.theuke@heuboe.de)
 */
@SpringBootTest(properties = {
        "grpc.client.GLOBAL.address=localhost:9090",
        "grpc.client.GLOBAL.negotiationType=PLAINTEXT"
})
@SpringJUnitConfig(classes = {
        GzipCodecTest.CustomConfiguration.class,
        ServiceConfiguration.class,
        BaseAutoConfiguration.class})
@DirtiesContext
public class GzipCodecTest extends AbstractCodecTest {

    private static final String CODEC = "gzip";

    public GzipCodecTest() {
        super(CODEC);
    }

    @Configuration
    public static class CustomConfiguration {

        @GrpcGlobalClientInterceptor
        CodecValidatingClientInterceptor gcic() {
            return new CodecValidatingClientInterceptor(CODEC);
        }

        @GrpcGlobalServerInterceptor
        CodecValidatingServerInterceptor gsic() {
            return new CodecValidatingServerInterceptor(CODEC);
        }

        @Bean
        GrpcCodecDiscoverer grpcCodecDiscoverer() {
            return () -> Arrays.asList(GrpcCodecDefinition.GZIP_DEFINITION);
        }

    }

}
