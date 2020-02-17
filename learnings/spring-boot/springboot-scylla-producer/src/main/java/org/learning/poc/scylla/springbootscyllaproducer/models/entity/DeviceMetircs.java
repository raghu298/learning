package org.learning.poc.scylla.springbootscyllaproducer.models.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table("device_metrics")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceMetircs {


    @Id
    @PrimaryKeyColumn(name = "id" , type = PrimaryKeyType.PARTITIONED)
    private UUID id;

    @Column("device_id")
    private String deviceId;

    @Column("device_type")
    private  String deviceType;

    @Column("created_time")
    private LocalDateTime createdOn;

    @Column("modified_time")
    private LocalDateTime modifiedOn;

}

