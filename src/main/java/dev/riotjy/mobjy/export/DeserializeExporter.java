package dev.riotjy.mobjy.export;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.riotjy.mobjy.export.codegen.java.JavaClassCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaDeserializeClassCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaDeserializeObjectCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaDeserializeStaticCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaImportsCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaPackageCodeGenerator;
import dev.riotjy.mobjy.export.codegen.java.JavaResourceCodeGenerator;
import dev.riotjy.mobjy.model.MjyClass;
import dev.riotjy.mobjy.model.MjyModel;

public class DeserializeExporter {

  final static Logger log = LoggerFactory.getLogger(SerializeExporter.class);

  MjyModel theModel;

  private  DeserializeExporter() {}

  public DeserializeExporter(MjyModel model) {
    super();
    theModel = model;
  }
  
  public void exportJava (String path) {

    JavaResourceCodeGenerator resourceGen = new JavaResourceCodeGenerator(theModel.getProject() + "Serializer");

    resourceGen.addPart(new JavaPackageCodeGenerator(theModel.getLanguageSettingValue("java", "package")));

    JavaImportsCodeGenerator importGen = 
        new JavaImportsCodeGenerator(true, true);
    importGen.addImport("java.util.Iterator");
    importGen.addImport("java.util.Map.Entry");
    importGen.addImport("com.google.gson.JsonArray");
    importGen.addImport("com.google.gson.JsonElement");
    importGen.addImport("com.google.gson.JsonObject");
    importGen.addImport("com.google.gson.JsonParser");
    importGen.addImport("com.google.gson.JsonPrimitive");
    resourceGen.addPart(importGen);

    String capitalized = theModel.getProject().substring(0,1).toUpperCase() + theModel.getProject().substring(1);
    JavaClassCodeGenerator classGen = new JavaClassCodeGenerator(capitalized + "Deserializer", false, null);
    
    JavaDeserializeObjectCodeGenerator objGen = new JavaDeserializeObjectCodeGenerator();

    for (MjyClass clazz : theModel.getClasses()) {
      objGen.addClassName(clazz.getName());
      
      MjyClass gener = clazz.getGeneralization();
      
      JavaDeserializeClassCodeGenerator clsGen  = 
          new JavaDeserializeClassCodeGenerator(clazz.getName(), (null != gener) ? gener.getName() : null,
            clazz.getAttributesInfo(), clazz.getArraysInfo(), clazz.getMapsInfo());
      classGen.addPart(clsGen);
    }
    classGen.addPart(objGen);
    classGen.addPart(new JavaDeserializeStaticCodeGenerator());
    resourceGen.addPart(classGen);

    String code = resourceGen.generate();
    log.info("\n#########################\n" + code + "\n#########################\n\n");
  }

}
