package org.mohansun.kafka.deserializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mohansun.kafka.pojo.CustomMessage;

public class CustomDeserializer implements Deserializer<CustomMessage> {
	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
	}

	@Override
	public CustomMessage deserialize(String topic, byte[] data) {
		ObjectMapper mapper = new ObjectMapper();
		CustomMessage object = null;
		try {
			object = mapper.readValue(data, CustomMessage.class);
		} catch (Exception exception) {
			System.out.println("Error in deserializing bytes " + exception);
		}
		return object;
	}

	@Override
	public void close() {
	}
}