package com.project.comit.entities.account;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.comit.entities.account.admin.Admin;
import com.project.comit.entities.account.usr.Usr;

public class AccountDeserializer extends JsonDeserializer<Account> {

	public Account deserialize(JsonParser jp, DeserializationContext context) throws IOException {
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		ObjectNode root = mapper.readTree(jp);

		if (root.has("type") && root.get("type").asText().equals("admin")) {
			return mapper.readValue(root.toString(), Admin.class);
		}
		return mapper.readValue(root.toString(), Usr.class);
	}

}
