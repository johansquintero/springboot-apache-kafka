package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic generateTopic(){
        Map<String,String> configurations = new HashMap<>();

        //Tratamiento de borrado de los mensajes, delete(borra el mensaje despues de un tiempo)
        //existe otro como el compact(mantiene el mas actualizado)
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG,TopicConfig.CLEANUP_POLICY_DELETE);

        //Duracion de retencion un mensaje en milisegundos
        configurations.put(TopicConfig.RETENTION_MS_CONFIG,"8600000");

        //Tamano maximo de cada segmento o particion del topic(1 gb)
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG,"1073741824");

        //tamano maximo de cada mensaje por defeto esta en 1Mb
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG,"1000000");

        return TopicBuilder.name("GreetingTopic")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();
    }
}
