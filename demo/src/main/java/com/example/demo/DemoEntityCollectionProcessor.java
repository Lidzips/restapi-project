package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.apache.olingo.commons.api.data.*;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.ex.ODataRuntimeException;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.*;
import org.apache.olingo.server.api.processor.EntityCollectionProcessor;
import org.apache.olingo.server.api.serializer.EntityCollectionSerializerOptions;
import org.apache.olingo.server.api.serializer.ODataSerializer;
import org.apache.olingo.server.api.serializer.SerializerResult;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class DemoEntityCollectionProcessor implements EntityCollectionProcessor {

    private OData oData;
    private ServiceMetadata serviceMetadata;

    @Override
    public void readEntityCollection(ODataRequest oDataRequest, ODataResponse oDataResponse, UriInfo uriInfo, ContentType contentType) throws ODataApplicationException, ODataLibraryException {
        // 1st we have retrieve the requested EntitySet from the uriInfo object (representation of the parsed service URI)
        List<UriResource> resourcePaths = uriInfo.getUriResourceParts();
        UriResourceEntitySet uriResourceEntitySet = (UriResourceEntitySet) resourcePaths.get(0); // in our example, the first segment is the EntitySet
        EdmEntitySet edmEntitySet = uriResourceEntitySet.getEntitySet();

        // 2nd: fetch the data from backend for this requested EntitySetName
        // it has to be delivered as EntitySet object
        EntityCollection entitySet = getData(edmEntitySet);

        // 3rd: create a serializer based on the requested format (json)
        ODataSerializer serializer = oData.createSerializer(contentType);

        // 4th: Now serialize the content: transform from the EntitySet object to InputStream
        EdmEntityType edmEntityType = edmEntitySet.getEntityType();
        ContextURL contextUrl = ContextURL.with().entitySet(edmEntitySet).build();

        final String id = oDataRequest.getRawBaseUri() + "/" + edmEntitySet.getName();
        EntityCollectionSerializerOptions opts = EntityCollectionSerializerOptions.with().id(id).contextURL(contextUrl).build();
        SerializerResult serializerResult = serializer.entityCollection(serviceMetadata, edmEntityType, entitySet, opts);
        InputStream serializedContent = serializerResult.getContent();

        // Finally: configure the response object: set the body, headers and status code
        oDataResponse.setContent(serializedContent);
        oDataResponse.setStatusCode(HttpStatusCode.OK.getStatusCode());
        oDataResponse.setHeader(HttpHeader.CONTENT_TYPE, contentType.toContentTypeString());
    }

    private EntityCollection getData(EdmEntitySet edmEntitySet){

        EntityCollection entityCollection = new EntityCollection();
        // check for which EdmEntitySet the data is requested
        if(DemoEdmProvider.ES_ALPHABETS_NAME.equals(edmEntitySet.getName())) {
            List<Entity> productList = entityCollection.getEntities();
            JdbcOperations jdbcOperations = new JdbcTemplate();
            AlphabetRepository alphabetRepository = new AlphabetRepository(jdbcOperations);
            Alphabet[] alphabets = alphabetRepository.select();
            int i = 1;
            for (Alphabet alphabet : alphabets) {
                final Entity e = new Entity()
                        .addProperty(new Property(null, "Id", ValueType.PRIMITIVE, alphabet.getId()))
                        .addProperty(new Property(null, "Name", ValueType.PRIMITIVE, alphabet.getName()))
                        .addProperty(new Property(null, "SymbolId", ValueType.PRIMITIVE, alphabet.getSymbolId()));
                e.setId(createId(edmEntitySet.getName(), i));
                productList.add(e);
                i++;
            }
        }

        if(DemoEdmProvider.ES_RULES_NAME.equals(edmEntitySet.getName())) {
            List<Entity> productList = entityCollection.getEntities();
            JdbcOperations jdbcOperations = new JdbcTemplate();
            RuleRepository ruleRepository = new RuleRepository(jdbcOperations);
            Rule[] rules = ruleRepository.select();
            int i = 1;
            for (Rule rule : rules) {
                final Entity e = new Entity()
                        .addProperty(new Property(null, "Id", ValueType.PRIMITIVE, rule.getId()))
                        .addProperty(new Property(null, "InSSId", ValueType.PRIMITIVE, rule.getInSsid()))
                        .addProperty(new Property(null, "OutSSId", ValueType.PRIMITIVE, rule.getOutSsid()));
                e.setId(createId(edmEntitySet.getName(), i));
                productList.add(e);
                i++;
            }
        }

        if(DemoEdmProvider.ES_RULESETS_NAME.equals(edmEntitySet.getName())) {
            List<Entity> productList = entityCollection.getEntities();
            JdbcOperations jdbcOperations = new JdbcTemplate();
            RuleSetRepository ruleSetRepository = new RuleSetRepository(jdbcOperations);
            RuleSet[] ruleSets = ruleSetRepository.select();
            int i = 1;
            for (RuleSet ruleSet : ruleSets) {
                final Entity e = new Entity()
                        .addProperty(new Property(null, "Id", ValueType.PRIMITIVE, ruleSet.getId()))
                        .addProperty(new Property(null, "AlphabetFromId", ValueType.PRIMITIVE, ruleSet.getAlphabetFromId()))
                        .addProperty(new Property(null, "AlphabetToId", ValueType.PRIMITIVE, ruleSet.getAlphabetToId()))
                        .addProperty(new Property(null, "RuleId", ValueType.PRIMITIVE, ruleSet.getRuleId()));
                e.setId(createId(edmEntitySet.getName(), i));
                productList.add(e);
                i++;
            }
        }

        if(DemoEdmProvider.ES_SYMBOLS_NAME.equals(edmEntitySet.getName())) {
            List<Entity> productList = entityCollection.getEntities();
            JdbcOperations jdbcOperations = new JdbcTemplate();
            SymbolRepository symbolRepository = new SymbolRepository(jdbcOperations);
            Symbol[] symbols = symbolRepository.select();
            int i = 1;
            for (Symbol symbol : symbols) {
                final Entity e = new Entity()
                        .addProperty(new Property(null, "Id", ValueType.PRIMITIVE, symbol.getId()))
                        .addProperty(new Property(null, "Code", ValueType.PRIMITIVE, symbol.getCode()));
                e.setId(createId(edmEntitySet.getName(), i));
                productList.add(e);
                i++;
            }
        }

        if(DemoEdmProvider.ES_SYMBOLSETS_NAME.equals(edmEntitySet.getName())) {
            List<Entity> productList = entityCollection.getEntities();
            JdbcOperations jdbcOperations = new JdbcTemplate();
            SymbolSetRepository symbolSetRepository = new SymbolSetRepository(jdbcOperations);
            SymbolSet[] symbolSets = symbolSetRepository.select();
            int i = 1;
            for (SymbolSet symbolSet : symbolSets) {
                final Entity e = new Entity()
                        .addProperty(new Property(null, "Id", ValueType.PRIMITIVE, symbolSet.getId()))
                        .addProperty(new Property(null, "SymbolId1", ValueType.PRIMITIVE, symbolSet.getSymbolId1()))
                        .addProperty(new Property(null, "SymbolId2", ValueType.PRIMITIVE, symbolSet.getSymbolId2()))
                        .addProperty(new Property(null, "SymbolId3", ValueType.PRIMITIVE, symbolSet.getSymbolId3()));
                e.setId(createId(edmEntitySet.getName(), i));
                productList.add(e);
                i++;
            }
        }

        return entityCollection;
    }

    private URI createId(String entitySetName, Object id) {
        try {
            return new URI(entitySetName + "(" + String.valueOf(id) + ")");
        } catch (URISyntaxException e) {
            throw new ODataRuntimeException("Unable to create id for entity: " + entitySetName, e);
        }
    }

    @Override
    public void init(OData oData, ServiceMetadata serviceMetadata) {
        this.oData = oData;
        this.serviceMetadata = serviceMetadata;
    }
}
