package com.maatic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjetland.jackson.jsonSchema.JsonSchemaConfig;
import com.kjetland.jackson.jsonSchema.JsonSchemaGenerator;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class StudentController {

    @PostMapping("/student")
    public Student home(@Valid @RequestBody Student student) throws JsonProcessingException {
        JSONObject jsonObject = new JSONObject(student.getAddress());
        ObjectMapper mapper = new ObjectMapper();
        JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper, JsonSchemaConfig.nullableJsonSchemaDraft4());
        JsonNode addressSchema = schemaGen.generateJsonSchema(Address.class);
        String jsonSchemaAsString = mapper.writeValueAsString(addressSchema);
        jsonSchemaAsString = addPattern(jsonSchemaAsString);
        JSONObject jsonSchema = new JSONObject(jsonSchemaAsString);
        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonObject);
        return student;
    }

    private String addPattern(String schemaString) {
        String[] splits = schemaString.split("\"string\"");
        StringBuilder schema = new StringBuilder();
        for(int i = 0; i < splits.length; i++) {
            if(i < splits.length - 1) {
                schema.append(splits[i]);
                schema.append("\"string\", \"pattern\": \"^[a-zA-Z0-9,.@$\\\\s]*$\"");
            } else {
                schema.append(splits[i]);
            }
        }
        return schema.toString();
    }
}
