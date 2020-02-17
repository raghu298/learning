package org.learning.poc.scylla.springbootscyllaproducer.repository;

import org.learning.poc.scylla.springbootscyllaproducer.models.entity.DeviceMetircs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DeviceMetricsRepository  {

    @Autowired
    private CassandraOperations template;


    public List<DeviceMetircs> findAll() {
        return template.select(" select * from device_metrics ", DeviceMetircs.class);
    }

    public DeviceMetircs save(DeviceMetircs deviceMetircs) {
        return template.insert(deviceMetircs);
    }
    public DeviceMetircs update(DeviceMetircs deviceMetircs) {
        return template.update(deviceMetircs);
    }

    public Boolean existsById(UUID id) {
        Query query = Query.query(Criteria.where("id").is(id));

        return template.exists(query, DeviceMetircs.class);
    }

    public void deleteById(UUID id) {
        Query query = Query.query(Criteria.where("id").is(id));
        template.delete(query, DeviceMetircs.class);


    }
}
