package com.example.demo;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.*;
import org.apache.olingo.commons.api.ex.ODataException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DemoEdmProvider extends CsdlAbstractEdmProvider {

    // Service Namespace
    public static final String NAMESPACE = "OData.Demo";

    // EDM Container
    public static final String CONTAINER_NAME = "Container";
    public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

    // Entity Types Names
    public static final String ET_ALPHABET_NAME = "Alphabet";
    public static final FullQualifiedName ET_ALPHABET_FQN = new FullQualifiedName(NAMESPACE, ET_ALPHABET_NAME);

    public static final String ET_RULE_NAME = "Rule";
    public static final FullQualifiedName ET_RULE_FQN = new FullQualifiedName(NAMESPACE, ET_RULE_NAME);

    public static final String ET_RULESET_NAME = "RuleSet";
    public static final FullQualifiedName ET_RULESET_FQN = new FullQualifiedName(NAMESPACE, ET_RULESET_NAME);

    public static final String ET_SYMBOL_NAME = "Symbol";
    public static final FullQualifiedName ET_SYMBOL_FQN = new FullQualifiedName(NAMESPACE, ET_SYMBOL_NAME);

    public static final String ET_SYMBOLSET_NAME = "SymbolSet";
    public static final FullQualifiedName ET_SYMBOLSET_FQN = new FullQualifiedName(NAMESPACE, ET_SYMBOLSET_NAME);

    // Entity Set Names
    public static final String ES_ALPHABETS_NAME = "Alpabets";
    public static final String ES_RULES_NAME = "Rules";
    public static final String ES_RULESETS_NAME = "RuleSets";
    public static final String ES_SYMBOLS_NAME = "Symbols";
    public static final String ES_SYMBOLSETS_NAME = "SymbolSets";

    public DemoEdmProvider() {
        super();
    }

    @Override
    public CsdlEnumType getEnumType(FullQualifiedName enumTypeName) throws ODataException {
        return super.getEnumType(enumTypeName);
    }

    @Override
    public CsdlTypeDefinition getTypeDefinition(FullQualifiedName typeDefinitionName) throws ODataException {
        return super.getTypeDefinition(typeDefinitionName);
    }

    @Override
    public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) throws ODataException {
        // this method is called for one of the EntityTypes that are configured in the Schema
        if(entityTypeName.equals(ET_ALPHABET_FQN)){
            //create EntityType properties
            CsdlProperty id = new CsdlProperty().setName("Id").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty name = new CsdlProperty().setName("Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
            CsdlProperty symbolId = new CsdlProperty().setName("SymbolId").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());

            // create CsdlPropertyRef for Key element
            CsdlPropertyRef propertyRef = new CsdlPropertyRef();
            propertyRef.setName("Id");

            // configure EntityType
            CsdlEntityType entityType = new CsdlEntityType();
            entityType.setName(ET_ALPHABET_NAME);
            entityType.setProperties(Arrays.asList(id, name , symbolId));
            entityType.setKey(Collections.singletonList(propertyRef));

            return entityType;
        } else if (entityTypeName.equals(ET_RULE_FQN)) {
            //create EntityType properties
            CsdlProperty id = new CsdlProperty().setName("Id").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty inSSId = new CsdlProperty().setName("InSSId").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty outSSId = new CsdlProperty().setName("OutSSId").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());

            // create CsdlPropertyRef for Key element
            CsdlPropertyRef propertyRef = new CsdlPropertyRef();
            propertyRef.setName("Id");

            // configure EntityType
            CsdlEntityType entityType = new CsdlEntityType();
            entityType.setName(ET_RULE_NAME);
            entityType.setProperties(Arrays.asList(id, inSSId , outSSId));
            entityType.setKey(Collections.singletonList(propertyRef));

            return entityType;
        } else if (entityTypeName.equals(ET_RULESET_FQN)) {
            //create EntityType properties
            CsdlProperty id = new CsdlProperty().setName("Id").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty alphabetFromId = new CsdlProperty().setName("AlphabetFromId").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty alphabetToId = new CsdlProperty().setName("AlphabetToId").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty ruleId = new CsdlProperty().setName("RuleId").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());

            // create CsdlPropertyRef for Key element
            CsdlPropertyRef propertyRef = new CsdlPropertyRef();
            propertyRef.setName("Id");

            // configure EntityType
            CsdlEntityType entityType = new CsdlEntityType();
            entityType.setName(ET_RULESET_NAME);
            entityType.setProperties(Arrays.asList(id, alphabetFromId , alphabetToId, ruleId));
            entityType.setKey(Collections.singletonList(propertyRef));

            return entityType;
        } else if (entityTypeName.equals(ET_SYMBOL_FQN)) {
            //create EntityType properties
            CsdlProperty id = new CsdlProperty().setName("Id").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty code = new CsdlProperty().setName("Code").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());

            // create CsdlPropertyRef for Key element
            CsdlPropertyRef propertyRef = new CsdlPropertyRef();
            propertyRef.setName("Id");

            // configure EntityType
            CsdlEntityType entityType = new CsdlEntityType();
            entityType.setName(ET_SYMBOL_NAME);
            entityType.setProperties(Arrays.asList(id, code));
            entityType.setKey(Collections.singletonList(propertyRef));

            return entityType;
        } else if (entityTypeName.equals(ET_SYMBOLSET_FQN)) {
            //create EntityType properties
            CsdlProperty id = new CsdlProperty().setName("Id").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty symbolId1 = new CsdlProperty().setName("SymbolId1").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty symbolId2 = new CsdlProperty().setName("SymbolId2").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
            CsdlProperty symbolId3 = new CsdlProperty().setName("SymbolId3").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());

            // create CsdlPropertyRef for Key element
            CsdlPropertyRef propertyRef = new CsdlPropertyRef();
            propertyRef.setName("Id");

            // configure EntityType
            CsdlEntityType entityType = new CsdlEntityType();
            entityType.setName(ET_SYMBOLSET_NAME);
            entityType.setProperties(Arrays.asList(id, symbolId1, symbolId2, symbolId3));
            entityType.setKey(Collections.singletonList(propertyRef));

            return entityType;
        }

        return null;
    }

    @Override
    public CsdlComplexType getComplexType(FullQualifiedName complexTypeName) throws ODataException {
        return super.getComplexType(complexTypeName);
    }

    @Override
    public List<CsdlAction> getActions(FullQualifiedName actionName) throws ODataException {
        return super.getActions(actionName);
    }

    @Override
    public List<CsdlFunction> getFunctions(FullQualifiedName functionName) throws ODataException {
        return super.getFunctions(functionName);
    }

    @Override
    public CsdlTerm getTerm(FullQualifiedName termName) throws ODataException {
        return super.getTerm(termName);
    }

    @Override
    public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) throws ODataException {
        if(entityContainer.equals(CONTAINER)){
            if(entitySetName.equals(ES_ALPHABETS_NAME)){
                CsdlEntitySet entitySet = new CsdlEntitySet();
                entitySet.setName(ES_ALPHABETS_NAME);
                entitySet.setType(ET_ALPHABET_FQN);

                return entitySet;
            } else if (entitySetName.equals(ES_RULES_NAME)) {
                CsdlEntitySet entitySet = new CsdlEntitySet();
                entitySet.setName(ES_RULES_NAME);
                entitySet.setType(ET_RULE_FQN);

                return entitySet;
            } else if (entitySetName.equals(ES_RULESETS_NAME)) {
                CsdlEntitySet entitySet = new CsdlEntitySet();
                entitySet.setName(ES_RULESETS_NAME);
                entitySet.setType(ET_RULESET_FQN);

                return entitySet;
            } else if (entitySetName.equals(ES_SYMBOLS_NAME)) {
                CsdlEntitySet entitySet = new CsdlEntitySet();
                entitySet.setName(ES_SYMBOLS_NAME);
                entitySet.setType(ET_SYMBOL_FQN);

                return entitySet;
            } else if (entitySetName.equals(ES_SYMBOLSETS_NAME)) {
                CsdlEntitySet entitySet = new CsdlEntitySet();
                entitySet.setName(ES_SYMBOLSETS_NAME);
                entitySet.setType(ET_SYMBOLSET_FQN);

                return entitySet;
            }
        }

        return null;
    }

    @Override
    public CsdlSingleton getSingleton(FullQualifiedName entityContainer, String singletonName) throws ODataException {
        return super.getSingleton(entityContainer, singletonName);
    }

    @Override
    public CsdlActionImport getActionImport(FullQualifiedName entityContainer, String actionImportName) throws ODataException {
        return super.getActionImport(entityContainer, actionImportName);
    }

    @Override
    public CsdlFunctionImport getFunctionImport(FullQualifiedName entityContainer, String functionImportName) throws ODataException {
        return super.getFunctionImport(entityContainer, functionImportName);
    }

    @Override
    public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) throws ODataException {
        // This method is invoked when displaying the Service Document at e.g. http://localhost:8080/DemoService/DemoService.svc
        if (entityContainerName == null || entityContainerName.equals(CONTAINER)) {
            CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
            entityContainerInfo.setContainerName(CONTAINER);
            return entityContainerInfo;
        }

        return null;
    }

    @Override
    public List<CsdlAliasInfo> getAliasInfos() throws ODataException {
        return super.getAliasInfos();
    }

    @Override
    public List<CsdlSchema> getSchemas() throws ODataException {
        // create Schema
        CsdlSchema schema = new CsdlSchema();
        schema.setNamespace(NAMESPACE);

        // add EntityTypes
        List<CsdlEntityType> entityTypes = new ArrayList<CsdlEntityType>();
        entityTypes.add(getEntityType(ET_ALPHABET_FQN));
        entityTypes.add(getEntityType(ET_RULE_FQN));
        entityTypes.add(getEntityType(ET_RULESET_FQN));
        entityTypes.add(getEntityType(ET_SYMBOL_FQN));
        entityTypes.add(getEntityType(ET_SYMBOLSET_FQN));
        schema.setEntityTypes(entityTypes);

        // add EntityContainer
        schema.setEntityContainer(getEntityContainer());

        // finally
        List<CsdlSchema> schemas = new ArrayList<CsdlSchema>();
        schemas.add(schema);

        return schemas;
    }

    @Override
    public CsdlEntityContainer getEntityContainer() throws ODataException {
        // create EntitySets
        List<CsdlEntitySet> entitySets = new ArrayList<CsdlEntitySet>();
        entitySets.add(getEntitySet(CONTAINER, ES_ALPHABETS_NAME));
        entitySets.add(getEntitySet(CONTAINER, ES_RULES_NAME));
        entitySets.add(getEntitySet(CONTAINER, ES_RULESETS_NAME));
        entitySets.add(getEntitySet(CONTAINER, ES_SYMBOLS_NAME));
        entitySets.add(getEntitySet(CONTAINER, ES_SYMBOLSETS_NAME));

        // create EntityContainer
        CsdlEntityContainer entityContainer = new CsdlEntityContainer();
        entityContainer.setName(CONTAINER_NAME);
        entityContainer.setEntitySets(entitySets);

        return entityContainer;
    }

    @Override
    public CsdlAnnotations getAnnotationsGroup(FullQualifiedName targetName, String qualifier) throws ODataException {
        return super.getAnnotationsGroup(targetName, qualifier);
    }
}
