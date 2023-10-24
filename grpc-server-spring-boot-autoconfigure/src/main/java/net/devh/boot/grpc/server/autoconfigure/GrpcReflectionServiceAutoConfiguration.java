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

package net.devh.boot.grpc.server.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.grpc.BindableService;
import io.grpc.protobuf.services.ProtoReflectionService;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * Auto configuration that sets up the proto reflection service.
 *
 * @author Daniel Theuke (daniel.theuke@heuboe.de)
 */
@Configuration
@ConditionalOnClass(ProtoReflectionService.class)
@ConditionalOnProperty(prefix = "grpc.server", name = "reflection-service-enabled", matchIfMissing = true)
@AutoConfigureBefore(GrpcServerFactoryAutoConfiguration.class)
public class GrpcReflectionServiceAutoConfiguration {

    /**
     * Creates a new ProtoReflectionService instance.
     *
     * @return The newly created bean.
     */
    @Bean
    @GrpcService
    BindableService protoReflectionService() {
        return ProtoReflectionService.newInstance();
    }

}
