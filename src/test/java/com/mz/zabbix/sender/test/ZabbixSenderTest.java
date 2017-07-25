package com.mz.zabbix.sender.test;

import com.mz.zabbix.sender.DataObject;
import com.mz.zabbix.sender.SenderResult;
import com.mz.zabbix.sender.ZabbixSender;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ZabbixSenderTest {

    private static final Logger Logger = LoggerFactory.getLogger(ZabbixSenderTest.class);

	String host = "10.170.10.226";
	int port = 10051;

	@Test
	public void test_LLD_rule() throws IOException {
		ZabbixSender zabbixSender = new ZabbixSender(host, port);

		DataObject dataObject = new DataObject();
		dataObject.setHost("test_host");
		dataObject.setKey("test_item");

		JsonObject xxx = Json.createObjectBuilder().add("hello","hello").build();

		JsonArrayBuilder array = Json.createArrayBuilder();
		array.add(xxx);
		JsonObject data = Json.createObjectBuilder()
				.add("data",array).build();

		dataObject.setValue(data.toString());
		dataObject.setClock(System.currentTimeMillis()/1000);
		SenderResult result = zabbixSender.send(dataObject);

        Logger.debug("result:" + result);
		if (result.success()) {
            Logger.debug("send success.");
		} else {
            Logger.error("send fail!");
		}

	}

	@Test
	public void test() throws IOException {
		ZabbixSender zabbixSender = new ZabbixSender(host, port);

		DataObject dataObject = new DataObject();
		dataObject.setHost("test_host");
		dataObject.setKey("test_item");
		dataObject.setValue("10");
		dataObject.setClock(System.currentTimeMillis()/1000);
		SenderResult result = zabbixSender.send(dataObject);

        Logger.debug("result:" + result);
		assertNotEquals(null,result);
		assertEquals(true,result.success());

	}

}
