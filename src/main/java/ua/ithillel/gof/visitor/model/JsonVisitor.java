package ua.ithillel.gof.visitor.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonVisitor implements Visitor {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void visitPersonCollection(PersonCollection personCollection) {
        try {
            String s = objectMapper.writeValueAsString(personCollection.getPersons());
            System.out.println(s);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void visitBookCollection(BookCollection bookCollection) {
        try {
            String s = objectMapper.writeValueAsString(bookCollection.getBooks());
            System.out.println(s);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
