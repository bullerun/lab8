package server.xml;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import common.data.LabWork;

import java.io.IOException;
import java.time.LocalDate;

/**
 * class for setting the operating mode
 *
 * @author Nikita and Vlad
 * @version 0.1
 */
public class LabWorkDeserializer extends StdDeserializer<LabWork> {
    public LabWorkDeserializer() {
        this(null);
    }

    public LabWorkDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LabWork deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        try {
            LabWork labWork = new LabWork();
            labWork.setId(Long.parseLong(node.get("id").textValue().trim()));
            labWork.setName(node.get("name").textValue().trim());
            labWork.getCoordinates().setX(Float.parseFloat(node.get("coordinates").get("X_coordinates").textValue().trim()));
            labWork.getCoordinates().setY(Long.parseLong(node.get("coordinates").get("Y_coordinates").textValue().trim()));
            labWork.setCreationDate(LocalDate.parse(node.get("creationDate").textValue().trim()));
            labWork.setMinimalPoint(Long.parseLong(node.get("minimalPoint").textValue().trim()));
            if (node.has("difficulty")) labWork.setDifficulty(node.get("difficulty").textValue().trim());
            if (node.has("discipline")) {
                labWork.getDiscipline().setName(node.get("discipline").get("name").textValue().trim());
                labWork.getDiscipline().setPracticeHours(Integer.parseInt(node.get("discipline").get("practice_hours").textValue().trim()));
            } else {
                labWork.setDiscipline(null);
            }
            return labWork;
        } catch (Exception e) {
            System.out.println("Не валидное поле " + e.getMessage());
            return null;
        }
    }
}
