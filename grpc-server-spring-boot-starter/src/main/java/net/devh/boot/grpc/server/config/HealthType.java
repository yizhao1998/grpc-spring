/*
 * Copyright (c) 2016-2024 The gRPC-Spring Authors
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

package net.devh.boot.grpc.server.config;


/**
 * Enum to specify the type of health service to use in GRPC.
 */
public enum HealthType {
    /**
     * Use the standard GRPC health service from io.grpc.
     * 
     * @see net.devh.boot.grpc.server.autoconfigure.GrpcHealthServiceAutoConfiguration#grpcHealthService
     */
    GRPC,
    /**
     * Uses a bridge to the Spring Boot Actuator health service.
     * 
     * @see net.devh.boot.grpc.server.autoconfigure.GrpcHealthServiceAutoConfiguration#grpcHealthServiceActuator
     * @see net.devh.boot.grpc.server.health.ActuatorGrpcHealth
     */
    ACTUATOR,
    /**
     * No health service will be created.
     */
    NONE
}
