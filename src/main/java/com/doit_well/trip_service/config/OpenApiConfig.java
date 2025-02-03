package com.doit_well.trip_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Messeng Mvambodo Michel Chateau",
                        email = "mcmvambodo@gmail.com",
                        url = "http://mcmvambodo.doit-well.com"
                ),
                description = "Documentation for transportation in Cameroon",
                title = "Go-Partout App",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://#"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8083"
                ),
                @Server(
                        description = "Prod ENV",
                        url = "http://mcmvambodo.doit-well.com"
                )

        }
)
public class OpenApiConfig {
}
